package org.learning.order_system.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.learning.order_system.pojo.OrderItem;

import java.util.List;
@Mapper
public interface OrderItemMapper {
    @Select("select * from order_item")
    public List<OrderItem>  getOrderItems();
    @Insert("insert into order_item (item_id, order_id, product_id, quantity, item_price, subtotal) VALUES (#{itemId},#{orderId},#{productId},#{quantity},#{itemPrice},#{subtotal})")
    public void insertOrderItem(OrderItem orderItem);
    @Delete("delete from order_item where item_id=#{itemId}")
    public void deleteOrderItem(Integer itemId);
    @Select("select * from order_item where item_id=#{itemId}")
    public OrderItem getOrderItemById(Integer itemId);
}
