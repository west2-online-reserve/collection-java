package com.wjord;

import com.wjord.config.Config;
import com.wjord.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;

@Component
@SpringJUnitConfig(classes = Config.class)
public class TestOrderService {
    @Autowired
    private OrderService orderService;

    @Test
    public void testInsertOrder() {
        orderService.insertOrder("反甲", "15344459810", 1, BigDecimal.valueOf(1850));
        orderService.insertOrder("饮血剑", "18896898798", 6, BigDecimal.valueOf(2405 * 6));
        orderService.insertOrder("帽子", "15344459810", 2, BigDecimal.valueOf(23455 * 2));
        orderService.insertOrder("狂徒铠甲", "15234356709", 3, BigDecimal.valueOf(22445 * 3));
        orderService.insertOrder("帽子", "15344466666", 5, BigDecimal.valueOf(12405 * 5));
    }

    @Test
    public void testDeleteOrder() {
        orderService.deleteOrder("004529491470");
    }

    @Test
    public void testUpdateOrder() {
        orderService.updateOrder("615345748105", "反甲", "18896898798", 2, BigDecimal.valueOf(1840 * 2));
    }

    @Test
    public void testSelectOrder() {
        System.out.println(orderService.selectOrder("694489692889"));
    }

    @Test
    public void testSelectAllOrders() {
        orderService.selectAllOrders().forEach(System.out::println);
    }

    @Test
    public void testSelectTotalOrderCount() {
        System.out.println(orderService.selectTotalOrderCount());
    }

    @Test
    public void testSelectTotalOrderCountByProductName() {
        System.out.println(orderService.selectTotalOrderCountByProductName("帽子"));
    }

    @Test
    public void testSelectTotalOrderCountByBuyerPhone() {
        System.out.println(orderService.selectTotalOrderCountByBuyerPhone("18896898798"));
    }

    @Test
    public void testSelectTotalOrderCountByProductCode() {
        System.out.println(orderService.selectTotalOrderCountByProductCode("2020052012345"));
    }

    @Test
    public void testSelectOrdersByProductName() {
        System.out.println(orderService.selectOrdersByProductName("帽子"));
    }

    @Test
    public void testSelectOrdersByBuyerPhone() {
        System.out.println(orderService.selectOrdersByBuyerPhone("18896898798"));
    }

    @Test
    public void testSelectOrdersByProductCode() {
        System.out.println(orderService.selectOrdersByProductCode("2020052012345"));
    }

    @Test
    public void testSelectSortedOrderByPrice() {
        System.out.println(orderService.selectSortedOrderByPrice());
    }

    @Test
    public void testSelectSortedOrderByUpdateTime() {
        System.out.println(orderService.selectSortedOrderByUpdateTime());
    }
}
