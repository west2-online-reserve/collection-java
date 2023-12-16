package com.chenyyy111.pojo;

import com.sun.xml.internal.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private int orderId;
    private List<Product> products;
    private LocalDateTime orderTime;
    private double orderPrice;

    public Order() {
    }

    public Order(int orderId, LocalDateTime orderTime, double orderPrice){
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
    }

    public Order(LocalDateTime orderTime, double orderPrice){
        this.orderTime = orderTime;
        this.orderPrice = orderPrice;
    }

    // 计算订单价格
    public void calculateOrderPrice() {
        double totalPrice = 0.0;
        for (Product product : products) {
            totalPrice += product.getProductPrice();
        }
        this.orderPrice = totalPrice;
    }
}

