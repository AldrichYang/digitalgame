package digitalgame.controller;

import digitalgame.model.po.AccountParam;
import digitalgame.model.po.BetInfo;
import digitalgame.model.po.OpenInfo;
import digitalgame.service.GuessService;
import digitalgame.service.OddsInfoService;
import digitalgame.service.UserFinanceAccountService;
import javafx.scene.control.TextFormatter;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SimpleTimeZone;


/**
 * 用于处理和竞猜相关的控制跳转
 *
 * 处理流程：
 * 1、解析竞猜内容，判断积分是否足够
 * 2、开奖（开奖前先下注，然后返奖）
 * @author simon
 * @version 1.0 2017/10/01
 */
@Controller
@RequestMapping("/guess")
public class GuessController {

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(){
        return "guess";
    }

    @Autowired
    GuessService guessService;

    @Autowired
    OddsInfoService oddsInfoService;

    @Autowired
    UserFinanceAccountService userFinanceAccountService;

    /**
     * 解析下注内容，返回标准格式下注内容
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/analysis", method=RequestMethod.POST)
    public String analysisBet(HttpSession session,@RequestParam(value = "betContent",required = true)String betContent){
        //从session中获取下次开奖信息
        OpenInfo openInfo = (OpenInfo)session.getAttribute("openInfo");
        if(openInfo == null){
            openInfo = guessService.getNextOpenInfo();
             session.setAttribute("openInfo",openInfo);
        }


        //分析下注内容
        List<BetInfo> list = guessService.analysisBetContent(openInfo,betContent);

        //把下注内容保存到数据库，同时调用资金的方法进行资金划转
        List<Object> listResult = new ArrayList<Object>();
        if(list != null && list.size()>0) {
            guessService.doBet(list, openInfo);
        }
        listResult.add(list);
        listResult.add(openInfo);
        String result = JSONArray.fromObject(listResult).toString();
        return result;
    }



    @ResponseBody
    @RequestMapping(value="/reopen" ,method = RequestMethod.POST)
    public String reOpen(HttpSession session,@RequestParam(value="openNum", required = true)String openNum,@RequestParam(value="openNo", required = true)String openNo){
        /**
         * 重新开奖，先对之前的开奖资金进行撤销
         */
        //1、收到的openNo为数字类型，需要转换为yyyymmddxxx格式的开奖期数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String now = sdf.format(date);
        now = now + String.format("%03d",Integer.parseInt(openNo));

        //2、查询本期开奖结果，进行撤销
        List<BetInfo> betInfoList = guessService.getBetInfoByOpenId(1);
        for(BetInfo betInfo : betInfoList){
            AccountParam ap = new AccountParam();
            ap.setPeriods(now);
            userFinanceAccountService.revocationUserBalanceByNickName(betInfo.getBetitem(),betInfo.getReturnMoney(),ap);
        }

        OpenInfo openInfo = guessService.getOpenInfoByOpenNo(Long.parseLong(now));
        openInfo.setOpenNum(openNum);

        //调用开奖进行处理
        return this.doOpen(session,openNum,openInfo);
    }

    /**
     * 开奖
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/open" ,method = RequestMethod.POST)
    public String open(HttpSession session,@RequestParam(value="openNum", required = true)String openNum){

        //开奖之后从session中将开奖期数删除
        OpenInfo openInfo = (OpenInfo) session.getAttribute("openInfo");
        openInfo.setOpenNum(openNum);
        return this.doOpen(session,openNum, openInfo);
    }

    /**
     * 开奖
     * @param session
     * @param openNum
     * @return
     */
    private String doOpen(HttpSession session, String openNum, OpenInfo openInfo){
        guessService.doOpen(openInfo);

        //查询投注信息
        List<BetInfo> betInfoList = guessService.getBetInfoByOpenId(openInfo.getId());
        int i = Integer.parseInt(String.valueOf(openNum.charAt(0)));
        int j = Integer.parseInt(String.valueOf(openNum.charAt(1)));
        int k = Integer.parseInt(String.valueOf(openNum.charAt(2)));

        /**
         * 先下注 ，后计算中奖结果
         * 下注，返回下注成功的金额（如果返回<0表示余额不足）
         * ps：如果用户下注金额超过账户余额，以账户余额为准，除非用户下注类型为（全大、全小、全单、全双）这几种类型为复合类型，余额不足不可以下注
         */
        for(BetInfo tmpBetInfo : betInfoList){
            AccountParam ap = new AccountParam();
            ap.setPeriods(String.valueOf(openInfo.getId()));
            ap.setOrderId(String.valueOf(tmpBetInfo.getId()));
            int successMoney = userFinanceAccountService.reduceUserBalanceByNickName(tmpBetInfo.getBetman(),tmpBetInfo.getBetmoney(),ap);
            tmpBetInfo.setBetmoney(Double.valueOf(successMoney));
        }


        //开奖，计算用户中奖结果
        List<BetInfo> betInfoList1 = oddsInfoService.oddsNumber(i,j,k,betInfoList,String.valueOf(openInfo.getId()));
        for(BetInfo tmpBetInfo : betInfoList1){
            AccountParam ap = new AccountParam();
            ap.setPeriods(String.valueOf(openInfo.getId()));
            ap.setOrderId(String.valueOf(tmpBetInfo.getId()));
            if(tmpBetInfo.getReturnMoney() > 0){
                //中奖，将本金和中奖金额一起返还
                userFinanceAccountService.addUserBalanceByNickName(tmpBetInfo.getBetman(),tmpBetInfo.getBetmoney()+tmpBetInfo.getBetmoney(),ap);
            }
        }


        session.removeAttribute("openInfo");

        List<Object> result = new ArrayList<>();
        result.add(openInfo.getOpenResult());
        result.add(betInfoList1);
        result.add(guessService.caculateUserBetInfo(betInfoList1));

        return JSONArray.fromObject(result).toString();
    }


    @ResponseBody
    @RequestMapping(value="/viewHistory",method = RequestMethod.GET)
    public String viewHistory(){
        List<OpenInfo> openInfoList = guessService.getTodayOpenResult();
        String result = JSONArray.fromObject(openInfoList).toString();
        return result;
    }
}
