import utils.JdbcUtils;
import utils.helperClass;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class orderSystemMain {
    static Scanner scanner = new Scanner(System.in);
    static JdbcUtils jdbcUtils = new JdbcUtils(); // 实例化JdbcUtils
    static final RoundingMode ROUND_MODE = RoundingMode.HALF_UP;

    public static void main(String[] args) {
        try {
            showMenu();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("系统运行异常：" + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    // 系统菜单
    private static void showMenu() throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("\n===== 订单管理系统 =====");
            System.out.println("1. 新增商品（ID默认自增）");
            System.out.println("2. 新增订单（ID默认自增）");
            System.out.println("3. 查询所有商品");
            System.out.println("4. 查询所有订单（按时间/价格排序）");
            System.out.println("5. 删除商品");
            System.out.println("6. 退出系统");
            System.out.print("请选择操作：");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addGoods();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    queryAllGoods();
                    break;
                case 4:
                    queryAllOrders();
                    break;
                case 5:
                    deleteGoods();
                    break;
                case 6:
                    System.out.println("系统已退出");
                    return;
                default:
                    System.out.println("无效操作，请重新选择");
            }
        }
    }

    /**
     * 新增商品（goods_id自增，无需手动输入）
     */
    static void addGoods() throws SQLException, ClassNotFoundException {
        System.out.print("请输入商品名称：");
        String name = scanner.nextLine();
        System.out.print("请输入商品价格：");
        double priceInput = scanner.nextDouble();

        // 转换为BigDecimal，保留两位小数
        BigDecimal price = BigDecimal.valueOf(priceInput).setScale(2, ROUND_MODE);
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("商品价格不合法（需大于0）");
            return;
        }

        String sql = "INSERT INTO goods(name, price) VALUES (?, ?)";
        Object[] params = {name, price};
        // 获取自增的goods_id
        int goodsId = JdbcUtils.executeUpdateReturnId(sql, params);
        if (goodsId > 0) {
            // 输出保留两位小数
            System.out.printf("商品新增成功！自增商品ID：%d，价格：%.2f元%n", goodsId, price);
        } else {
            System.out.println("商品新增失败");
        }
    }

    /**
     * 新增订单（order_id自增，无需手动输入）
     */
    static void addOrder() throws SQLException, ClassNotFoundException {
        System.out.print("请输入订单包含的商品数量：");
        int itemCount = scanner.nextInt();
        scanner.nextLine();

        BigDecimal orderTotal = BigDecimal.ZERO;
        List<Object[]> itemParamsList = new ArrayList<>();

        // 收集所有商品明细，计算订单总价
        for (int i = 0; i < itemCount; i++) {
            System.out.print("请输入第" + (i + 1) + "个商品的ID：");
            int goodsId = scanner.nextInt();
            System.out.print("请输入该商品的购买数量：");
            int goodsNum = scanner.nextInt();
            scanner.nextLine();

            if (!isGoodsExists(goodsId)) {
                System.out.println("商品ID不存在，订单创建失败");
                return;
            }
            if (goodsNum <= 0) {
                System.out.println("购买数量不合法（需大于0）");
                return;
            }

            // 计算商品小计和订单总价（全用BigDecimal）
            BigDecimal goodsPrice = getGoodsPrice(goodsId);
            BigDecimal itemTotal = goodsPrice.multiply(BigDecimal.valueOf(goodsNum)).setScale(2, ROUND_MODE);
            orderTotal = orderTotal.add(itemTotal).setScale(2, ROUND_MODE);

            // 先暂存商品明细（order_id后续填充）
            itemParamsList.add(new Object[]{goodsId, goodsNum, itemTotal});
        }

        // 插入订单主表，获取自增的order_id
        String orderSql = "INSERT INTO orders(time, total_price) VALUES (?, ?)";
        // 下单时间用当前时间，数据库time字段为DATETIME
        String orderTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Object[] orderParams = {orderTime, orderTotal};
        int orderId = JdbcUtils.executeUpdateReturnId(orderSql, orderParams);

        if (orderId <= 0) {
            System.out.println("订单主表创建失败");
            return;
        }

        // 3. 填充订单明细的order_id，批量插入
        String itemSql = "INSERT INTO order_info(order_id, goods_id, goods_num, item_total) VALUES (?, ?, ?, ?)";
        List<Object[]> finalItemParams = new ArrayList<>();
        for (Object[] params : itemParamsList) {
            // 拼接order_id + 原有参数
            finalItemParams.add(new Object[]{orderId, params[0], params[1], params[2]});
        }

        JdbcUtils.executeBatchUpdate(itemSql, finalItemParams);
        // 输出保留两位小数
        System.out.printf("订单创建成功！自增订单ID：%d，订单总价：%.2f元%n", orderId, orderTotal);
    }

    /**
     * 查询所有商品（显示自增的goods_id，金额保留两位小数）
     */
    static void queryAllGoods() throws SQLException, ClassNotFoundException {
        String sql = "SELECT goods_id, name, price FROM goods ORDER BY goods_id";
        ResultSet rs = JdbcUtils.executeQuery(sql, null);

        System.out.println("\n===== 商品列表 =====");
        while (rs.next()) {
            // 读取为BigDecimal，保证精度
            BigDecimal price = rs.getBigDecimal("price").setScale(2, ROUND_MODE);
            System.out.printf("商品ID：%d，名称：%s，价格：%.2f元%n",
                    rs.getInt("goods_id"),
                    rs.getString("name"),
                    price);
        }
        JdbcUtils.release(null, null, rs);
    }

    /**
     * 查询所有订单（适配自增INT类型order_id）
     */
    static void queryAllOrders() throws SQLException, ClassNotFoundException {
        System.out.print("是否按价格排序？（1=是/0=否）：");
        boolean byPrice = scanner.nextInt() == 1;
        System.out.print("是否升序排列？（1=是/0=否）：");
        boolean ascending = scanner.nextInt() == 1;

        List<helperClass.Order> orderList = jdbcUtils.getAllOrders(byPrice, ascending);
    }

    /**
     * 删除商品（校验自增goods_id）
     */
    static void deleteGoods() throws SQLException, ClassNotFoundException {
        System.out.print("请输入要删除的商品ID（自增ID）：");
        int goodsId = scanner.nextInt();

        // 检查商品是否被订单关联
        String checkSql = "SELECT COUNT(*) FROM order_info WHERE goods_id = ?";
        ResultSet rs = JdbcUtils.executeQuery(checkSql, new Object[]{goodsId});
        rs.next();
        if (rs.getInt(1) > 0) {
            System.out.println("该商品已被订单关联，无法删除");
            JdbcUtils.release(null, null, rs);
            return;
        }
        JdbcUtils.release(null, null, rs);

        // 执行删除
        String deleteSql = "DELETE FROM goods WHERE goods_id = ?";
        int rows = JdbcUtils.executeUpdate(deleteSql, new Object[]{goodsId});
        if (rows > 0) {
            System.out.println("商品删除成功");
        } else {
            System.out.println("商品ID不存在，删除失败");
        }
    }

    // 辅助方法：检查商品是否存在
    static boolean isGoodsExists(int goodsId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM goods WHERE goods_id = ?";
        ResultSet rs = JdbcUtils.executeQuery(sql, new Object[]{goodsId});
        rs.next();
        boolean exists = rs.getInt(1) > 0;
        JdbcUtils.release(null, null, rs);
        return exists;
    }

    // 辅助方法：获取商品价格（返回BigDecimal，保证精度）
    static BigDecimal getGoodsPrice(int goodsId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT price FROM goods WHERE goods_id = ?";
        ResultSet rs = JdbcUtils.executeQuery(sql, new Object[]{goodsId});
        rs.next();
        // 读取为BigDecimal并保留两位小数
        BigDecimal price = rs.getBigDecimal("price").setScale(2, ROUND_MODE);
        JdbcUtils.release(null, null, rs);
        return price;
    }
}