package JDBC;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;



public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    static{
        try{
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");

            Class.forName(driver);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = tl.get();
        // 检查连接是否有效
        if (connection != null && !connection.isClosed()) {
            return connection;
        }
        // 如果连接无效，清理ThreadLocal
        if (connection != null && connection.isClosed()) {
            tl.remove();
        }
        return DriverManager.getConnection(url,username,password);
    }

    /**
     * 释放连接资源
     * @param connection
     * @param preparedStatement
     * @param resultSet
     */
    public static void release(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        if (tl.get() == null){
            if (connection != null){
                try {
                    if (!connection.isClosed())
                        connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        if (preparedStatement != null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /**
     * 执行增删改的操作
     * @param sql SQL语句
     * @param params 占位符对应的参数
     * @return 受影响的行数(int)
     * */
    public static int executeUpdate(String sql,Object... params){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int i;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //给用？代替的占位符设置参数
            setParameters(preparedStatement,params);
            i = preparedStatement.executeUpdate();
            return i;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (tl.get() == null)
                release(connection,preparedStatement,null);
            else{
                if (preparedStatement!=null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    /**
     * 执行查询的操作
     * @param sql SQL语句
     * @param params 占位符对应的参数
     */
    public static <T> T executeQuery(String sql, ResultSetHandler<T> resultSetHandler, Object... params) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            setParameters(preparedStatement,params);
            rs = preparedStatement.executeQuery();
            if (rs != null)
                return resultSetHandler.handle(rs);
            else{
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            if (tl.get() == null){
                release(connection,preparedStatement,rs);
            } else{
                if (preparedStatement!=null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (rs!=null) {
                    try {
                        rs.close();
                    } catch (SQLException e) {
                    throw new RuntimeException(e);}
                }
            }

        }
    }
    /**
     * 给用？代替的占位符设置参数
     * @param p PreparedStatement对象
     * @param params 占位符对应的参数
     */
    private static void setParameters(PreparedStatement p,Object... params) throws SQLException {
        if (params != null && params.length > 0) {
            for (int i=1;i<=params.length;i++){
                p.setObject(i,params[i-1]);
            }
        }
    }

    /**
     * 开启事务
     */
    public static void beginTransaction(){
        try {
            Connection connection = getConnection();
            if (connection != null) {
                tl.set(connection);
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 提交事务

     */
    public static void submitTransaction(){
        Connection connection = tl.get() ;
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 回滚事务
     */
    public static void rollbackTransaction(){
        Connection connection = tl.get() ;
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 关闭数据库连接
     *
     */
    /**
     * 开启事务
     */
    public static void overTransaction(){
        Connection connection = tl.get() ;
        if (connection != null){
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                tl.remove();
            }
        }
    }
}

