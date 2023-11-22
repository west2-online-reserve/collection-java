package com.wjord.mapper;

import com.wjord.info.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductRowMapper implements RowMapper<Product>, Serializable {


    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setProductCode(rs.getString("product_code"));
        product.setProductName(rs.getString("product_name"));
        product.setProductPrice(rs.getBigDecimal("product_price"));
        product.setProductStock(rs.getInt("product_stock"));
        product.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
        product.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
        return product;
    }
}
