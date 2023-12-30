package base.util;
/**
 * 此类用于获得数据库连接以及释放数据库连接
 * @author 1293978818
 * @version 1.0
 */

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {

    /**获得连接的必要变量 */
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    /**使用驱动 */
    static{
        
        Properties properties = new Properties();
        ClassLoader classLoader = JdbcUtil.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("order.properties");
        try {
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");

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
