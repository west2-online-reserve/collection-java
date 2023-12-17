package base.util;
/**
 * 此类用于获得数据库连接以及释放数据库连接
 * @author 1293978818
 * @version 1.0
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

    /**获得连接的必要变量 */
    private static String driver =  "com.mysql.cj.jdbc.Driver";
    private static String url =  "jdbc:mysql://localhost:3306/order?useUnicode=true&characterEndcoding=utf8&useSSL=true";
    private static String username =  "root";
    private static String password =  "ygh8730080";

    /**使用驱动 */
    static{
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**获得连接 */
    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, username, password);
    }

    /**释放连接 */
    public static void close(ResultSet resultSet,Statement statement) throws SQLException{
        if (resultSet != null){
            resultSet.close();
        }
        if (statement != null){
            statement.close();
        }
        
    }
}
