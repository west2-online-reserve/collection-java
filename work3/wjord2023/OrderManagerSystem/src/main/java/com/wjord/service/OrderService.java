package com.wjord.service;

import com.wjord.info.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    void insertOrder(String productName, String buyerPhone, int orderAmount, BigDecimal orderPrice);

    void updateOrder(String orderCode, String productName, String buyerPhone, int orderAmount, BigDecimal orderPrice);

    void deleteOrder(String orderCode);

    Order selectOrder(String orderCode);

    List<Order> selectAllOrders();

    int selectTotalOrderCount();

    int selectTotalOrderCountByProductCode(String productCode);

    int selectTotalOrderCountByProductName(String productName);

    int selectTotalOrderCountByBuyerPhone(String buyerPhone);

    List<Order> selectOrdersByProductName(String productName);

    List<Order> selectOrdersByProductCode(String productCode);

    List<Order> selectOrdersByBuyerPhone(String buyerPhone);

    List<Order> selectSortedOrderByPrice();

    List<Order> selectSortedOrderByUpdateTime();
}
