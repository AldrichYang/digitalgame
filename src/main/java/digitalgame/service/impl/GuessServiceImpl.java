package digitalgame.service.impl;

import digitalgame.dao.BetInfoMapper;
import digitalgame.model.po.BetInfo;
import digitalgame.model.po.OddsInfo;
import digitalgame.service.GuessService;
import digitalgame.service.OddsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
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

    @Override
    public void doBet(List<BetInfo> betInfoList) {
        betInfoMapper.addBatch(betInfoList);
    }

    @Override
    public List<BetInfo> analysisBetContent(String betContent) {
        String [] arrBet = betContent.split("\n");

        List<OddsInfo>  oddsInfos = ois.selectOddsList();
//        Map<String, BetInfo> mapBetInfo = new HashMap<>();
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
                    for(OddsInfo oddsinfo: oddsInfos){
                        if(betStr.contains(oddsinfo.getOddsName())){
                            BetInfo tmpBi = new BetInfo();
                            tmpBi.setBetman(betMan);
                            tmpBi.setBetitem(oddsinfo.getOddsName());
                            tmpBi.setBetmoney(Double.valueOf(betMoney));
                            tmpBi.setCreateTime(betTime);
                            betInfos.add(tmpBi);
                        }
                    }
                }while(m.find());

            }

        }

        return betInfos;
    }

//    private List<BetInfo> toList(Map<String,BetInfo> mapBetinfo){
//        List<BetInfo> list = new ArrayList<>();
//        while(mapBetinfo.values().iterator().hasNext()){
//            list.add(mapBetinfo.values().iterator().next());
//        }
//        return list;
//    }

//    /**
//     * 下注
//     * @param mapBetInfo 投注的map，不可以为null哦，否则会报错
//     * @param betMan 投注人
//     * @param item 投注内容
//     */
//    private void bet(Map<String,BetInfo> mapBetInfo, String betMan, BetItem item){
//
//        if(mapBetInfo.containsKey(betMan)){
//            BetInfo betInfo = mapBetInfo.get(betMan);
//            betInfo.getBetList().add(item);
//        }else{
//            BetInfo betInfo = new BetInfo();
//            betInfo.getBetList().add(item);
//            mapBetInfo.put(betMan,betInfo);
//        }
//    }

    public static void main(String[] args) {
        String str = "陕西-百大100-24-波浪谷旅游服务18291273704  20:41:59\ndfd";
        //如果是下注，进行解析 ps：标注下注格式应该 xxx100，投注内容+金额
        Pattern pattern = Pattern.compile("(.*)(\\D+)(\\d+)(.*)");
        System.out.println(pattern.matcher(str).matches());


    }
}
