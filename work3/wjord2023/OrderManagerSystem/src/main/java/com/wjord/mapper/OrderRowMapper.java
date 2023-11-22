package com.wjord.mapper;

import com.wjord.info.Order;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

// 这里原本想用自动注入的，结果发现会导致输出全部订单数时会出现输出多个但每个都相同且为第一个元素的情况，不太清楚IoC具体原理，希望可以得到一个解释
@Component
public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setOrderCode(rs.getString("order_code"));
        order.setBuyerPhone(rs.getString("buyer_phone"));
        order.setProductCode(rs.getString("product_code"));
        order.setOrderPrice(rs.getBigDecimal("order_price"));
        order.setOrderAmount(rs.getInt("order_amount"));
        order.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
        order.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
        return order;
    }
}
