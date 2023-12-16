package com.west2.work3;

import java.sql.Time;

import java.time.LocalDate;

public class OrderForm {
    // 订单编号
    private int no;
    // 商品信息
    private Goods goods;
    // 下单时间
    private LocalDate ordertime;
    // 订单价格
    private double price;

    public OrderForm() {}

    public OrderForm(int no, Goods goods, LocalDate ordertime, double price) {
        this.no = no;
        this.goods = goods;
        this.ordertime = ordertime;
        this.price = price;
    }

    /**
     * 获取订单编号
     * @return no
     */
    public int getNo() {
        return no;
    }

    /**
     * 设置订单编号
     * @param no
     */
    public void setNo(int no) {
        this.no = no;
    }

    /**
     * 获取商品信息
     * @return goods
     */
    public Goods getGoods() {
        return goods;
    }

    /**
     * 设置商品信息
     * @param goods
     */
    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    /**
     * 获取下单时间
     * @return ordertime
     */
    public LocalDate getOrdertime() {
        return ordertime;
    }

    /**
     * 设置下单时间
     * @param ordertime
     */
    public void setOrdertime(LocalDate ordertime) {
        this.ordertime = ordertime;
    }

    /**
     * 获取订单价格
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置订单价格
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        return "订单编号 : " + no + "\n"+goods.toString()+"\n下单时间 : " + ordertime + "\n订单价格 : " + price;
    }
}
