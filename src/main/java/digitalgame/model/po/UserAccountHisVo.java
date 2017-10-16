package digitalgame.model.po;

public class UserAccountHisVo {

    private String userName ;

    private String nickName;

    private String opertType;

    private double money;

    private double balance;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOpertType() {
        return opertType;
    }

    public void setOpertType(String opertType) {
        this.opertType = opertType;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
