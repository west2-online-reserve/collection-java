package test;

import JDBC.JdbcUtils;
import JDBC.ResultSetHandler;
import informationClass.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionTest {
    public static void main(String[] args) {
        String sql1 = "select * from `product_information` where `product_code`=? and `order_code`=? ";
        String sql2 = "select * from `product_information` where `product_code`=? and `order_code`=? ";

        ArrayList<Product> p1 = new ArrayList<Product>();
        ArrayList<Product> p2 = new ArrayList<Product>();
        try {
            Connection connection = JdbcUtils.getConnection();
            JdbcUtils.beginTransaction();
            p1 = JdbcUtils.executeQuery(sql1, new ResultSetHandler<ArrayList<Product>>(){
                @Override
                public ArrayList<Product> handle(ResultSet rs){
                    ArrayList<Product> p3 = new ArrayList<Product>();
                    try {
                        while (rs.next()) {
                            Product p = new Product();
                            p.setProduct_code(String.valueOf(rs.getInt("product_code")));
                            p.setProduct_name(rs.getString("product_name"));
                            p.setProduct_price(rs.getDouble("product_price"));
                            p.setOrder_code(String.valueOf(rs.getInt("order_code")));
                            p3.add(p);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return p3;
                }
            }, 1, 1);
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter("src/main/java/test/Output.txt",true));
                if (p1.size()>0){
                    for (Product p : p1){
                        bw.write(p.toString());
                        bw.write("========================================\n");
                    }
                } else {
                    bw.write("没有查询到！！");
                    bw.write("========================================\n");
                }
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Select successfully!");
            p2 = JdbcUtils.executeQuery(sql1, new ResultSetHandler<ArrayList<Product>>(){
                @Override
                public ArrayList<Product> handle(ResultSet rs){
                    ArrayList<Product> p4 = new ArrayList<Product>();
                    try {
                        while (rs.next()) {
                            Product p = new Product();
                            p.setProduct_code(String.valueOf(rs.getInt("product_code")));
                            p.setProduct_name(rs.getString("product_name"));
                            p.setProduct_price(rs.getDouble("product_price"));
                            p.setOrder_code(String.valueOf(rs.getInt("order_code")));
                            p4.add(p);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    return p4;
                }
            }, 2, 2);
            try {
                bw = new BufferedWriter(new FileWriter("src/main/java/test/Output.txt",true));
                if (p2.size()>0){
                    for (Product p : p2){
                        bw.write(p.toString());
                        bw.write("========================================\n");
                    }
                } else {
                    bw.write("没有查询到！！");
                    bw.write("========================================\n");
                }
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Select successfully!");

            int i= 1/0;
            JdbcUtils.submitTransaction();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
