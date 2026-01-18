package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.pojo.MyOrder;
import org.example.pojo.OrderItems;

import java.util.List;

public interface MyOrderMapper {
    //增删改查:要考虑联表查询，大部分情况应该由商品ID来进行查询
    void add(MyOrder myOrder);
    void deleteByCommodityId(int commodityId);

    //order_item
    void addOrderItem(@Param("orderId") int orderId, @Param("commodityIds")List<Integer> commodityIds);

    List<MyOrder> queryAll();
    MyOrder queryById(int id);

    void deleteItemsById(int id);
}
