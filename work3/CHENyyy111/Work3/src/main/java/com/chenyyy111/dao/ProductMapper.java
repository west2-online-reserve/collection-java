package com.chenyyy111.dao;

import com.chenyyy111.pojo.Product;

import java.util.List;

public interface ProductMapper {
    //查询所有商品
    List<Product> getProductsList();

    //通过商品编号查询商品
    Product getProductById(int productId);

    //添加一个商品
    int addProduct(Product product);

    //修改商品
    int updateProduct(Product product);

    //通过商品编号删除商品
    int deleteProduct(int productId);


}
