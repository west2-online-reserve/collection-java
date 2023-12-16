package com.west2.work3;

import com.west2.peizhi.utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
            con= JdbcUtils.getConnection();
            // 创建商品表
            String sql = "create table if not exists `goods`(" +
                    "`id` INT(8) NOT NULL AUTO_INCREMENT COMMENT '商品编号'," +
                    "`name` VARCHAR(30) NOT NULL DEFAULT '空' COMMENT '商品名称'," +
                    "`price` DOUBLE(20) NOT NULL DEFAULT '0.00' COMMENT '商品价格'," +
                    "PRIMARY KEY(`id`)" +
                    ")ENGINE INNODB DEFAULT CHARSET=utf8;" ;
            JdbcUtils.createOrder(con,sql);

        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            JdbcUtils.release(con,st,rs);
        }
    }
}
