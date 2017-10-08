package digitalgame.model.po;

/**
 * 投注项
 * @author simon
 * @version 1.0 creat@2017/10/08
 */
public class BetItem{
    public String getBetName() {
        return betName;
    }

    public void setBetName(String betName) {
        this.betName = betName;
    }

    /**
     * 投注项
     */
    private String betName;

    /**
     * 投注金额
     */
    private double betMoney = 0.0;

    /**
     * 中奖金额
     */
    private double returnMoney = 0.0;



    public double getBetMoney() {
        return betMoney;
    }

    public void setBetMoney(double betMoney) {
        this.betMoney = betMoney;
    }

    public double getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(double returnMoney) {
        this.returnMoney = returnMoney;
    }
}
