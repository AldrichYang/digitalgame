package digitalgame.model.po;

/**
 *
 *  下注的实体类
 *  @author simon
 *  @version 1.0 create 2017-10-08
 *
 */
public class BetInfo {
  private Long id;
  private long openNo; //开奖期数
  private int userId; // 投注人Id
  private String betman; //投注人
  private String betitem; //投注项
  private Double betmoney; //投注金额
  private Double returnMoney; //返奖金额（中奖数）
  private Long status; //投注状态 1投注成功 -1已撤销 3投注失败
  private String createTime; //投注时间

  public long getOpenNo() {
    return openNo;
  }

  public void setOpenNo(long openNo) {
    this.openNo = openNo;
  }

  public Double getReturnMoney() {
    return returnMoney;
  }

  public void setReturnMoney(Double returnMoney) {
    this.returnMoney = returnMoney;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBetman() {
    return betman;
  }

  public void setBetman(String betman) {
    this.betman = betman;
  }

  public String getBetitem() {
    return betitem;
  }

  public void setBetitem(String betitem) {
    this.betitem = betitem;
  }

  public Double getBetmoney() {
    return betmoney;
  }

  public void setBetmoney(Double betmoney) {
    this.betmoney = betmoney;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }

  private String updateTime;

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }
}
