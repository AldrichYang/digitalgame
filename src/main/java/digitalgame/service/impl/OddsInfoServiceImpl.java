package digitalgame.service.impl;

import digitalgame.dao.BetResultMapper;
import digitalgame.dao.OddsInfoMapper;
import digitalgame.model.po.BetInfo;
import digitalgame.model.po.BetResult;
import digitalgame.model.po.OddsInfo;
import digitalgame.model.po.UserBetInfo;
import digitalgame.service.OddsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class OddsInfoServiceImpl implements OddsInfoService{

    @Autowired
    private OddsInfoMapper oddsInfoMapper;

    @Autowired
    private BetResultMapper betResultMapper;

    @Override
    public OddsInfo saveOddsInfo(OddsInfo oddsInfo) {
        oddsInfo.setCreateTime(new Date().toString());
        oddsInfoMapper.insert(oddsInfo);
        return oddsInfo;
    }

    @Override
    public List<OddsInfo> selectOddsList() {
        return oddsInfoMapper.selectOddsList();
    }

    @Override
    public OddsInfo editOddsInfo(OddsInfo oddsInfo) {
        oddsInfoMapper.updateByPrimaryKeySelective(oddsInfo);
        return oddsInfo;
    }

    @Override
    public OddsInfo selectOddsInfo(int oddsInfoID) {
        return oddsInfoMapper.selectByPrimaryKey(oddsInfoID);
    }

    @Override
    public int deleteOddsInfo(int oddsInfoID) {
        return oddsInfoMapper.deleteByPrimaryKey(oddsInfoID);
    }

    @Override
    public int addOddsInfo(OddsInfo oddsInfo) {
        return oddsInfoMapper.insert(oddsInfo);
    }

    @Override
    public HashMap<String, Integer> selectOddsMap() {
        HashMap<String, Integer> oddsMap = new HashMap();
        List<OddsInfo> oddsList = oddsInfoMapper.selectOddsList();

        System.out.println(oddsList.size());
        if(oddsList != null && oddsList.size() > 0){
            for(OddsInfo oddsInfo : oddsList){
                oddsMap.put(oddsInfo.getOddsName(),oddsInfo.getOddsNumber());
            }
        }
        return oddsMap;
    }

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
        if(allNum > 12){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断是否为龙大
     * @param i
     * @param j
     * @param k
     * @return
     */
    private boolean isLongda(int i, int j ,int k){
        if((i == 5 || i == 6) && j == 7 && k == 8){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 判断是否为龙小
     * @param i
     * @param j
     * @param k
     * @return
     */
    private boolean isLongxiao(int i, int j ,int k){
        if((k == 3 || k == 4) && j == 2 && i == 1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 开奖结果处理方法
     * @param i 个位数
     * @param j 十位数
     * @param k 百位数
     * @param userBetInfoList  参加开奖人信息
     * @param resultDate 开奖期数
     * @return
     */
    @Override
    public List<UserBetInfo> oddsNumber(int i, int j, int k, List<UserBetInfo> userBetInfoList, String resultDate){

        HashMap<String, Integer> oddsMap = this.selectOddsMap();
        int allNum = i + j + k;
        if(null == userBetInfoList){
            return null;
        }else{
            for (UserBetInfo userBetInfo : userBetInfoList){
                List<BetInfo> betInfoList = userBetInfo.getBetInfoList();
                if(null == betInfoList || betInfoList.size() == 0){
                    break;
                }
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
                    } else if ("十大".equals(betInfo.getBetitem())) {
                        if(isBigNum(j)){
                            betNum = result;
                        }
                    }else if ("十小".equals(betInfo.getBetitem())) {
                        if(!isBigNum(j)){
                            betNum = result;
                        }
                    } else if ("十单".equals(betInfo.getBetitem())) {
                        if(!isBigDouble(j)){
                            betNum = result;
                        }
                    } else if ("十双".equals(betInfo.getBetitem())) {
                        if(isBigDouble(j)){
                            betNum = result;
                        }
                    }else if ("个大".equals(betInfo.getBetitem())) {
                        if(isBigNum(i)){
                            betNum = result;
                        }
                    }else if ("个小".equals(betInfo.getBetitem())) {
                        if(!isBigNum(i)){
                            betNum = result;
                        }
                    } else if ("个单".equals(betInfo.getBetitem())) {
                        if(!isBigDouble(i)){
                            betNum = result;
                        }
                    } else if ("个双".equals(betInfo.getBetitem())) {
                        if(isBigDouble(i)){
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
                        if(isLongda(i, j, k)){
                            betNum = result;
                        }
                    } else if ("龙小".equals(betInfo.getBetitem())) {
                        if (isLongxiao(i, j, k)){
                            betNum = result;
                        }
                    } else if ("龙".equals(betInfo.getBetitem())) {
                        if(isLongda(i, j, k) || isLongxiao(i, j, k)){
                            betNum = result;
                        }
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
                    betResult.setBetdate(i + "," + j + "," + k);
                    betResult.setResultdate(resultDate);
                    betResultMapper.insert(betResult);
                }
            }
        }
        return userBetInfoList;
    }
}
