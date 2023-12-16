package com.west2.work3;

import com.west2.peizhi.utils.JdbcUtils;

import java.sql.*;

import java.util.Date;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtils.getConnection();

            //预编译参数，先写sql，不执行.使用?占位符代替参数
            //String sql = "insert into t_student(id,`NAME`,`age`,`sex`) values(?,?,?,?)";
            //String sql = "delete from 表名 where id = ?";
            // String sql = "update 表名 set `列名`= ? where id = ?";
            String sql = "select * from 表名 where id= ?";
            st = conn.prepareStatement(sql);
            //手动给参数赋值
            st.setInt(1,4);
            st.setString(2,"linda");
            st.setInt(3,17);
            st.setString(4,"女");
            // 注意如果涉及时间参数，需要使用java.util.Date包中的new Date().getTime()获得时间戳，再转换为sql中的时间
            //st.setDate(对应参数序号，new java.sql.Date(new Date().getTime()));
            int i = st.executeUpdate();
            if(i>0){
                System.out.println("插入成功！");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            JdbcUtils.release(conn,st,rs);
        }

    }
}
