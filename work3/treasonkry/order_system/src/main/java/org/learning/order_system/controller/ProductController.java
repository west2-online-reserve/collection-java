package org.learning.order_system.controller;

import org.apache.ibatis.annotations.Delete;
import org.learning.order_system.pojo.Product;
import org.learning.order_system.seivice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    List<Product> getProducts(){
        return productService.getProducts();
    }
    @PostMapping
    void createProduct(@RequestBody Product product){
        productService.addProduct(product);
    }
    @PutMapping
    void updateProduct(@RequestBody Product product){
        productService.updateProduct(product);
    }
    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
    }
}
