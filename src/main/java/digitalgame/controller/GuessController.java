package digitalgame.controller;

import digitalgame.model.po.BetInfo;
import digitalgame.model.po.OpenInfo;
import digitalgame.model.po.UserBetInfo;
import digitalgame.service.GuessService;
import digitalgame.service.OddsInfoService;
import digitalgame.service.UserFinanceAccountService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


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
        guessService.doBet(list,openInfo);

        List<Object> listResult = new ArrayList<Object>();
        listResult.add(list);
        listResult.add(openInfo);

        String result = JSONArray.fromObject(listResult).toString();
        return result;
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
        guessService.doOpen(openInfo);

        //查询投注信息
        List<BetInfo> betInfoList = guessService.getBetInfoByOpenId(openInfo.getId());
        int i = Integer.parseInt(String.valueOf(openNum.charAt(0)));
        int j = Integer.parseInt(String.valueOf(openNum.charAt(1)));
        int k = Integer.parseInt(String.valueOf(openNum.charAt(2)));
        UserBetInfo userBetInfo = new UserBetInfo();
        userBetInfo.setBetInfoList(betInfoList);
        List<UserBetInfo> userBetInfoList = new ArrayList<>();
        userBetInfoList.add(userBetInfo);

        //开奖
        List<UserBetInfo> userBetInfoList1 = oddsInfoService.oddsNumber(i,j,k,userBetInfoList,String.valueOf(openInfo.getId()));
        for(UserBetInfo tmpUserBetInfo : userBetInfoList1){
            for(BetInfo tmpBetInfo : tmpUserBetInfo.getBetInfoList()){
                if(tmpBetInfo.getReturnMoney() <= 0){
                    //未中奖
                    userFinanceAccountService.reduceUserBalanceByNickName(tmpBetInfo.getBetman(),tmpBetInfo.getBetmoney(),openInfo.getId());
                }else{
                    //中奖
                    userFinanceAccountService.addUserBalanceByNickName(tmpBetInfo.getBetman(),tmpBetInfo.getBetmoney(),openInfo.getId());
                }

            }
        }


        session.removeAttribute("openInfo");

        List<Object> result = new ArrayList<>();
        result.add(openInfo.getOpenResult());
        result.add(userBetInfoList1.get(0).getBetInfoList());

       return JSONArray.fromObject(result).toString();

    }


    /**
     * 获取下一次开奖的期数
     * @return
     */
    public String getNextOpenNO(){

        return "20171025001期";
    }


    @ResponseBody
    @RequestMapping(value="/viewHistory",method = RequestMethod.GET)
    public String viewHistory(){
        List<OpenInfo> openInfoList = guessService.getTodayOpenResult();
        String result = JSONArray.fromObject(openInfoList).toString();
        return result;
    }
}
