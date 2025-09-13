package org.learning.order_system.seivice.impl;

import org.learning.order_system.mapper.OrderItemMapper;
import org.learning.order_system.mapper.OrderMapper;
import org.learning.order_system.mapper.ProductMapper;
import org.learning.order_system.pojo.Order;
import org.learning.order_system.pojo.OrderItem;
import org.learning.order_system.pojo.OrderRequest;
import org.learning.order_system.pojo.Product;
import org.learning.order_system.seivice.OrderService;
import org.learning.order_system.seivice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Order selectOrder(Integer orderId){
        return orderMapper.selectOrder(orderId);
    }
    @Override
    public List<Order> selectAllOrders(){
        return orderMapper.getOrders();
    }
    @Override
    public void deleteOrder(Integer orderId){
        orderMapper.deleteOrder(orderId);
    }
    @Override
    public void updateOrder(Order order){
        orderMapper.updateOrder(order);
    }
    @Override
    @Transactional
    public void insertOrder(OrderRequest orderRequest){
        Order order = new Order();
        double amount = 0;
        //创建订单
        order.setOrderAmount(0.0);
        order.setUpdateAt(LocalDateTime.now());
        order.setCreateAt(LocalDateTime.now());
        orderMapper.insertOrder(order);
        Integer orderId=order.getOrderId();
        try {
            for(OrderRequest.OrderItemRequest orderItemRequest:orderRequest.getOrderItemRequests()){
                if(checkQuantity(orderItemRequest)==false){
                    orderMapper.deleteOrder(orderId);
                    throw new RuntimeException("库存不足");
                }else{
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrderId(orderId);
                    orderItem.setQuantity(orderItemRequest.getQuantity());
                    orderItem.setProductId(orderItemRequest.getProductId());
                    orderItem.setItemPrice(productMapper.getProduct(orderItemRequest.getProductId()).getProductPrice());
                    orderItem.setSubtotal(orderItem.getQuantity()*orderItem.getItemPrice());
                    //插入订单项
                    orderItemMapper.insertOrderItem(orderItem);
                    amount += orderItem.getQuantity()*orderItem.getItemPrice();
                    //更新商品信息
                    productMapper.reduceQuantity(orderItem.getProductId(), orderItem.getQuantity());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("订单创建失败");
        }
        order.setOrderAmount(amount);
        orderMapper.updateOrder(order);
    }
    private boolean checkQuantity(OrderRequest.OrderItemRequest orderItemRequest){
        Integer buyQuantity=orderItemRequest.getQuantity();
        Product product=productMapper.getProduct(orderItemRequest.getProductId());
        Integer stockQuantity=product.getQuantity();
        if(stockQuantity<buyQuantity){
            return false;
        }else {
            return true;
        }
    }
}
