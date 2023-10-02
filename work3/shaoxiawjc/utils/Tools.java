package com.wjc.exam.utils;

import com.wjc.demo.Demo01.utils.jdbcUtils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;

public class Tools {
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;
    //配置并加载驱动
    static {
        try {
            InputStream in = jdbcUtils.class.getClassLoader().getResourceAsStream("db1.properties");
            Properties properties = new Properties();
            properties.load(in);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    //获取连接
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    // ========================================================================= //
    //增,创建订单
    public static  void addOrder(Connection connection, int productid, Date ordertime, BigDecimal orderprice) throws SQLException {
        String sql = "insert into orders (productid,ordertime,orderprice) values (?,?,?)";
        PreparedStatement preparedStatement = null;
        String compareSql = "select * from products where productid = ?";
        PreparedStatement compareSt = null; // 用于比较订单价格是否小于商品价格
        ResultSet resultSet = null;
        try {
            // 预编译
            compareSt = connection.prepareStatement(compareSql);
            compareSt.setInt(1,productid);
            resultSet = compareSt.executeQuery();
            resultSet.next();
            if ((resultSet.getBigDecimal("productprice")).compareTo(orderprice) >= 0){
                System.out.println("订单价格异常，创建失败");
                return;
            }


            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,productid);
            preparedStatement.setDate(2,new java.sql.Date(ordertime.getTime()));
            preparedStatement.setBigDecimal(3,orderprice);


            int i = preparedStatement.executeUpdate();
            if (i == 0) {
                System.out.println("订单创建失败");
            }else {
                System.out.println("订单创建成功");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }//释放 preparedStatement 内存
        finally {
            if(resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null){
          preparedStatement.close();
            }
            if (compareSt != null) {
                compareSt.close();
            }
        }
    }
    // ======================================= //

    //删除订单
    public static void deleteOrder(Connection connection,int orderid) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "delete from orders where orderid = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,orderid);
            int i =preparedStatement.executeUpdate();
            if (i == 0){
                System.out.println("没有该订单，订单删除失败");
            }else {
                System.out.println("订单删除成功");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
    }
    // 改，修改订单信息
    public static void updateOrder(Connection connection,int orderid,int productid,Date ordertime,BigDecimal orderprice) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "update orders set productid = ?,ordertime = ?,orderprice = ? where orderid = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,productid);
            preparedStatement.setDate(2,new java.sql.Date(ordertime.getTime()));
            preparedStatement.setBigDecimal(3,orderprice);
            preparedStatement.setInt(4,orderid);
            int i = preparedStatement.executeUpdate();
            if(i == 0) {
                System.out.println("修改失败");
            }else {
                System.out.println("修改成功");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (preparedStatement != null){
            preparedStatement.close();}
        }
    }
    // ==================================================== //
    // 查，查询订单
    public static void selectOrder(Connection connection,int orderid) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql ="SELECT `orderid`,`productname`,`ordertime`,`productprice`,`orderprice` " +
                "FROM `orders` ods " +
                "INNER JOIN `products` pds " +
                "ON ods.`productid` = pds.`productid` " +
                "WHERE `orderid` = ? ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,orderid);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null&&resultSet.next()) {
                System.out.println("订单的id为 ： "+resultSet.getInt("orderid"));
                System.out.println("商品的名字为 ： "+resultSet.getString("productname"));
                System.out.println("下单的日期为 ： "+resultSet.getDate("ordertime"));
                System.out.println("商品的价格为 ： "+resultSet.getBigDecimal("productprice"));
                System.out.println("订单的价格为 ： "+resultSet.getBigDecimal("orderprice"));
            }else {
                System.out.println("没有该订单");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }


    // 以上的增删改查为对orders表的操作
    // ====================================================== //
    // 以下为对products表的操作

    // 增加商品
    public static void addProduct(Connection connection,String prooductname,BigDecimal productprice) throws SQLException {
        if (productprice.compareTo(new BigDecimal("0")) < 0) {
            System.out.println("输入有误，商品价格不能为负");
            return;
        }
        PreparedStatement preparedStatement = null;
        String sql = "insert into products(productname,productprice) values (?,?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,prooductname);
            preparedStatement.setBigDecimal(2,productprice);
            int i = preparedStatement.executeUpdate();
            if (i != 0){
                System.out.println("商品添加成功");
            }else {
                System.out.println("商品添加失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

    }
    //删除商品（我的想法是如果订单中含有该商品，则一并删除）
    public static void deleteProduct(Connection connection,int productid) throws SQLException {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        ResultSet resultSet = null;
        //PreparedStatement 只能执行一条语句
        String sql = "delete from orders where productid = ?;";
        String sql1 = "delete from products where productid = ?;";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement.setInt(1,productid);
            preparedStatement1.setInt(1,productid);
            int i = preparedStatement1.executeUpdate();
            if (i != 0){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }

    }

    //修改商品信息
    public static void updateProduct(Connection connection,int productid,String productname,BigDecimal productprice) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "update products set productname = ?,productprice = ? where productid = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,productname);
            preparedStatement.setBigDecimal(2,productprice);
            preparedStatement.setInt(3,productid);
            int i = preparedStatement.executeUpdate();
            if(i == 0) {
                System.out.println("修改失败");
            }else {
                System.out.println("修改成功");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();}
        }
    }
    //查询商品信息
    public static void selectProducts(Connection connection,String productname) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql ="SELECT * FROM `products` WHERE `productname` = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,productname);
            resultSet = preparedStatement.executeQuery();
            if (resultSet != null&&resultSet.next()) {
                System.out.println("该商品的id是："+resultSet.getInt("productid"));
                System.out.println("该商品的名字是："+resultSet.getString("productname"));
                System.out.println("该商品的价格是"+resultSet.getBigDecimal("productprice"));
            }else {
                System.out.println("未找到该商品");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }


    }

}
