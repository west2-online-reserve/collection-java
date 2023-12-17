package base.domain;
/**
 * 注：ordertime 无需放入javabean中操作
 * @author 1293978818
 */
public class TotalOrder {
    private int orderId;
    private double totalPrice;
    private long orderTime;

    public TotalOrder(){

    }
    
    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public TotalOrder(int orderId,long orderTime,double totalPrice){
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "TotalOrder [orderId=" + orderId + ", totalPrice=" + totalPrice + "]";
    }

    
    
}
