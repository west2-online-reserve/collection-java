package com.amiera.mapper;

import com.amiera.objects.orders;
import com.amiera.objects.products;

import java.util.List;

public interface objectsMapper {
    //实现CRUD操作
    //orders增删改查
    //添加订单
    int insertOrders(orders orders);
    //删除订单
    int deleteOrders(int id);
    //更新订单
    int updateOrders(orders orders);
    //查询所有订单
    List<orders> selectAllOrders();
    //products
    //增加产品
    int insertProducts(products products);
    //删除产品
    int deleteProducts(int id);
    //更新产品
    int updateProducts(products products);
    //查询所有产品
    List<products> selectAllProducts();
    //检查工具
    //根据ID查询商品
    products selectProductById(int productId);
    //根据ID查询订单
    orders selectOrderById(int orderId);
}
