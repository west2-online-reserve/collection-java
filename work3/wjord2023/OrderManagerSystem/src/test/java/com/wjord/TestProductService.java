package com.wjord;

import com.wjord.config.Config;
import com.wjord.info.Product;
import com.wjord.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;

@Component
@SpringJUnitConfig(classes = Config.class)
public class TestProductService {

    @Autowired
    private Product product;
    @Autowired
    private ProductService productService;

    @Test
    public void testInsertProduct() {
        productService.insertProduct("反甲", 1, BigDecimal.valueOf(1840));
        productService.insertProduct("饮血剑", 6, BigDecimal.valueOf(2405));
        productService.insertProduct("帽子", 5, BigDecimal.valueOf(3600));
        productService.insertProduct("狂徒铠甲", 1, BigDecimal.valueOf(2750));
    }

    @Test
    public void testDeleteProduct() {
        productService.deleteProduct("268498896998");
    }

    @Test
    public void testUpdateProduct() {
        productService.updateProduct("680918584993", "反甲", 2, BigDecimal.valueOf(1840));
    }

    @Test
    public void testSelectProduct() {
        System.out.println(productService.selectProduct("680918584993"));
    }

    @Test
    public void testSelectAllProducts() {
        System.out.println(productService.selectAllProducts());
    }

    @Test
    public void testSelectTotalProductCount() {
        System.out.println(productService.selectTotalProductCount());
    }

    @Test
    public void testSelectSortedProductByPrice() {
        System.out.println(productService.selectSortedProductByPrice());
    }

    @Test
    public void testSelectSortedProductByUpdateTime() {
        System.out.println(productService.selectSortedProductByUpdateTime());
    }
}
