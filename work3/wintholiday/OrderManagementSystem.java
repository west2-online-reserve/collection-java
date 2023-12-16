package 订单;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderManagementSystem {
    // 添加商品
    public static void addProduct(int productId, String productName, double productPrice) {
        if (isProductExist(productId)) {
            System.out.println("商品编号 " + productId + " 已存在");
            return;
        }

        String sql = "INSERT INTO products (product_id, product_name, product_price) VALUES (?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false); // 开启事务
            stmt.setInt(1, productId);
            stmt.setString(2, productName);
            stmt.setDouble(3, productPrice);
            stmt.executeUpdate();
            conn.commit(); // 提交事务
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除商品
    public static void deleteProduct(int productId) {
        if (!isProductExist(productId)) {
            System.out.println("商品编号 " + productId + " 不存在");
            return;
        }

        String sql = "DELETE FROM products WHERE product_id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false); // 开启事务
            stmt.setInt(1, productId);
            stmt.executeUpdate();
            conn.commit(); // 提交事务
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询所有商品（分页查询）
    public static List<Product> getAllProducts(int page) {
        List<Product> productList = new ArrayList<>();
        int offset = (page - 1) * 10; // 计算偏移量

        String sql = "SELECT * FROM products LIMIT ?, ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, offset);
            stmt.setInt(2, 10);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                double productPrice = resultSet.getDouble("product_price");

                Product product = new Product(productId, productName, productPrice);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productList;
    }

    // 创建订单
    public static void createOrder(int orderId, List<Product> products) {
        double totalPrice = 0;

        for (Product product : products) {
            if (!isProductExist(product.getProductId())) {
                System.out.println("商品编号 " + product.getProductId() + " 不存在");
                return;
            }
            totalPrice += product.getProductPrice();
        }

        String sql = "INSERT INTO orders (order_id, product_info, order_time, order_price) VALUES (?, ?, NOW(), ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false); // 开启事务
            stmt.setInt(1, orderId);
            stmt.setString(2, getProductInfo(products));
            stmt.setDouble(3, totalPrice);
            stmt.executeUpdate();
            conn.commit(); // 提交事务
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除订单
    public static void deleteOrder(int orderId) {
        String sql = "DELETE FROM orders WHERE order_id = ?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false); // 开启事务
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
            conn.commit(); // 提交事务
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 获取所有订单信息
    public static List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                String productInfo = resultSet.getString("product_info");
                String orderTime = resultSet.getString("order_time");
                double orderPrice = resultSet.getDouble("order_price");

                List<Product> products = parseProductInfo(productInfo);

                Order order = new Order(orderId, products, orderTime, orderPrice);
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderList;
    }

    // 对订单按价格排序
    public static List<Order> sortOrdersByPrice(List<Order> orders) {
        List<Order> sortedList = new ArrayList<>(orders);
        Collections.sort(sortedList, (o1, o2) -> Double.compare(o1.getOrderPrice(), o2.getOrderPrice()));
        return sortedList;
    }

    // 对订单按下单时间排序
    public static List<Order> sortOrdersByTime(List<Order> orders) {
        List<Order> sortedList = new ArrayList<>(orders);
        Collections.sort(sortedList, (o1, o2) -> o1.getOrderTime().compareTo(o2.getOrderTime()));
        return sortedList;
    }

    // 检查商品是否存在
    static boolean isProductExist(int productId) {
        String sql = "SELECT EXISTS(SELECT 1 FROM products WHERE product_id = ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // 获取商品信息字符串（用于存储到订单）
    private static String getProductInfo(List<Product> products) {
        StringBuilder productInfo = new StringBuilder();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            productInfo.append(product.getProductId()).append(":").append(product.getProductName());
            if (i != products.size() - 1) {
                productInfo.append(",");
            }
        }

        return productInfo.toString();
    }

    // 解析商品信息字符串
    private static List<Product> parseProductInfo(String productInfo) {
        List<Product> productList = new ArrayList<>();
        String[] products = productInfo.split(",");

        for (String product : products) {
            String[] info = product.split(":");
            int productId = Integer.parseInt(info[0]);
            String productName = info[1];

            Product p = new Product(productId, productName);
            productList.add(p);
        }

        return productList;
    }
}
