package digitalgame.model.po;

import java.util.Date;

/**
 * 开奖的实体类
 * @author simon
 * @version 1.0 create@20171020
 */
public class OpenInfo {

    private int id;
    private int openNo; //开奖号码
    private Date openTime; //开奖时间
    private Date createTime; //创建时间
    private Date updateTime; //修改时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOpenNo() {
        return openNo;
    }

    public void setOpenNo(int openNo) {
        this.openNo = openNo;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
