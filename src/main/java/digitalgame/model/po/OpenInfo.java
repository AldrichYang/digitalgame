package digitalgame.model.po;

import java.util.Date;

/**
 * 开奖的实体类
 * @author simon
 * @version 1.0 create@20171020
 */
public class OpenInfo {

    private int id;
    private long openNo; //开奖期数
    private String openNum; //开奖号码
    private String openResult; //开奖结果
    private String openTime; //开奖时间
    private String createTime; //创建时间
    private String updateTime; //修改时间


    public String getOpenResult() {
        return openResult;
    }

    public void setOpenResult(String openResult) {
        this.openResult = openResult;
    }

    public int getId() {
        return id;
    }

    public String getOpenNum() {
        return openNum;
    }

    public void setOpenNum(String openNum) {
        this.openNum = openNum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getOpenNo() {
        return openNo;
    }

    public void setOpenNo(long openNo) {
        this.openNo = openNo;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
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
}
