package utils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class JdbcUtils {

    private static String url ;
    private static String username ;
    private static String password ;

    static {
        try {
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);

            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 获取连接
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // 释放资源
    public static void release(Connection connection , PreparedStatement statement , ResultSet resultSet) {
        if(resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置 PreparedStatement 参数。
     *
     * @param pstmt PreparedStatement
     * @param params 参数数组
     * @throws SQLException
     */
    private static void setParameters(PreparedStatement pstmt, Object[] params) throws SQLException {
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        }
    }

    /**
     * 执行查询操作（SELECT）。
     *
     * 使用 PreparedStatement 防止 SQL 注入。
     *
     * @param sql SQL 查询语句（带 ? 占位符）
     * @param params 参数数组
     * @return ResultSet 结果集
     * @throws SQLException
     */
    public static ResultSet executeQuery(String sql, Object[] params) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        setParameters(pstmt, params);
        return pstmt.executeQuery();
    }

    /**
     * 执行更新操作（INSERT, UPDATE, DELETE）。
     *
     * 使用 PreparedStatement 防止 SQL 注入，支持事务。
     *
     * @param sql SQL 语句（带 ? 占位符）
     * @param params 参数数组
     * @return 受影响的行数
     * @throws SQLException
     */
    public static int executeUpdate(String sql, Object[] params) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false); // 开启事务
            pstmt = conn.prepareStatement(sql);
            setParameters(pstmt, params);
            int rows = pstmt.executeUpdate();
            conn.commit(); // 提交事务
            return rows;
        } catch (SQLException | ClassNotFoundException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // 回滚事务
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            release(conn, pstmt, null);
        }
    }

    /**
     * 执行批量更新操作，支持事务。
     *
     * @param sql SQL 语句
     * @param paramsList 参数列表的列表
     * @return 受影响的行数数组
     * @throws SQLException
     */
    public static int[] executeBatchUpdate(String sql, List<Object[]> paramsList) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);
            for (Object[] params : paramsList) {
                setParameters(pstmt, params);
                pstmt.addBatch();
            }
            int[] rows = pstmt.executeBatch();
            conn.commit();
            return rows;
        } catch (SQLException | ClassNotFoundException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            release(conn, pstmt, null);
        }
    }

    /**
     *
     * @param sql sql语句
     * @param params 参数组
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static int executeUpdateReturnId(String sql, Object[] params) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            // 指定返回自增主键
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            setParameters(pstmt, params);
            pstmt.executeUpdate();
            // 获取自增ID
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                conn.commit();
                return rs.getInt(1); // 返回自增的ID
            }
            conn.commit();
            return -1;
        } catch (SQLException | ClassNotFoundException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw e;
        } finally {
            release(conn, pstmt, rs);
        }
    }

    /**
     * 查询所有订单，按下单时间或价格排序。
     *
     * @param byPrice 是否按价格排序（否则按时间）
     * @param ascending 是否升序
     * @return 订单列表
     * @throws SQLException SQL执行异常
     * @throws ClassNotFoundException 驱动加载异常
     */
    public List<helperClass.Order> getAllOrders(boolean byPrice, boolean ascending) throws SQLException {
        String orderField = byPrice ? "total_price" : "time";
        String orderDir = ascending ? "ASC" : "DESC";

        String sql = "SELECT " +
                "o.order_id, o.time, o.total_price, " +
                "g.name, g.goods_id, oi.goods_num, oi.item_total " +
                "FROM orders o " +
                "INNER JOIN order_info oi ON o.order_id = oi.order_id " +
                "INNER JOIN goods g ON oi.goods_id = g.goods_id " +
                "ORDER BY o." + orderField + " " + orderDir;

        Map<Integer, helperClass.Order> orderMap = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            // 定义日期格式化器：支持一位月/日（yyyy-M-d HH:mm:ss）
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d HH:mm:ss");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d HH:mm:ss");

            while (rs.next()) {
                int orderId = rs.getInt("order_id");
                if (!orderMap.containsKey(orderId)) {
                    helperClass.Order order = new helperClass.Order();
                    order.setId(orderId);

                    // 将数据库Timestamp转为指定格式的字符串
                    String createTimeStr = sdf.format(rs.getTimestamp("time"));
                    // 用匹配的格式化器解析为LocalDateTime
                    LocalDateTime createTime = LocalDateTime.parse(createTimeStr, dtf);
                    order.setOrderTime(createTime);

                    // 金额用BigDecimal，保留两位小数
                    BigDecimal totalPrice = rs.getBigDecimal("total_price").setScale(2, RoundingMode.HALF_UP);
                    order.setOrderPrice(totalPrice);

                    orderMap.put(orderId, order);
                }

                helperClass.orderInfo item = new helperClass.orderInfo();
                item.setGoodsId(rs.getInt("goods_id"));
                item.setGoodsName(rs.getString("name"));
                item.setGoodsNum(rs.getInt("goods_num"));
                // 明细金额也保留两位小数
                BigDecimal itemTotal = rs.getBigDecimal("item_total").setScale(2, RoundingMode.HALF_UP);
                item.setTotalPrice(itemTotal);

                orderMap.get(rs.getInt("order_id")).getItemList().add(item);
            }
        } catch (SQLException | DateTimeParseException e) {
            throw new SQLException("查询订单失败：" + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            release(conn, pstmt, rs);
        }

        // 排序逻辑不变...
        List<helperClass.Order> orderList = new ArrayList<>(orderMap.values());
        orderList.sort((o1, o2) -> {
            if (byPrice) {
                return ascending ? o1.getOrderPrice().compareTo(o2.getOrderPrice())
                        : o2.getOrderPrice().compareTo(o1.getOrderPrice());
            } else {
                return ascending ? o1.getOrderTime().compareTo(o2.getOrderTime())
                        : o2.getOrderTime().compareTo(o1.getOrderTime());
            }
        });

        printOrders(orderList);
        return orderList;
    }

    /**
     * 用StringBuilder拼接并输出订单列表
     */
    private static void printOrders(List<helperClass.Order> orderList) {
        StringBuilder sb = new StringBuilder();
        for (helperClass.Order order : orderList) {
            sb.append("============").append("\n");
            sb.append("订单号：").append(order.getId()).append("\n");
            sb.append("下单时间：").append(order.getOrderTime()).append("\n");
            sb.append("\n");
            sb.append("订单明细：").append("\n");

            for (helperClass.orderInfo item : order.getItemList()) {
                String goodsDesc = item.getGoodsName() + "*" + item.getGoodsNum();
                // 金额保留两位小数
                String priceDesc = String.format("%.2f元", item.getTotalPrice());
                sb.append(String.format("%-8s | %s", goodsDesc, priceDesc)).append("\n");
            }

            sb.append("\n");
            // 订单总价保留两位小数
            sb.append(String.format("订单总价：%.2f元", order.getOrderPrice())).append("\n");
            sb.append("============").append("\n");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

}
