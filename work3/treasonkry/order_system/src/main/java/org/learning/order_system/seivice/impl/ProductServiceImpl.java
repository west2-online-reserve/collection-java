package org.learning.order_system.seivice.impl;

import org.learning.order_system.mapper.ProductMapper;
import org.learning.order_system.pojo.Product;
import org.learning.order_system.seivice.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductMapper productMapper;
    @Override
    public void addProduct(Product product) {
        productMapper.insertProduct(product);
    }
    @Override
    public void updateProduct(Product product) {
        productMapper.updateProduct(product);
    }
    @Override
    public List<Product> getProducts(){
        return productMapper.getAllProducts();
    }
    @Override
    public Product getProduct(Integer id) {
        return productMapper.getProduct(id);
    }
    //采用软删除机制
    @Override
    public void deleteProduct(Integer productId) {
        productMapper.softDeleteProduct(productId);
    }
}
