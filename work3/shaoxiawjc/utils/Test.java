package com.wjc.exam.utils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws SQLException {
        Connection connection = Tools.getConnection();

        try {
            //先添加商品
            Tools.addProduct(connection,"拜登写真",new BigDecimal("100.00"));
            Tools.addProduct(connection,"镇店之宝",new BigDecimal("99999.99"));
            Tools.addProduct(connection,"帝皇侠变身器",new BigDecimal("10000.00"));
            Tools.addProduct(connection,"shit",new BigDecimal("-666")); //价格异常测试
            //添加订单
            Tools.addOrder(connection,1,new java.sql.Date(new Date().getTime()),new BigDecimal("200.00"));
            Tools.addOrder(connection,1,new java.sql.Date(new Date().getTime()),new BigDecimal("500.00"));
            Tools.addOrder(connection,3,new java.sql.Date(new Date().getTime()),new BigDecimal("60000.00"));
            Tools.addOrder(connection,2,new java.sql.Date(new Date().getTime()),new BigDecimal("1")); //价格异常测试
            //查询订单，商品
            Tools.selectOrder(connection,2);
            Tools.selectOrder(connection,100); //订单编号异常测试
            Tools.selectProducts(connection,"镇店之宝");
            Tools.selectProducts(connection,"电动");//商品名字异常测试
            //删除订单，商品
           Tools.deleteOrder(connection,16); // 订单号不存在测试
           Tools.deleteProduct(connection,18); //商品号不存在测试
            //修改
            Tools.updateOrder(connection,3,1,new java.sql.Date(new Date().getTime()),new BigDecimal("10000.00"));

            Tools.updateProduct(connection,3,"修罗铠甲变身器", new BigDecimal("999999"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
