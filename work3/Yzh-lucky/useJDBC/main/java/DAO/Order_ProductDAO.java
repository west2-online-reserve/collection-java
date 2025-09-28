package DAO;

import JDBC.JdbcUtils;
import JDBC.ResultSetHandler;
import informationClass.Order;
import informationClass.Order_Product;
import informationClass.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order_ProductDAO {
    /**
     * 查询所有订单及其对应的商品信息
     */
    public void queryAll(String sort_order) {
        String sql = "select * from `order_product` order by `order_code`"+sort_order+",`product_code`" +sort_order;

        JdbcUtils.executeQuery(sql, new ResultSetHandler<Void>() {
            @Override
            public Void handle(ResultSet rs) {
                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(new FileWriter("src/main/java/test/Output.txt", true));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                while (true) {
                    StringBuilder sb = new StringBuilder();
                    try {
                        if (!rs.next()) break;
                        sb.append("订单编号： " + rs.getInt("order_code") + "\n");
                        sb.append("商品编号： " + rs.getInt("product_code") + "\n");
                        sb.append("商品名称： " + rs.getString("product_name") + "\n");
                        sb.append("商品价格： " + rs.getDouble("product_price") + "\n");
                        sb.append("订单总价： " + rs.getDouble("order_price") + "\n");
                        sb.append("============================\n");
                        bw.write(sb.toString());
                        bw.flush();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
                return null;
            }
        }, new Object[0]);
    }

    /**
     * 查询指定订单及其对应的商品信息
     */
    public void queryByOrderCode(String orderCode,String sort_order) {
        String sql = "select * from `order_product` where order_code = ? order by `order_code`"+sort_order+",`product_code`" +sort_order;

        JdbcUtils.executeQuery(sql, new ResultSetHandler<Void>() {
            @Override
            public Void handle(ResultSet rs) {
                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(new FileWriter("src/main/java/test/Output.txt", true));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                while (true) {
                    StringBuilder sb = new StringBuilder();
                    try {
                        if (!rs.next()) break;
                        sb.append("订单编号： " + rs.getInt("order_code") + "\n");
                        sb.append("商品编号： " + rs.getInt("product_code") + "\n");
                        sb.append("商品名称： " + rs.getString("product_name") + "\n");
                        sb.append("商品价格： " + rs.getDouble("product_price") + "\n");
                        sb.append("订单总价： " + rs.getDouble("order_price") + "\n");
                        sb.append("============================\n");
                        bw.write(sb.toString());
                        bw.flush();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
                return null;
            }
        }, orderCode);
    }
    /**
     * 分页查询订单
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return ArrayList<Order>
     */
    public ArrayList<Order_Product> queryByPage(int  pageNum, int pageSize){
        if (pageNum <1)
            pageNum = 1;
        if (pageSize <10 || pageSize >50)
            pageSize = 10;
        int offset = (pageNum - 1) * pageSize;
        String sql = "select `order_code`,`product_code`,`product_name`,`product_price`,`order_price` from `order_product` order by `order_code` asc limit ?,?";
        return JdbcUtils.executeQuery(sql,new ResultSetHandler<ArrayList<Order_Product>>(){
            @Override
            public ArrayList<Order_Product> handle(ResultSet rs){
                ArrayList<Order_Product> ops = new ArrayList<>();
                while (true){
                    try {
                        if (!rs.next())
                            break;
                        Order_Product op = new Order_Product();
                        op.setOrder_code(String.valueOf(rs.getInt("order_code")));
                        op.setProduct_code(String.valueOf(rs.getInt("product_code")));
                        op.setProduct_name(rs.getString("product_name"));
                        op.setProduct_price(rs.getDouble("product_price"));
                        op.setOrder_price(rs.getDouble("order_price"));
                        ops.add(op);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                return ops;
            }
        },offset,pageSize);
    }
    /**
     * 打印订单商品信息到文件
     * @param pageNum
     * @param pageSize
     */
    public void printQueryByPage(int pageNum, int pageSize) {
        ArrayList<Order_Product> list = queryByPage(pageNum, pageSize);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("src/main/java/test/Output.txt",true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Order_Product o : list){
            StringBuilder sb = new StringBuilder();
            sb.append("订单编号: "+ o.getOrder_code() + "\n");
            sb.append("商品编号: "+ o.getProduct_code() + "\n");
            sb.append("商品名称: "+ o.getProduct_name() + "\n");
            sb.append("商品价格 "+ o.getProduct_price() + "\n");
            sb.append("订单总价: "+ o.getOrder_price() + "\n");
            sb.append("======================================\n");
            try {
                bw.write(sb.toString());
                bw.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
