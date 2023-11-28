package JDBC.orderManagemaentSystem;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * jdbcutils 工具类，实现连接数据库，执行增删查改操作，事务增添，资源释放，异常处理
 * 商品已存在再次添加异常，订单未找到异常，订单价格不合理异常，商品未找到。
 *
 * @Author 31445
 * @Date 2023/11/23
 */
public class jdbcutils {
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;

    static {
        try{
            InputStream in = jdbcutils.class.getClassLoader().getResourceAsStream("collection-java/work3/zy102303127/db.properties");
            Properties pr=new Properties();
            pr.load(in);

            driver=pr.getProperty("driver");
            url=pr.getProperty("url");
            username=pr.getProperty("username");
            password=pr.getProperty("password");

            /*
            驱动只用加载一次
             */
            Class.forName(driver);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    /**
     * 释放资源
     * @param conn
     * @param st
     * @param rs
     * @throws SQLException
     */
    public static void release(Connection conn, Statement st, ResultSet rs) throws SQLException {
        if (rs!=null){
            rs.close();
        }
        if (st!=null){
            st.close();
        }
        if(conn!=null){
            conn.close();
        }
    }

    /**
     * 查询的方法
     * @param conn
     * @param sql
     * @param params
     * @return 查询到的结果
     * @throws SQLException
     */
    public static ResultSet executeQuery(Connection conn, String sql, Object... params) throws SQLException {
        /*
        先进行预编译，不执行
         */
        conn.setAutoCommit(false);
        PreparedStatement st = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            st.setObject(i + 1, params[i]);
        }
        return st.executeQuery();
    }

    /**
     * 更新的方法(增删改)
     * @param conn
     * @param sql
     * @param params
     * @return 表中变换的行数
     * @throws SQLException
     */
    public static int executeUpdate(Connection conn, String sql, Object... params) throws SQLException {
        conn.setAutoCommit(false);
        PreparedStatement st= conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            st.setObject(i + 1, params[i]);
        }
        return st.executeUpdate();
    }

    /**
     * 开启事务
     * @param conn
     * @throws SQLException
     */
    public static void beginTransaction(Connection conn) throws SQLException {
        conn.setAutoCommit(false);
    }

    /**
     * 提交事务
     * @param conn
     * @throws SQLException
     */
    public static void commitTranscation(Connection conn) throws SQLException {
        conn.commit();
    }

    /**
     * 执行失败，回滚。
     *
     * @param conn
     */
    public static void rollTranscation(Connection conn){
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * 商品存在的异常处理
     *
     * @param conn,productName
     * @throws MyException,SQLException
     */
    public static void productHasExistException(Connection conn, String productName) throws MyException, SQLException {
        String query = "SELECT * FROM `商品` WHERE `商品名` = ?";
        ResultSet rs;
        rs = jdbcutils.executeQuery(conn, query, productName);
        int count=0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        jdbcutils.release(null, null, rs);
        if (count != 0) {
            throw new MyException("商品名已存在！");
        }
    }

    /**
     * 订单未找到异常
     * @param conn
     * @param orderId
     * @throws SQLException
     * @throws MyException
     */
    public static void orderNotFoundException(Connection conn,int orderId) throws SQLException, MyException {
        ResultSet rs;
        String queryOrderSql = "select count(*) from `订单` where `订单编号` = ?";
        rs = jdbcutils.executeQuery(conn, queryOrderSql, orderId);
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        jdbcutils.release(null, null, rs);
        if (count == 0) {
            throw new MyException("订单不存在！");
        }
    }

    /**
     * 价格不合理异常
     *
     * @param orderPrice
     * @param productPrice
     * @throws SQLException
     * @throws MyException
     */
    public static void IllegalPriceException(double orderPrice,double productPrice) throws SQLException, MyException {
        if(orderPrice>productPrice*2||orderPrice<=productPrice){
            throw new MyException("订单价格与实际情况不符。");
        }
    }

    /**
     * 商品未找到异常
     *
     * @param conn
     * @param productId
     * @throws SQLException
     * @throws MyException
     */
    public static void productNotFountException(Connection conn,int productId) throws SQLException, MyException {
        ResultSet rs;
        String queryOrderSql = "select count(*) from `商品` where `商品编号` = ?";
        rs = jdbcutils.executeQuery(conn, queryOrderSql, productId);
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        jdbcutils.release(null, null, rs);
        if (count == 0) {
            throw new MyException("商品不存在！");
        }
    }

}
