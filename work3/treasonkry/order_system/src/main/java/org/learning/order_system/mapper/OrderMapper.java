package org.learning.order_system.mapper;

import org.apache.ibatis.annotations.*;
import org.learning.order_system.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Mapper
public interface OrderMapper {

    @Select("select order_amount, create_at, update_at from `order`,order_item where `order`.order_id=order_item.order_id")
    List<Order> getOrders();
    @Delete("delete from `order` where order_id=#{orderId}")
    void deleteOrder(Integer orderId);
    @Update("update `order` set order_amount=#{orderAmount},create_at=#{createAt},update_at=#{updateAt},create_at=#{createAt} where order_id=#{orderId}")
    void updateOrder(Order order);
    @Insert("insert into `order` (order_amount, create_at, update_at) VALUES (#{orderAmount},#{createAt},#{updateAt})")
    @Options(useGeneratedKeys = true, keyProperty = "orderId", keyColumn = "order_id")
    void insertOrder(Order order);
    @Select("select order_amount, create_at, update_at from `order` where order_id=#{orderId}")
    Order selectOrder(Integer orderId);

}
