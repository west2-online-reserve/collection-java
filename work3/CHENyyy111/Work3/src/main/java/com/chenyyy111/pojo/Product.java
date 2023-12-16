package com.chenyyy111.pojo;

import com.chenyyy111.dao.ProductMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int productId;
    private String productName;
    private double productPrice;

    public Product(String productName, double productPrice){
        this.productName = productName;
        this.productPrice = productPrice;
    }
}

