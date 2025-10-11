package Y.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private int id;
    private int orderCode;
    private String userName;
    private double orderPrice;
    private LocalDateTime orderDate;
    private ArrayList<Product> products;

    public Order(int orderCode,String userName,ArrayList<Product> products,LocalDateTime orderTime){
        this.orderCode = orderCode;
        this.userName = userName;
        this.orderDate = orderTime;
        this.products = products;
        double sum=0;
        for (Product p:products){
            sum+=p.getProductPrice();
        }
        this.orderPrice=sum;
    }
    // getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getOrderCode() {
        return orderCode;
    }
    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }
    public String getUsername() {
        return userName;
    }
    public void setUsername(String username) {
        this.userName = username;
    }
    public double getOrderPrice() {
        return orderPrice;
    }
    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
    public LocalDateTime getOrderTime() {
        return orderDate;
    }
    public void setOrderTime(LocalDateTime orderTime) {
        this.orderDate = orderTime;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public void setProducts(ArrayList<Product> products) {
        this.products.addAll(products);
    }

    @Override
    public String toString() {
        return "Order ["+ "orderCode=" + orderCode + ", username=" + userName + ", orderPrice=" + orderPrice + ", orderTime=" + orderDate + "]";
    }
}
