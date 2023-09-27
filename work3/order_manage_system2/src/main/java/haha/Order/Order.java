package com.haha.Order;

import java.time.LocalDateTime;
import java.util.Date;

public class Order {
    int orderID;
    LocalDateTime orderTime;
    String goodsName;

    int num;//当前订单购买的商品数量
    int restNum;//当前购买的商品库存数量
    double profit;
    String manager;

    public Order(int orderID, LocalDateTime orderTime, String goodsName, int num, double profit, String manager) {
        this.orderID = orderID;
        this.orderTime = orderTime;
        this.goodsName = goodsName;
        this.num = num;
        this.profit = profit;
        this.manager = manager;
    }

    public Order() {
    }



    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}
