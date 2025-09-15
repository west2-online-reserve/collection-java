package org.learning.order_system;

import org.junit.jupiter.api.Test;
import org.learning.order_system.mapper.ProductMapper;
import org.learning.order_system.pojo.OrderRequest;
import org.learning.order_system.pojo.Product;
import org.learning.order_system.seivice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class OrderSystemApplicationTests {
    @Autowired
    ProductMapper productMapper;
    @Autowired
    OrderService orderService;
    @Test
    void insertTest() {
        Product product = new Product();
        product.setProductName("sedfs");
        product.setProductPrice(100.0);
        product.setIsDeleted(0);
        product.setQuantity(1);
        productMapper.insertProduct(product);
    }
    @Test
    void createOrderTest(){
        OrderRequest orderRequest = new OrderRequest();
        List<OrderRequest.OrderItemRequest> orderItemRequests = new ArrayList<>();
        orderItemRequests.add(new OrderRequest.OrderItemRequest(32,1));
        orderItemRequests.add(new OrderRequest.OrderItemRequest(34,4));
        orderRequest.setOrderItemRequests(orderItemRequests);
        orderService.insertOrder(orderRequest);
    }
    @Test
    void createOrderTestWithError(){
        //异常抛出测试
        OrderRequest orderRequest = new OrderRequest();
        List<OrderRequest.OrderItemRequest> orderItemRequests = new ArrayList<>();
        orderItemRequests.add(new OrderRequest.OrderItemRequest(32,1));
        orderItemRequests.add(new OrderRequest.OrderItemRequest(1,4));
        orderRequest.setOrderItemRequests(orderItemRequests);
        orderService.insertOrder(orderRequest);
    }
    @Test
    void displayOrderTest(){
        System.out.println(orderService.selectAllOrders());
    }
}
