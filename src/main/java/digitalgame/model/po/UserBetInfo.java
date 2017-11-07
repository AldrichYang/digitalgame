package digitalgame.model.po;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户投注信息
 * @version  1.0 create@2017/10/11
 * @author simon
 */
public class UserBetInfo {
    String userName; //昵称
    List<BetInfo> betInfoList; //投注信息
    double userBalance; //用户账户余额
    double betSum; //投注总额
    double returnSum; //中奖总额

    public double getReturnSum() {
        return returnSum;
    }

    public void setReturnSum(double returnSum) {
        this.returnSum = returnSum;
    }

    public double getBetSum() {
        return betSum;
    }

    public void setBetSum(double betSum) {
        this.betSum = betSum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<BetInfo> getBetInfoList() {
        if(betInfoList == null)
            betInfoList = new ArrayList<>();
        return betInfoList;
    }

    public void setBetInfoList(List<BetInfo> betInfoList) {
        this.betInfoList = betInfoList;
    }

    public double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(double userBalance) {
        this.userBalance = userBalance;
    }

}
