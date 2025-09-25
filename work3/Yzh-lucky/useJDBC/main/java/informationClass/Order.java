package informationClass;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private String order_code;
    private double order_price;
    private LocalDateTime order_date;
    private String username;
    private ArrayList<Product> products;

    public Order(){}
    public Order(String order_code,LocalDateTime order_date,String username, ArrayList<Product> products){
        double sum = 0;
        this.order_code = order_code;
        for (Product p : products){
            sum += p.getProduct_price();
        }
        this.order_price = sum;
        this.order_date = order_date;
        this.username = username;
        this.products = products;
    }

    public void setOrder_code(String s) {
        order_code = s;
    }
    public void setUsername(String s){
        username = s;
    }
    public void setOrder_price(double s){
        order_price = s;
    }
    public void setOrder_date(LocalDateTime s){
        order_date = s;
    }

    //get
    public String getOrder_code(){
        return order_code;
    }
    public String getUsername(){
        return username;
    }
    public double getOrder_price(){
        return order_price;
    }
    public LocalDateTime getOrder_date(){
        return order_date;
    }
    public ArrayList<Product> getProducts(){
        return products;
    }

    public String toString(){
        return "Order Code: " + order_code  + "\n" + "Order Price: " + order_price + "\n" + "Order Date: " + order_date;
    }
}
