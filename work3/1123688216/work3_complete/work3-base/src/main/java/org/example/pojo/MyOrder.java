package org.example.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MyOrder {
    protected Integer id;
    protected List<Commodity> commodities;
    protected LocalDateTime orderTime;
    protected double price;
    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }


    public MyOrder(){}

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        // 1. 处理商品列表的显示
        StringBuilder sb = new StringBuilder();
        if (commodities != null && !commodities.isEmpty()) {
            for (Commodity c : commodities) {
                // 这里假设 Commodity 类也有一个不错的 toString，或者直接打印其属性
                sb.append("\n    - ").append(c.getName())
                        .append(" (￥").append(c.getPrice()).append(")")
                        .append(" x ").append(c.getCount());
            }
        } else {
            sb.append(" [暂无商品]");
        }
        // 2. 拼接整体格式
        return "============================\n" +
                "【订单详情】\n" +
                " 订单编号: " + id + "\n" +
                " 下单时间: " + (orderTime != null ? orderTime.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) : "未知") + "\n" +
                " 订单总价: ￥" + String.format("%.2f", price) + "\n" +
                " 商品清单: " + sb.toString() + "\n" +
                "============================"+
                "\n";
    }


}
