package com.wjord.mapper;

import com.wjord.info.Buyer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 使得buyer信息可以正常传入query
 *
 * @Author WJORD
 */
@Component
public class BuyerRowMapper implements RowMapper<Buyer>, Serializable {

    @Override
    public Buyer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Buyer buyer = new Buyer();
        buyer.setBuyerName(rs.getString("buyer_name"));
        buyer.setBuyerPhone(rs.getString("buyer_phone"));
        buyer.setBuyerAddress(rs.getString("buyer_address"));
        buyer.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
        buyer.setUpdateTime(rs.getTimestamp("update_time").toLocalDateTime());
        return buyer;
    }
}
