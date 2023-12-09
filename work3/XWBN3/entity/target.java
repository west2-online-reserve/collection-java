package XWBN3.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * @Author：XWBN
 * @Package：XWBN3.entity
 * @Project：MyCodes
 * @name：selectAll
 * @Date：2023/12/1 21:06
 * @Filename：selectAll
 */
public class target implements Serializable {
    private int commoditylinkorderId;
    private int orderId;
    private int commodityId;
    private String commodityName;
    private int commodityNum;
    private String orderTime;
    private double orderPrice;

    private double totalPrice;

    public target(){}
    public target(int commoditylinkorder_id,int order_id,int commodity_id,String commodity_name,int commodity_num,String order_time,double order_price,double totalPrice){
        this.commoditylinkorderId = commoditylinkorder_id;
        this.orderId = order_id;
        this.commodityId = commodity_id;
        this.commodityName = commodity_name;
        this.commodityNum = commodity_num;
        this.orderTime = order_time;
        this.orderPrice = order_price;
        this.totalPrice = totalPrice;
    }

    public int getCommodity_id() {
        return commodityId;
    }

    public void setCommodity_id(int commodity_id) {
        this.commodityId = commodity_id;
    }

    public String getCommodity_name() {
        return commodityName;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodityName = commodity_name;
    }

    public int getCommodity_num() {
        return commodityNum;
    }

    public void setCommodity_num(int commodity_num) {
        this.commodityNum = commodity_num;
    }

    public int getCommoditylinkorder_id() {
        return commoditylinkorderId;
    }

    public void setCommoditylinkorder_id(int commoditylinkorder_id) {
        this.commoditylinkorderId = commoditylinkorder_id;
    }

    public int getOrder_id() {
        return orderId;
    }

    public void setOrder_id(int order_id) {
        this.orderId = order_id;
    }

    public double getOrder_price() {
        return orderPrice;
    }

    public void setOrder_price(double order_price) {
        this.orderPrice = order_price;
    }

    public String getOrder_time() {
        return orderTime;
    }

    public void setOrder_time(String order_time) {
        this.orderTime = order_time;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString(){
        return "[商品关联订单表编号]:"+commoditylinkorderId+" [订单编号]:"+orderId+" [商品编号]:"+commodityId+" [商品名称]:"+commodityName+" [购买数量]:"+commodityNum+" [下单时间]:"+orderTime+" [订单价格]:"+orderPrice +" [订单总价格]:" +totalPrice;
    }
}
