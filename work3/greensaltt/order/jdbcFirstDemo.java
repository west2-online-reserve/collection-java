package com.greensalt.order;

import java.sql.*;


public class jdbcFirstDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //
        String url="jdbc:mysql://localhost:3306/jdbcstudy?useUnicode=true&characterEncoding=utf8&useSSl=true";
        String username = "root";
        String password = "42365879abc.@";

        // 连接
        Connection connection = DriverManager.getConnection(url,username,password);

        // 执行对象
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM users";

        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            System.out.println("id=" + resultSet.getObject("id"));
            System.out.println("name=" + resultSet.getObject("NAME"));
            System.out.println("pwd=" + resultSet.getObject("PASSWORD"));
            System.out.println("email=" + resultSet.getObject("email"));
            System.out.println("birth=" + resultSet.getObject("birthday"));
        }

        // 释放
        resultSet.close();
        connection.close();
        statement.close();




    }
}
