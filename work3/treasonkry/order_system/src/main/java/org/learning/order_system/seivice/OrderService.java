package org.learning.order_system.seivice;

import org.learning.order_system.pojo.Order;
import org.learning.order_system.pojo.OrderRequest;

import java.util.List;

public interface OrderService {
    void insertOrder(OrderRequest orderRequest);
    void deleteOrder(Integer orderId);
    void updateOrder(Order order);
    Order selectOrder(Integer orderId);
    List<Order> selectAllOrders();
}
