package XWBN3.entity;

import java.io.Serializable;

/**
 * @Author：XWBN
 * @Package：XWBN3.entity
 * @Project：MyCodes
 * @name：commoditylinkorder
 * @Date：2023/12/1 19:04
 * @Filename：commoditylinkorder
 */
public class commoditylinkorder implements Serializable {
    private int commoditylinkorderId;
    private int commodityId;
    private int orderId;
    private int commodityNum;
    private double orderPrice;


    public commoditylinkorder(){}

    public commoditylinkorder(int commoditylinkorder_id,int commodity_id,int order_id,int commodity_num,double order_price){
        this.commoditylinkorderId = commoditylinkorder_id;
        this.commodityId = commodity_id;
        this.orderId = order_id;
        this.commodityNum = commodity_num;
        this.orderPrice = order_price;
    }

    public int getCommoditylinkorderId() {
        return commoditylinkorderId;
    }

    public void setCommoditylinkorderId(int commoditylinkorder_id) {
        this.commoditylinkorderId = commoditylinkorder_id;
    }

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodity_id) {
        this.commodityId = commodity_id;
    }

    public int getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(int commodity_num) {
        this.commodityNum = commodity_num;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int order_id) {
        this.orderId = order_id;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double order_price) {
        this.orderPrice = order_price;
    }



    @Override
    public String toString(){
        return "[商品关联订单表编号]:"+commoditylinkorderId+" [订单编号]："+orderId+" [商品编号]:"+commodityId+" [商品数量]:"+commodityNum+" [订单价格]:"+orderPrice;
    }

}
