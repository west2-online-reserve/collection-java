package 订单;

import java.util.List;

public class Order {
    private int orderId;
    private List<Product> products;
    private String orderTime;
    private double orderPrice;

    public Order(int orderId, List<Product> products, String orderTime, double orderPrice) {
        this.orderId = orderId;
        this.products = products;
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
    }

    public Order() {
    }

    /**
     * 获取
     * @return orderId
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * 设置
     * @param orderId
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取
     * @return products
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * 设置
     * @param products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * 获取
     * @return orderTime
     */
    public String getOrderTime() {
        return orderTime;
    }

    /**
     * 设置
     * @param orderTime
     */
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 获取
     * @return orderPrice
     */
    public double getOrderPrice() {
        return orderPrice;
    }

    /**
     * 设置
     * @param orderPrice
     */
    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }




}
