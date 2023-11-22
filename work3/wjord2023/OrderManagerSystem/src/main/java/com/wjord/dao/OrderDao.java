package com.wjord.dao;

import com.wjord.info.Order;

import java.util.List;

public interface OrderDao {
    void insertOrder(Order order);

    void deleteOrderByCode(String orderCode);

    void updateOrderByCode(String orderCode, Order order);

    Order selectOrderByCode(String orderCode);

    List<Order> selectAllOrders();

    int selectTotalOrderCount();

    int selectTotalOrderCountByProductCode(String productCode);

    int selectTotalOrderCountByProductNmae(String productName);

    int selectTotalOrderCountByProductName(String productName);

    int selectTotalOrderCountByBuyerPhone(String buyerPhone);


    List<Order> selectOrdersByProductCode(String productCode);

    List<Order> selectOrdersByBuyerPhone(String buyerPhone);

    void deleteOrderByProductCode(String productCode);

    void deleteOrderByBuyerPhone(String buyerPhone);

    List<Order> sortOrderByPrice();

    List<Order> sortOrderByUpdateTime();

}
