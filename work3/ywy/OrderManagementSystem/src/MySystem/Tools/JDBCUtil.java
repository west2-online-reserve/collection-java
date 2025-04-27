package MySystem.Tools;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");//加载驱动
            Properties prop = new Properties();
            prop.load(new FileReader("src\\MySystem\\driver.properties"));//获取项目中文件的信息
            url = prop.getProperty("url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //与数据库获取链接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    //关闭连接和其他
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try{
            if(rs!=null) rs.close();
            if(ps!=null) ps.close();
            if(conn!=null) conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    //执行sql语句查询等
    public static ResultSet select(Connection conn,PreparedStatement ps,String sql,Object... objs){
        try {
            ps=conn.prepareStatement(sql);
            System.out.println(sql);//test////////////////
            if(objs!=null){
                int i=1;
                for(Object obj:objs){
                    ps.setObject(i++, obj);
                }
            }
            return ps.executeQuery();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //进行sql语句删除和更新等
    public static int update(Connection conn,PreparedStatement ps,String sql,Object... objs){
        try{
            ps=conn.prepareStatement(sql);
            if(objs!=null){
                int i=1;
                for(Object obj:objs){
                    ps.setObject(i++, obj);
                }
            }
            return ps.executeUpdate();
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    //开启事务
    public static void startTransaction(Connection conn)  {
        try {
            conn.setAutoCommit(false);// 关闭自动提交:
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //关闭事务
    public static void commitTransaction(Connection conn) {
        try {
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //回滚事务
    public static void rollbackTransaction(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
