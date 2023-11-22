package com.wjord.service;

import com.wjord.info.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    void insertProduct(String productName, int productStock, BigDecimal productPrice);

    void updateProduct(String productCode, String productName, int productStock, BigDecimal productPrice);

    void deleteProduct(String productCode);

    Product selectProduct(String productCode);

    Product selectProductByName(String productName);

    List<Product> selectAllProducts();

    int selectTotalProductCount();

    List<Product> selectSortedProductByPrice();

    List<Product> selectSortedProductByUpdateTime();
}
