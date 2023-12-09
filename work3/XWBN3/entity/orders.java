package XWBN3.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * @Author：XWBN
 * @Package：XWBN3.entity
 * @Project：MyCodes
 * @name：orders
 * @Date：2023/12/1 20:45
 * @Filename：orders
 */

public class orders implements Serializable {
    private int orderId;
    private String orderTime;
    private double totalPrice;

    public orders(){}

    public orders(int order_id,String order_time,double totalPrice){
        this.orderId = order_id;
        this.orderTime = order_time;
        this.totalPrice = totalPrice;

    }


    public  int getOrderId() {
        return orderId;
    }

    public void setOrderId(int order_id) {
        this.orderId = order_id;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String order_time) {
        this.orderTime = order_time;
    }


    public double getToatalPrice() {
        return totalPrice;
    }

    public void setToatalPrice(double toatalPrice) {
        this.totalPrice = toatalPrice;
    }

    @Override
    public String toString(){
        return "[订单编号]:"+orderId+" [下单时间]:"+orderTime+" [订单价格]:"+totalPrice;
    }
}
