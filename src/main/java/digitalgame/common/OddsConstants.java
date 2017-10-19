package digitalgame.common;

import digitalgame.model.po.BetInfo;

import java.util.ArrayList;
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

//
//    public List<BetItem> oddsNumber(int i,int j,int k,BetInfo betInfo){
//        List<BetItem> newBetList = new ArrayList<>() ;
//        List<BetItem> betList = betInfo.getBetList();
//        int allNum = i + j + k;
//
//
//        for(BetItem betItem :betList) {
//            int betNum = 0;
//            if ("百大".equals(betItem.getBetName())) {
//                if(isBigNum(k)){
//                    betNum = 2;
//                }
//            } else if ("百小".equals(betItem.getBetName())) {
//                if(!isBigNum(k)){
//                    betNum = 2;
//                }
//            } else if ("百单".equals(betItem.getBetName())) {
//                if(!isBigDouble(k)){
//                    betNum = 2;
//                }
//            } else if ("百双".equals(betItem.getBetName())) {
//                if(isBigDouble(k)){
//                    betNum = 2;
//                }
//            } else if ("十双".equals(betItem.getBetName())) {
//                if(isBigDouble(j)){
//                    betNum = 2;
//                }
//            } else if ("大".equals(betItem.getBetName())) {
//                if(isAllNumBig(allNum)){
//                    betNum = 2;
//                }
//            } else if ("小".equals(betItem.getBetName())) {
//                if(!isAllNumBig(allNum)){
//                    betNum = 2;
//                }
//            } else if ("单".equals(betItem.getBetName())) {
//                if(!isBigDouble(i + j + k)){
//                    betNum = 2;
//                }
//            } else if ("双".equals(betItem.getBetName())) {
//                if(isBigDouble(i + j + k)){
//                    betNum = 2;
//                }
//            } else if ("龙大".equals(betItem.getBetName())) {
//                if(allNum > 19){
//                    betNum = 2;
//                }
//            } else if ("龙小".equals(betItem.getBetName())) {
//                if (allNum < 8){
//                    betNum = 2;
//                }
//            } else if ("龙".equals(betItem.getBetName())) {
//
//            } else if ("小龙小双".equals(betItem.getBetName())) {
//
//            } else if ("全大".equals(betItem.getBetName())) {
//                if(isBigNum(i) && isBigNum(j) && isBigNum(k)){
//                    betNum = 2;
//                }
//            } else if ("全小".equals(betItem.getBetName())) {
//                if(!isBigNum(i) && !isBigNum(j) && !isBigNum(k)){
//                    betNum = 2;
//                }
//            }else if("全单".equals(betItem.getBetName())){
//                if(!isBigDouble(i) && !isBigDouble(j) && !isBigDouble(k)){
//                    betNum = 2;
//                }
//            }else if("全双".equals(betItem.getBetName())){
//                if(isBigDouble(i) && isBigDouble(j) && isBigDouble(k)){
//                    betNum = 2;
//                }
//            }else if("顺子".equals(betItem.getBetName())){
//                if(isShunzi(i,j,k)){
//                    betNum = 20;
//                }
//            }
//            betItem.setReturnMoney(betItem.getBetMoney() * betNum);
//            newBetList.add(betItem);
//        }
//        return newBetList;
//    }
}
