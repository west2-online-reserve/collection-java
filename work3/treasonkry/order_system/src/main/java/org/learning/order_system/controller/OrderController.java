package org.learning.order_system.controller;

import org.learning.order_system.pojo.Order;
import org.learning.order_system.seivice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping
    List<Order> getAllOrders(){
        return orderService.selectAllOrders();
    }
}
