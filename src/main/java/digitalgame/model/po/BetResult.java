package digitalgame.model.po;

public class BetResult {
    private Integer id;

    private String betuser;

    private Double betnumber;

    private String bettype;

    private String betdate;

    private String resultdate;

    private String createdate;

    private String updatedate;

    private Integer betuserid;

    private Double resultnumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBetuser() {
        return betuser;
    }

    public void setBetuser(String betuser) {
        this.betuser = betuser == null ? null : betuser.trim();
    }

    public Double getBetnumber() {
        return betnumber;
    }

    public void setBetnumber(Double betnumber) {
        this.betnumber = betnumber;
    }

    public String getBettype() {
        return bettype;
    }

    public void setBettype(String bettype) {
        this.bettype = bettype == null ? null : bettype.trim();
    }

    public String getBetdate() {
        return betdate;
    }

    public void setBetdate(String betdate) {
        this.betdate = betdate == null ? null : betdate.trim();
    }

    public String getResultdate() {
        return resultdate;
    }

    public void setResultdate(String resultdate) {
        this.resultdate = resultdate == null ? null : resultdate.trim();
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate == null ? null : createdate.trim();
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate == null ? null : updatedate.trim();
    }

    public Integer getBetuserid() {
        return betuserid;
    }

    public void setBetuserid(Integer betuserid) {
        this.betuserid = betuserid;
    }

    public Double getResultnumber() {
        return resultnumber;
    }

    public void setResultnumber(Double resultnumber) {
        this.resultnumber = resultnumber;
    }
}