package util;

import java.net.ConnectException;
import java.sql.*;

public class JDBC {
    //释放资源
    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) throws Exception {
        try {
            if (rs != null)
                rs.close();
            if (pstmt != null)
                pstmt.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //获取连接方法
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/oms";
        String name = "root";
        String password = "123456";
        Class.forName("com.mysql.cj.jdbc.Driver");//注册驱动
        return DriverManager.getConnection(url, name, password);
    }

    //事务管理功能
    public static void begin() throws Exception {
        Connection conn = getConnection();
        conn.setAutoCommit(false); // 手动开启事务管理
        close(conn,null,null);
    }

    public static void commit() throws Exception {
        Connection conn = getConnection();
        conn.commit();
        close(conn,null,null);
    }
    public static void rollback() throws Exception {
        Connection conn = getConnection();
        conn.rollback();
        close(conn,null,null);

    }



    //执行增删查改操作


    //1.增
    public static void addOrder(int productId, Timestamp orderTime, double orderPrice) throws Exception {
        try {
            //检查订单价格是否合法
            if(orderPrice<=0) {
                throw new IllegalArgumentException("订单价格不合法！！");
            }
            // 检查商品是否存在
            Connection conn = getConnection();
            String checkProductSql = "SELECT * FROM product WHERE product_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(checkProductSql);
            pstmt.setInt(1, productId);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next() || rs.getBoolean("deleted")) {
                // 如果没有找到商品，抛出异常
                close(conn, pstmt, rs);
                System.out.println("商品不存在！");
                throw new IllegalArgumentException("Product with ID " + productId + " does not exist.");
            } else {
                close(null, pstmt, rs);
            }
            String sql = "insert into `order` (product_id,order_time,order_price) values" +
                    "( ?,?,?)";

            PreparedStatement pstmt2 = ((Connection) conn).prepareStatement(sql);//使用preparedStatement对象防止sql注入问题
            pstmt2.setInt(1, productId);
            pstmt2.setTimestamp(2, orderTime);
            pstmt2.setDouble(3, orderPrice);

            int count = pstmt.executeUpdate();
            if (count > 0)
                System.out.println("添加成功！");
            else
                System.out.println("添加失败");
            close(conn, pstmt2, null);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    public static void addProduct(String productName, double productPrice) throws Exception {
        Connection conn = getConnection();
        String sql = "insert into `product` (product_name,product_price) values" +
                "( ?,?)";

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, productName);
        pstmt.setDouble(2, productPrice);
        int count = pstmt.executeUpdate();
        if (count > 0)
            System.out.println("添加成功！");
        else
            System.out.println("添加失败");

        close(conn, pstmt, null);
    }


    //2.删
    public static void deleteProduct(int productId) throws Exception {
        Connection conn = getConnection();
        String sql = "update product set deleted = 1 where product_id =? ";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, productId);
        int count = pstmt.executeUpdate();
        if (count > 0)
            System.out.println("删除成功");
        else
            System.out.println("删除失败");
        close(conn, pstmt, null);
    }

    public static void deleteOrder(int orderId) throws Exception {
        Connection conn = getConnection();
        String sql = "delete from `order` where order_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, orderId);
        int count = pstmt.executeUpdate();
        if (count > 0)
            System.out.println("订单删除成功");
        else
            System.out.println("订单删除失败");
        close(conn, pstmt, null);
    }


    //3.改
    public static void update(String sql, Object... args) throws Exception {
        //Object... args 是一个可变参数（varargs）的语法。
        // 这种语法允许你在调用方法时传入可变数量的参数，而这些参数被当做一个数组来处理。
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        int count = pstmt.executeUpdate();
        if (count > 0)
            System.out.println("修改成功");
        else
            System.out.println("修改失败");
        close(conn, pstmt, null);
    }


    //4.查
    public static void query(String sql, Object... args) throws Exception {
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        ResultSet rs = pstmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();//有关结果集的结构和内容的元数据
        while (rs.next()) {
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnLabel(i + 1) + ":" + rs.getObject(i + 1) + " ");
            }
            System.out.println();
        }
        close(conn, pstmt, rs);
    }


}
