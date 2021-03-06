package digitalgame.model.po;

public class SystemFinanceAccountReport {
    private Integer id;

    private String reportDate;

    private Double bettingMoney;

    private Double winningMoney;

    private Double platformMoney;

    private Double platformLossMoney;

    private String group;

    private String createTime;

    private String updateTime;

    public Double getPlatformLossMoney() {
        if(platformLossMoney == null) return 0.0;
        return platformLossMoney;
    }

    public void setPlatformLossMoney(Double platformLossMoney) {
        this.platformLossMoney = platformLossMoney;
    }

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
        if(bettingMoney == null) bettingMoney = 0.0;
        return bettingMoney;
    }

    public void setBettingMoney(Double bettingMoney) {
        this.bettingMoney = bettingMoney;
    }

    public Double getWinningMoney() {
        if(winningMoney == null) winningMoney = 0.0;
        return winningMoney;
    }

    public void setWinningMoney(Double winningMoney) {
        this.winningMoney = winningMoney;
    }

    public Double getPlatformMoney() {
        if(platformMoney == null) platformMoney = 0.0;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
