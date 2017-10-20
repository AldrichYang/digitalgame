package digitalgame.common;

import digitalgame.dao.BetResultMapper;
import digitalgame.model.po.BetInfo;
import digitalgame.model.po.BetResult;
import digitalgame.model.po.UserBetInfo;
import digitalgame.service.OddsInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 *  下注结果判断
 */
public class OddsConstants {

    /**
     * 判断数字是否为大，大返回真，小返回假
     * @param num
     * @return
     */
    private boolean isBigNum(int num){
        if(num > 4){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断数字为双
     * @param num
     * @return
     */
    private boolean isBigDouble(int num){
        if(num % 2 == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断三个是否为顺子（或者倒顺）
     * @param i 个位
     * @param j 十位
     * @param k 百位
     * @return
     */
    private boolean isShunzi(int i,int j,int k){
        if((j == i + 1 && k == j + 1) || (i == j+1 && j == k+1)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断所有数的和为大小
     * @param allNum
     * @return
     */
    private boolean isAllNumBig(int allNum){
        if(allNum > 13){
            return true;
        }else {
            return false;
        }
    }
    @Autowired
    protected BetResultMapper betResultMapper;

    @Autowired
    private OddsInfoService oddsInfoService;

    public List<UserBetInfo> oddsNumber(int i,int j,int k,List<UserBetInfo> userBetInfoList){

        HashMap<String, Integer> oddsMap = oddsInfoService.selectOddsMap();
        int allNum = i + j + k;
        if(null == userBetInfoList){
            return null;
        }else{
            for (UserBetInfo userBetInfo : userBetInfoList){
                List<BetInfo> betInfoList = userBetInfo.getBetInfoList();
                for(BetInfo betInfo :betInfoList) {
                    BetResult betResult = new BetResult();

                    int result = oddsMap.get(betInfo.getBetitem()) == null ? 0 : oddsMap.get(betInfo.getBetitem());
                    int betNum = 0;
                    if ("百大".equals(betInfo.getBetitem())) {
                        if(isBigNum(k)){
                            betNum = result;
                        }
                    } else if ("百小".equals(betInfo.getBetitem())) {
                        if(!isBigNum(k)){
                            betNum = result;
                        }
                    } else if ("百单".equals(betInfo.getBetitem())) {
                        if(!isBigDouble(k)){
                            betNum = result;
                        }
                    } else if ("百双".equals(betInfo.getBetitem())) {
                        if(isBigDouble(k)){
                            betNum = result;
                        }
                    } else if ("十双".equals(betInfo.getBetitem())) {
                        if(isBigDouble(j)){
                            betNum = result;
                        }
                    } else if ("大".equals(betInfo.getBetitem())) {
                        if(isAllNumBig(allNum)){
                            betNum = result;
                        }
                    } else if ("小".equals(betInfo.getBetitem())) {
                        if(!isAllNumBig(allNum)){
                            betNum = result;
                        }
                    } else if ("单".equals(betInfo.getBetitem())) {
                        if(!isBigDouble(allNum)){
                            betNum = result;
                        }
                    } else if ("双".equals(betInfo.getBetitem())) {
                        if(isBigDouble(allNum)){
                            betNum = result;
                        }
                    } else if ("龙大".equals(betInfo.getBetitem())) {
                        if(allNum > 19){
                            betNum = result;
                        }
                    } else if ("龙小".equals(betInfo.getBetitem())) {
                        if (allNum < 8){
                            betNum = result;
                        }
                    } else if ("龙".equals(betInfo.getBetitem())) {

                    } else if ("小龙小双".equals(betInfo.getBetitem())) {

                    } else if ("全大".equals(betInfo.getBetitem())) {
                        if(isBigNum(i) && isBigNum(j) && isBigNum(k)){
                            betNum = result;
                        }
                    } else if ("全小".equals(betInfo.getBetitem())) {
                        if(!isBigNum(i) && !isBigNum(j) && !isBigNum(k)){
                            betNum = result;
                        }
                    }else if("全单".equals(betInfo.getBetitem())){
                        if(!isBigDouble(i) && !isBigDouble(j) && !isBigDouble(k)){
                            betNum = result;
                        }
                    }else if("全双".equals(betInfo.getBetitem())){
                        if(isBigDouble(i) && isBigDouble(j) && isBigDouble(k)){
                            betNum = result;
                        }
                    }else if("顺子".equals(betInfo.getBetitem())){
                        if(isShunzi(i,j,k)){
                            betNum = result;
                        }
                    }
                    betInfo.setReturnMoney(betInfo.getBetmoney() * betNum);
                    betResult.setBetnumber(betInfo.getBetmoney());
                    betResult.setBettype(betInfo.getBetitem());
                    betResult.setBetuser(betInfo.getBetman());
                    betResult.setBetuserid(betInfo.getUserId());
                    betResult.setResultnumber(betInfo.getReturnMoney());
                    betResult.setBetdate("");
                    betResultMapper.insertSelective(betResult);
                }
            }
        }
        return userBetInfoList;
    }
}
