package base.domain;
/**
 * @author 1293978818
 */
public class OrderGood {

    
    private int orderId;
    private int goodId;
    private int goodNum;
    private double goodPrice;

    public OrderGood() {

    }

    public OrderGood(int orderId,int goodId,int goodNum,double goodPrice){
        this.goodId = goodId;
        this.goodNum = goodNum;
        this.goodPrice = goodPrice;
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public int getGoodNum() {
        return goodNum;
    }

    public void setGoodNum(int goodNum) {
        this.goodNum = goodNum;
    }

    public double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(double goodPrice) {
        this.goodPrice = goodPrice;
    }
}
