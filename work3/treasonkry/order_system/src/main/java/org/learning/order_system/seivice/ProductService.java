package org.learning.order_system.seivice;

import org.learning.order_system.pojo.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Integer productId);
    List<Product> getProducts();
    Product getProduct(Integer id);
}
