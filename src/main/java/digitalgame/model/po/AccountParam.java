package digitalgame.model.po;

public class AccountParam {

    /****
     * 期数
     */
    private  String periods;

    /****
     * 订单号
     */
    private String orderId;

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
