package digitalgame.model.po;

public class OddsInfo {
    private Integer id;

    private String oddsName;

    private String createTime;

    private String updateTime;

    private Double oddsNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOddsName() {
        return oddsName;
    }

    public void setOddsName(String oddsName) {
        this.oddsName = oddsName == null ? null : oddsName.trim();
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

    public Double getOddsNumber() {
        return oddsNumber;
    }

    public void setOddsNumber(Double oddsNumber) {
        this.oddsNumber = oddsNumber;
    }
}