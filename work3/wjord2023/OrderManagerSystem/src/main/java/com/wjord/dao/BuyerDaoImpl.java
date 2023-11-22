package com.wjord.dao;

import com.wjord.info.Buyer;
import com.wjord.mapper.BuyerRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BuyerDaoImpl implements BuyerDao {

    @Autowired
    private BuyerRowMapper buyerMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertBuyer(Buyer buyer) {
        String sql = "insert into buyer_info (buyer_phone, buyer_name, buyer_address, create_time, update_time) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, buyer.getBuyerPhone(), buyer.getBuyerName(), buyer.getBuyerAddress(), buyer.getCreateTime(), buyer.getUpdateTime());
    }

    @Override
    public void deleteBuyerByPhone(String buyerPhone) {
        String sql = "delete from buyer_info where buyer_phone = ?";
        jdbcTemplate.update(sql, buyerPhone);
    }

    @Override
    public void updateBuyerByPhone(String buyerPhone, Buyer buyer) {
        String sql = "update buyer_info set buyer_name = ?, buyer_address = ?, update_time = ? where buyer_phone = ?";
        jdbcTemplate.update(sql, buyer.getBuyerName(), buyer.getBuyerAddress(), buyer.getUpdateTime(), buyerPhone);
    }

    @Override
    public void updateBuyerByName(String buyerName, Buyer buyer) {
        String sql = "update buyer_info set buyer_phone = ?, buyer_address = ?, update_time = ? where buyer_name = ?";
        jdbcTemplate.update(sql, buyer.getBuyerPhone(), buyer.getBuyerAddress(), buyer.getUpdateTime(), buyerName);
    }

    @Override
    public Buyer selectBuyerByName(String buyerName) {
        String sql = "select buyer_phone, buyer_name, buyer_address, create_time, update_time from buyer_info where buyer_name = ?";
        List<Buyer> query = jdbcTemplate.query(sql, buyerMapper, buyerName);
        return query.isEmpty() ? null : query.get(0);
    }

    @Override
    public Buyer selectBuyerByPhone(String buyerPhone) {
        String sql = "select buyer_phone, buyer_name, buyer_address, create_time, update_time from buyer_info where buyer_phone = ?";
        List<Buyer> query = jdbcTemplate.query(sql, buyerMapper, buyerPhone);
        return query.isEmpty() ? null : query.get(0);
    }

    @Override
    public List<Buyer> selectAllBuyers() {
        String sql = "select buyer_phone, buyer_name, buyer_address, create_time, update_time from buyer_info";
        return jdbcTemplate.query(sql, buyerMapper);
    }

    @Override
    public int selectTotalBuyerCount() {
        String sql = "select count(*) from buyer_info";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int selectBuyerCountByName(String buyerName) {
        String sql = "select count(*) from buyer_info where buyer_name = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, buyerName);
    }
}
