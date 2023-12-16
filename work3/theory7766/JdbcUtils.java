// 这是一个获取数据库连接，以及关闭连接的工具类
package com.west2.peizhi.utils;

import com.west2.work3.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {
        try {
            // 从资源文件中获取信息流
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            // 仅加载一次驱动
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // 释放连接资源
    public static void release(Connection conn, PreparedStatement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 创建表
     *
     * @param con   数据库连接对象
     * @param sql   sql语句
     * */
    public static void createOrder(Connection con, String sql)throws SQLException{
        try {
            con.setAutoCommit(false);//开启事务
            Statement st = con.createStatement();
            st.execute(sql);
            con.commit();//业务完毕，提交事务
        } catch (SQLException e) {
            System.out.println("创建失败！");
            e.printStackTrace();
        }
    }


    /**
     * 预处理SQL，获取PreparedStatement对象，并对sql参数进行赋值操作
     *
     * @param con   数据库连接对象
     * @param sql   sql语句
     * @param param 实际参数
     * @return 返回PreparedStatement对象
     * @throws SQLException SQL异常
     */
    public static PreparedStatement handleSql(Connection con, String sql, Object[] param) throws SQLException {
        PreparedStatement st = con.prepareStatement(sql);
        int paramCnt = st.getParameterMetaData().getParameterCount();
        if (paramCnt != 0 && param != null && paramCnt == param.length) {
            for (int i = 0; i < paramCnt; i++) {
                st.setObject(i + 1, param[i]);
            }
        }
        return st;
    }

    /**
     * 更新方法：insert,delete,update
     *
     * @param con   数据库连接对象
     * @param sql   目标执行的SQL语句
     * @param param 对应的SQL语句的参数
     * @return SQL语句对应的数据表的影响行数
     */
    public static int update(Connection con, String sql, Object[] param) {
        int affectRows = 0;
        PreparedStatement st = null;
        try {
            con.setAutoCommit(false);//开启事务
            st = handleSql(con, sql, param);
            affectRows = st.executeUpdate();
            con.commit();//业务完毕，提交事务
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectRows;
    }

    /**
     * 通用query 查询方法
     *
     * @param con   数据库连接对象
     * @param sql   要执行的sql语句
     * @param rsh   结果集处理器接口实现类对象
     * @param param 对应的SQL语句的参数
     * @param <T>   自定义声明泛型
     * @return 接口实现类约束的返回值类型结果
     * @throws SQLException SQL异常
     */
    public <T> T queryOrder(Connection con, String sql, ResultSetHandler<T> rsh, Object[] param) throws SQLException {

        PreparedStatement st = null;
        ResultSet rs = null;
        // 泛型变量，具体数据类型由接口实现类决定
        T t = null;
        try {
            con.setAutoCommit(false);//开启事务
            st = handleSql(con, sql, param);
            rs = st.executeQuery();
            t = rsh.handle(rs);
            con.commit();//业务完毕，提交事务
        } catch (SQLException e) {
            System.out.println("查询出错");
        }
        return t;
    }

    /**
     * 查询一行数据 key:value
     *
     * @param con   数据库连接对象
     * @param sql   要执行的sql语句
     * @param param 对应的SQL语句的参数
     * @return 返回map(key, value)
     */
    public Map<String, Object> queryMap(Connection con, String sql, Object[] param) throws SQLException {
        return queryOrder(con, sql, new MapHandler(), param);
    }

    /**
     * 查询多行数据 key:value
     *
     * @param con   数据库连接对象
     * @param sql   要执行的sql语句
     * @param param 对应的SQL语句的参数
     * @return 返回多行数据 由map(key与value)构成的List
     */
    public List<Map<String, Object>> queryMapList(Connection con, String sql, Object[] param) throws SQLException {
        return queryOrder(con, sql, new MapListHandler(), param);
    }

    /**
     * 查询多行数据
     *
     * @param con   数据库连接对象
     * @param sql   要执行的sql语句
     * @param param 对应的SQL语句的参数
     * @return 返回list
     */
    public List<Object[]> queryArrayList(Connection con, String sql, Object[] param) throws SQLException {
        return (List<Object[]>) queryOrder(con,sql,new ArrayListHandler(),param);
    }
    /**
     * 查询单行数据
     *
     * @param con   数据库连接对象
     * @param sql   要执行的sql语句
     * @param param 对应的SQL语句的参数
     * @return 返回list
     */
    public Object[] queryArray(Connection con, String sql, Object[] param) throws SQLException {
        return queryOrder(con,sql,new ArrayHandler(),param);
    }
}
