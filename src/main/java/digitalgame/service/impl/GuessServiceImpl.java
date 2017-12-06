package digitalgame.service.impl;

import digitalgame.dao.BetInfoMapper;
import digitalgame.dao.OpenInfoMapper;
import digitalgame.model.po.*;
import digitalgame.service.GuessService;
import digitalgame.service.OddsInfoService;
import digitalgame.service.UserFinanceAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.java2d.pipe.SpanShapeRenderer;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class GuessServiceImpl implements GuessService,Serializable {


    @Autowired
    OddsInfoService ois;

    @Autowired
    BetInfoMapper betInfoMapper;

    @Autowired
    OpenInfoMapper openInfoMapper;

    @Autowired
    UserFinanceAccountService userFinanceAccountService;

    @Override
    public void doBet(List<BetInfo> betInfoList, OpenInfo openInfo) {
        //先删除当期投注数据，再下注，否则可能会重复下注
        betInfoMapper.deleteByOpenId(openInfo.getId());
        betInfoMapper.addBatch(betInfoList);
    }

    @Override
    public List<BetInfo> analysisBetContent(OpenInfo openInfo,String betContent) {
        String [] arrBet = betContent.split("\n");

        List<OddsInfo>  oddsInfos = ois.selectOddsList();
        List<BetInfo> betInfos = new ArrayList<>();
        String betMan = ""; //投注人
        String betTime = ""; //投注时间

        /**
         * 这里需要对内容进行解析
         * 1、现对用户进行解析，用户的标识为本句最后一个为日期，日期前为昵称
         */
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(String strBet : arrBet){
            strBet = strBet.trim();
            //截取最后一个空格后的内容，如果是日期，则前面为姓名
            String strTime = strBet.substring(strBet.lastIndexOf(" ")+1);

            Date betDate = null;

            //如果日期为空，表示当前不属于昵称行，直接解析，否则先解析人名
            boolean isBet = true;
            if(strTime != null && !"".equals(strTime)){
                try {
                    //是投注人，记录下来
                    betDate = sdf.parse(strTime);
                    betTime = sdf1.format(betDate);
                    betMan = strBet.substring(0,strBet.lastIndexOf(" "));
                    //有些qq版本copy出来的昵称是 xxx(23424)带qq号码的，需要处理掉
                    if(betMan.contains("(") && betMan.contains(")")){
                        betMan = strBet.substring(0,strBet.lastIndexOf("("));
                    }
                    isBet = false;
                } catch (ParseException e) {
                    System.out.println("不是投注人，解析内容 ，看看是否在下注。。。");
                }
            }

            //如果是下注，进行解析 ps：标注下注格式应该 xxx100，投注内容+金额
            Pattern pattern = Pattern.compile("((\\D+)(\\d+))");
            Matcher m = pattern.matcher(strBet);
            if(isBet && m.lookingAt()){

                //因为每条下注内容可能包含多条下注信息，所以还需要解析
                do{
                    String betStr = m.group(2); //下注内容，可能是多条，比如 大龙小龙全双100，那么这里会匹配到 大龙小龙全双
                    String betMoney = m.group(3); //下注金额

                    //获取下注内容与标准投注内容进行正则匹配，如果能匹配上则进行处理
                    boolean flag = true;
                    for (OddsInfo oddsinfo : oddsInfos) {
                        if (betStr.contains(oddsinfo.getOddsName())) {
                            BetInfo tmpBi = new BetInfo();
                            tmpBi.setOpenId(openInfo.getId());
                            tmpBi.setBetman(betMan.trim());
                            tmpBi.setBetitem(oddsinfo.getOddsName());
                            tmpBi.setBetmoney(Double.valueOf(betMoney));
                            tmpBi.setCreateTime(betTime);
                            betInfos.add(tmpBi);
                            betStr  = betStr.replace(oddsinfo.getOddsName(),""); //可能有复合下注，比如 个大个单25 需要计息出个大、个单
                            if(betStr.trim().equals(""))
                                break;
                        }
                    }
                }while(m.find());

            }

        }

        return betInfos;
    }

    /**
     * 将BetInfo信息进行group
     * @param betInfoList
     * @return
     */
    private List<UserBetInfo> toUserBetInfoList(List<BetInfo> betInfoList){
        List<UserBetInfo> userBetInfoList = new ArrayList<>();
        Map<String,UserBetInfo> map = new HashMap<>();
        //group
        for(BetInfo betInfo : betInfoList){
            if(map.containsKey(betInfo.getBetman())){
                map.get(betInfo.getBetman()).getBetInfoList().add(betInfo);
            }else{
                UserBetInfo userBetInfo = new UserBetInfo();
                userBetInfo.setUserName(betInfo.getBetman());
                userBetInfo.getBetInfoList().add(betInfo);
                map.put(betInfo.getBetman(),userBetInfo);
            }
        }

        //to list
        Iterator<UserBetInfo> iter = map.values().iterator();
        while(iter.hasNext()){
            UserBetInfo userBetInfo = iter.next();
            //查询用户账户余额

            //计算投注总额
            double betSum = 0;
            for(BetInfo betInfo: userBetInfo.getBetInfoList()){
                betSum += betInfo.getBetmoney();
            }
            userBetInfo.setBetSum(betSum);

            userBetInfoList.add(userBetInfo);
        }
        return userBetInfoList;
    }

    @Override
    public List<UserBetInfo> doOpen(OpenInfo openInfo) {
        /**
         * 开奖步骤 ：
         * 1、保存开奖信息；
         * 2、将下注内容传递给中奖算法的计算引擎
         * 3、将中奖信息传递给资金处理类，进行资金处理
         */
        Date now = new Date();
        OpenInfo info = new OpenInfo();
        String openNum = openInfo.getOpenNum();
        long openNo = openInfo.getOpenNo();
        if(openNum != null) {
            String[] openNumArr = new String[]{String.valueOf(openNum.charAt(0)), String.valueOf(openNum.charAt(1)), String.valueOf(openNum.charAt(2))};
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
            openNum = "";
            int sum = 0;
            for (String num : openNumArr) {
                openNum = openNum + num + ",";
                sum += Integer.parseInt(num);
            }
            if (openNum.endsWith(",")) {
                openNum = openNum.substring(0, openNum.length() - 1);
            }

            String result = openNum;
            if (sum < 13) {
                result += "  小";
            } else {
                result += "  大";
            }

            if (sum % 2 == 0) {
                result += "  双";
            } else {
                result += "  单";
            }
            info.setOpenNum(openNum);
            info.setOpenResult(result);
            info.setCreateTime(sdf.format(now));
            info.setOpenTime(sdf.format(now));
            info.setId(openInfo.getId());
            info.setOpenNo(openInfo.getOpenNo());
        }

        info.setOpenNo(openNo);
        openInfoMapper.updateOpenInfo(info); //保存开奖结果

        return null;
    }

    @Override
    public OpenInfo getNextOpenInfo() {

        //查询数据库中最新的编号 +1，如果没有使用当前日期创建一个 yyyyMMdd001
        String openNo = "";
        OpenInfo openInfo = openInfoMapper.selectTodayLasted();
        OpenInfo newOpenInfo = new OpenInfo();
        if(openInfo != null){
            if(openInfo.getOpenNum() == null)
                openNo = String.valueOf(openInfo.getOpenNo());
            else {
                openNo = String.valueOf(openInfo.getOpenNo() + 1);
                newOpenInfo.setOpenNo(Long.parseLong(openNo));
                openInfoMapper.insert(newOpenInfo);
                openInfo = openInfoMapper.selectTodayLasted();
            }

        }else
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date now = new Date();
            String strNow = sdf.format(now);
            openNo = strNow+"001";
            newOpenInfo.setOpenNo(Long.parseLong(openNo));
            openInfoMapper.insert(newOpenInfo);
            openInfo = openInfoMapper.selectTodayLasted();

        }
        newOpenInfo.setId(openInfo.getId());
        newOpenInfo.setOpenNo(Long.parseLong(openNo));
        return newOpenInfo;
    }

    @Override
    public List<OpenInfo> getTodayOpenResult() {
        List<OpenInfo> openInfoList = openInfoMapper.selectTop();
        return openInfoList;
    }

    @Override
    public List<BetInfo> getBetInfoByOpenId(int openId) {
        return betInfoMapper.selectByOpenId(openId);
    }

    @Override
    public List<UserBetInfo> caculateUserBetInfo(List<BetInfo> betInfoList) {

        /**
         * 分别统计用户本局投注总分数，账户总分数，输赢分数
         */
        Map<String,UserBetInfo> map = new HashMap<String,UserBetInfo>();

        for(BetInfo betInfo : betInfoList){
            if(map.containsKey(betInfo.getBetman())){
                UserBetInfo userBetInfo = map.get(betInfo.getBetman());
                userBetInfo.setUserName(betInfo.getBetman());
                userBetInfo.setBetSum(userBetInfo.getBetSum()+betInfo.getBetmoney());
                userBetInfo.setReturnSum(userBetInfo.getReturnSum() + betInfo.getReturnMoney());
                userBetInfo.setUserBalance(userBetInfo.getUserBalance()-betInfo.getReturnMoney());

            }else{
                UserBetInfo userBetInfo = new UserBetInfo();
                userBetInfo.setUserName(betInfo.getBetman());
                userBetInfo.setBetSum(userBetInfo.getBetSum()+betInfo.getBetmoney());
                userBetInfo.setReturnSum(userBetInfo.getReturnSum() + betInfo.getReturnMoney());
                //获取账户余额
                UserFinanceAccount userFinanceAccount = userFinanceAccountService.queryUserFinanceAccountByNickName(userBetInfo.getUserName().trim());
                if(userFinanceAccount!=null) {
                    userBetInfo.setUserBalance(userFinanceAccount.getBalance() - betInfo.getReturnMoney());
                }
                map.put(userBetInfo.getUserName(),userBetInfo);
            }
        }

        List<UserBetInfo> userBetInfoList = new ArrayList<>();
        Iterator<UserBetInfo> entries = map.values().iterator();
        while(entries.hasNext()){
            userBetInfoList.add(entries.next());
        }
        return userBetInfoList;
    }

    @Override
    public List<BetInfo> getBetInfoByOpenNo(int openNo) {
        return betInfoMapper.selectByOpenNo(openNo);
    }

    @Override
    public OpenInfo getOpenInfoByOpenNo(long openNo) {
        return openInfoMapper.selectByOpenNo(openNo);
    }
}
