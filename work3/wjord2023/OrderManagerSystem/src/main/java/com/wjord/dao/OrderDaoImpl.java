package com.wjord.dao;

import com.wjord.info.Order;
import com.wjord.mapper.OrderRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderRowMapper orderMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertOrder(Order order) {
        String sql = "insert into order_info (order_code, product_code, buyer_phone, order_amount, order_price, create_time, update_time) values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, order.getOrderCode(), order.getProductCode(), order.getBuyerPhone(), order.getOrderAmount(), order.getOrderPrice(), order.getCreateTime(), order.getUpdateTime());
    }

    @Override
    public void deleteOrderByCode(String orderCode) {
        String sql = "delete from order_info where order_code = ?";
        jdbcTemplate.update(sql, orderCode);
    }

    @Override
    public void updateOrderByCode(String orderCode, Order order) {
        String sql = "update order_info set order_price = ?, order_amount = ?, update_time = ? where order_code = ?";
        jdbcTemplate.update(sql, order.getOrderPrice(), order.getOrderAmount(), order.getUpdateTime(), orderCode);
    }

    @Override
    public Order selectOrderByCode(String orderCode) {
        String sql = "select order_code, product_code, buyer_phone, order_price, order_amount, create_time, update_time from order_info where order_code = ?";
        List<Order> query = jdbcTemplate.query(sql, orderMapper, orderCode);
        return query.isEmpty() ? null : query.get(0);
    }

    @Override
    public List<Order> selectAllOrders() {
        String sql = "select order_code, product_code, buyer_phone, order_price, order_amount, create_time, update_time from order_info";
        return jdbcTemplate.query(sql, orderMapper);
    }

    @Override
    public int selectTotalOrderCount() {
        String sql = "select count(*) from order_info";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int selectTotalOrderCountByProductCode(String productCode) {
        String sql = "select count(*) from order_info where product_code = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, productCode);
    }

    @Override
    public int selectTotalOrderCountByProductNmae(String productName) {
        String sql = "select count(*) from order_info where product_name = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, productName);
    }

    @Override
    public int selectTotalOrderCountByProductName(String productName) {
        String sql = "select count(*) from order_info where product_name = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, productName);
    }

    @Override
    public int selectTotalOrderCountByBuyerPhone(String buyerPhone) {
        String sql = "select count(*) from order_info where buyer_phone = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, buyerPhone);
    }

    @Override
    public List<Order> selectOrdersByProductCode(String productCode) {
        String sql = "select order_code, product_code, buyer_phone, order_price, order_amount, create_time, update_time from order_info where product_code = ?";
        List<Order> query = jdbcTemplate.query(sql, orderMapper, productCode);
        return query.isEmpty() ? null : query;
    }

    @Override
    public List<Order> selectOrdersByBuyerPhone(String buyerPhone) {
        String sql = "select order_code, product_code, buyer_phone, order_price, order_amount, create_time, update_time from order_info where buyer_phone = ?";
        List<Order> query = jdbcTemplate.query(sql, orderMapper, buyerPhone);
        return query.isEmpty() ? null : query;
    }

    @Override
    public void deleteOrderByProductCode(String productCode) {
        String sql = "delete from order_info where product_code = ?";
        jdbcTemplate.update(sql, productCode);
    }

    @Override
    public void deleteOrderByBuyerPhone(String buyerPhone) {
        String sql = "delete from order_info where buyer_phone = ?";
        jdbcTemplate.update(sql, buyerPhone);
    }

    //实现对订单的排序，按照订单价格降序排列,如果订单价格相同，按照创建时间降序排列
    @Override
    public List<Order> sortOrderByPrice() {
        String sql = "select order_code, product_code, buyer_phone, order_price, order_amount, create_time, update_time from order_info order by order_price desc, create_time desc";
        List<Order> query = jdbcTemplate.query(sql, orderMapper);
        return query.isEmpty() ? null : query;
    }

    @Override
    public List<Order> sortOrderByUpdateTime() {
        String sql = "select order_code, product_code, buyer_phone, order_price, order_amount, create_time, update_time from order_info order by update_time desc";
        List<Order> query = jdbcTemplate.query(sql, orderMapper);
        return query.isEmpty() ? null : query;
    }
}
