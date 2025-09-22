package org.learning.order_system.mapper;

import org.apache.ibatis.annotations.*;
import org.learning.order_system.pojo.Product;

import java.util.List;
@Mapper
public interface ProductMapper {
    @Insert("INSERT INTO product (product_name, product_price, quantity, is_deleted) VALUES (#{productName},#{productPrice}, #{quantity}, #{isDeleted})")
    void insertProduct(Product product);
    @Delete("delete from product where product_id=#{productId}")
    void deleteProduct(Integer productId);
    @Update("update product set product_name = #{productName}, product_price = #{productPrice},quantity=#{quantity},is_deleted=#{isDeleted} where product_id=#{productId}")
    void updateProduct(Product product);
    @Select("select product_name, product_price, quantity, is_deleted from product where product_id=#{productId}")
    Product getProduct(Integer productId);
    @Select("select product_name, product_price, quantity, is_deleted from product")
    List<Product> getAllProducts();
    @Update("update product set quantity=quantity-#{reduceQuantity} where product_id=#{productId}")
    void reduceQuantity(Integer productId, Integer reduceQuantity);
    //实现软删除，实际上是修改操作
    @Update("update product set is_deleted=1;")
    void softDeleteProduct(Integer productId);
}
