package test;

import JDBC.JdbcUtils;
import JDBC.ResultSetHandler;
import informationClass.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SelectTest {
    public static void main(String[] args) {
        String sql = null;
        Object[] params = null;
        Scanner sc = new Scanner(System.in);
        JdbcUtils jdbcUtils = new JdbcUtils();

        while (true) {
            sql = sc.nextLine();

            ArrayList<Product> ps = new ArrayList<>();
            if ("exit".equals(sql)) {
                System.out.println("Exiting...");
                break;
            }
            params = new Object[0];
            ;
            ps = jdbcUtils.executeQuery(sql, new ResultSetHandler<ArrayList<Product>>() {
                @Override
                public ArrayList<Product> handle(ResultSet rs) {
                    ArrayList<Product> ps1 = new ArrayList<>();
                    while (true){
                        try {
                            if (!rs.next())
                                break;
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        Product  p = new Product();
                        try {
                            p.setProduct_code(String.valueOf(rs.getInt("product_code")));
                            p.setProduct_name(rs.getString("product_name"));
                            p.setProduct_price(rs.getDouble("product_price"));
                            p.setOrder_code(String.valueOf(rs.getInt("order_code")));
                            ps1.add(p);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return ps1;
                }
            }, params);
            BufferedWriter bw = null;
            try {
                 bw = new BufferedWriter(new FileWriter("src/main/java/test/Output.txt",true));
                 if (ps.size()>0){
                     for (Product p : ps){
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
        }
        sc.close();
    }
}
