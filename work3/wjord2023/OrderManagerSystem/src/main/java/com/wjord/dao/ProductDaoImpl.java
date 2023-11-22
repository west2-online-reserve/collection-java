package com.wjord.dao;

import com.wjord.info.Product;
import com.wjord.mapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductRowMapper productMapper;

    @Override
    public void insertProduct(Product product) {
        String sql = "insert into product_info (product_code, product_name, product_price, product_stock, create_time, update_time) values (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getProductCode(), product.getProductName(), product.getProductPrice(), product.getProductStock(), product.getCreateTime(), product.getUpdateTime());
    }

    @Override
    public void deleteProductByCode(String productCode) {
        String sql = "delete from product_info where product_code = ?";
        jdbcTemplate.update(sql, productCode);
    }

    @Override
    public void deleteProductByName(String productName) {
        String sql = "delete from product_info where product_name = ?";
        jdbcTemplate.update(sql, productName);
    }

    @Override
    public void updateProductByCode(String productCode, Product product) {
        String sql = "update product_info set product_name = ?, product_price = ?, product_stock = ?, update_time = ? where product_code = ?";
        jdbcTemplate.update(sql, product.getProductName(), product.getProductPrice(), product.getProductStock(), product.getUpdateTime(), productCode);
    }

    @Override
    public void updateProductByName(String productName, Product product) {
        String sql = "update product_info set product_code = ?, product_price = ?, product_stock = ?, update_time = ? where product_name = ?";
        jdbcTemplate.update(sql, product.getProductCode(), product.getProductPrice(), product.getProductStock(), product.getUpdateTime(), productName);
    }

    @Override
    public Product selectProductByCode(String productCode) {
        String sql = "select product_code, product_name, product_price, product_stock, create_time, update_time from product_info where product_code = ?";
        List<Product> query = jdbcTemplate.query(sql, productMapper, productCode);
        return query.isEmpty() ? null : query.get(0);
    }

    @Override
    public Product selectProductByName(String productName) {
        String sql = "select product_code, product_name, product_price, product_stock, create_time, update_time from product_info where product_name = ?";
        List<Product> query = jdbcTemplate.query(sql, productMapper, productName);
        return query.isEmpty() ? null : query.get(0);
    }

    @Override
    public List<Product> selectAllProducts() {
        String sql = "select product_code, product_name, product_price, product_stock, create_time, update_time from product_info";
        return jdbcTemplate.query(sql, productMapper);
    }

    @Override
    public int selectTotalProductCount() {
        String sql = "select count(*) from product_info";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public List<Product> sortProductByPrice() {
        String sql = "select product_code, product_name, product_price, product_stock, create_time, update_time from product_info order by product_price desc ";
        List<Product> query = jdbcTemplate.query(sql, productMapper);
        return query.isEmpty() ? null : query;
    }

    @Override
    public List<Product> sortProductByUpdatedTime() {
        String sql = "select product_code, product_name, product_price, product_stock, create_time, update_time from product_info order by update_time desc ";
        List<Product> query = jdbcTemplate.query(sql, productMapper);
        return query.isEmpty() ? null : query;
    }
}
