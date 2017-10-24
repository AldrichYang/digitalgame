package digitalgame.model.po;

public class SystemFinanceAccountReport {
    private Integer id;

    private String reportDate;

    private Double bettingMoney;

    private Double winningMoney;

    private Double platformMoney;

    private String createTime;

    private String updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate == null ? null : reportDate.trim();
    }

    public Double getBettingMoney() {
        return bettingMoney;
    }

    public void setBettingMoney(Double bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public Double getWinningMoney() {
        return winningMoney;
    }

    public void setWinningMoney(Double winningMoney) {
        this.winningMoney = winningMoney;
    }

    public Double getPlatformMoney() {
        return platformMoney;
    }

    public void setPlatformMoney(Double platformMoney) {
        this.platformMoney = platformMoney;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }
}