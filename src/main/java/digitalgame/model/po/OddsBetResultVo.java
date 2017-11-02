package digitalgame.model.po;

public class OddsBetResultVo {

    private String betUser;
    private int resultNumber;
    private String resultDate;
    private int betNumber;

    public String getBetUser() {
        return betUser;
    }

    public void setBetUser(String betUser) {
        this.betUser = betUser;
    }

    public String getResultDate() {
        return resultDate;
    }

    public void setResultDate(String resultDate) {
        this.resultDate = resultDate;
    }

    public int getResultNumber() {
        return resultNumber;

    }

    public void setResultNumber(int resultNumber) {
        this.resultNumber = resultNumber;
    }

    public int getBetNumber() {
        return betNumber;
    }

    public void setBetNumber(int betNumber) {
        this.betNumber = betNumber;
    }
}
