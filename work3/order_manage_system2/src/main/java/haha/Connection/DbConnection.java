package com.haha.Connection;


import javax.swing.*;
import java.sql.*;


//连接数据库
public class DbConnection {
    //驱动类的类名
    private static final String DRIVENAME="com.mysql.cj.jdbc.Driver";
    //连接数据的URL路径
    private static final String URL="jdbc:mysql://127.0.0.1:3306/order_manage_system";
    //数据的登陆账号
    private static final String USER="root";
    //数据库的登录密码
    private static final String PASSWORD="liujiarong";
    //加载驱动
    static {
        try{
            Class.forName(DRIVENAME);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    //获取数据库连接
    public static Connection getConnetion(){
        try{
            return (Connection) DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //查询
    public  static ResultSet query(String sql) throws SQLException {
        System.out.println(sql);
        //获取连接
        Connection connection=getConnetion();
        PreparedStatement psd;
        try{
            if (connection != null) {
                psd=connection.prepareStatement(sql);
                return psd.executeQuery();
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"执行语句出错\n"+e.toString());
            e.printStackTrace();
        }
        return null;
    }
    //增删查改
    public static int updateInfo(String sql){
        System.out.println(sql);
        //获取连接
        Connection connection=getConnetion();
        PreparedStatement psd;
        try{
            psd=connection.prepareStatement(sql);
            return psd.executeUpdate();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"执行语句出错\n"+e.toString());
            e.printStackTrace();
        }
        return 0;
    }
    //关闭连接
    public static void close(ResultSet resultSet, Statement statement,Connection connection)throws Exception{
        try{
            if(resultSet!=null){
                resultSet.close();
            }
            if(statement!=null){
                statement.cancel();
                if(connection!=null){
                    connection.close();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }


}
