package com.huayu.work3.utils;

import com.huayu.work3.pojo.TbGoodsDO;
import com.huayu.work3.pojo.TbOrderDO;
import com.huayu.work3.pojo.TbOrderGoodsDO;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


/**
 *订单管理系统的JDBC工具类，包含各种修改数据库的方法
 */
public class JdbcUtils {
    private static BasicDataSource basicDataSource = null;
    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static PreparedStatement preparedStatement2 = null;
    private static ResultSet resultSet = null;

    /**
     * 处理数据库连接
     */
    static {
        InputStream resourceAsStream = com.huayu.lesson05.utils.JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            basicDataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 连接数据库
     *
     * @return 数据库对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * 查找所有的订单
     */
    public static void selectAllOrder() {
        try {
            connection = getConnection();
            String sql = "select `Id`,`time`,`price` from `tb_order`";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    System.out.println("订单编号为：" + resultSet.getObject("Id"));
                    System.out.println("下单时间为：" + resultSet.getObject("time"));
                    System.out.println("订单价格为：" + resultSet.getObject("price") + "元");
                } while (resultSet.next());
            } else {
                System.out.println("你的订单列表空空如也");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 根据订单号查找订单
     * @param order
     */
    public static void selectOrderById(TbOrderDO order) {
        try {
            connection = getConnection();
            String sql = "select `id`,`time`,`price` from `tb_order` where `id`=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("订单编号为：" + resultSet.getObject("Id"));
                System.out.println("下单时间为：" + resultSet.getObject("time"));
                System.out.println("订单价格为：" + resultSet.getObject("price") + "元");
            } else {
                System.out.println("没有符合条件的订单");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 利用订单号查找订单上的货物信息
     * @param order
     */
    public static void selectGoodsByOrderId(TbOrderDO order) {
        try {
            connection = getConnection();
            String sql = "select `name`,`price`,`quantity` from `tb_goods` as g " +
                    "INNER join `tb_order_goods` as og where g.id=og.goods_id and og.order_id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, order.getId());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    System.out.println("您购买商品包含：" + resultSet.getObject("name"));
                    System.out.println("您购买商品价格为：" + resultSet.getObject("price"));
                    System.out.println("这笔订单中您一共购买了" + resultSet.getObject("quantity") + "件");
                } while (resultSet.next());
            } else {
                System.out.println("找不到你要找的订单呢亲");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 新建一个订单
     * @param order
     * @param orderGoods
     */
    public static void addOrder(TbOrderDO order, TbOrderGoodsDO... orderGoods) {
        for (int i = 0; i < orderGoods.length; i++) {
            if (!order.getId().equals(orderGoods[i].getOrderId())) {
                System.out.println("添加一个订单时，该订单的货物应该包含在这个订单中");
                return;
            }
        }
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            String sql1 = "INSERT INTO `tb_order_goods`(`order_id`,`goods_id`,`quantity`) VALUES(?,?,?)";
            String sql2 = "INSERT INTO `tb_order`(`id`,`time`,`price`) VALUES(?,?,(select sum(`price`*`quantity`) from `tb_goods` as g " +
                    "INNER join `tb_order_goods` as og where g.id=og.goods_id and og.order_id=?))";
            preparedStatement = connection.prepareStatement(sql1);
            for (int i = 0; i < orderGoods.length; i++) {
                preparedStatement.setInt(1, orderGoods[i].getOrderId());
                preparedStatement.setInt(2, orderGoods[i].getGoodsId());
                preparedStatement.setInt(3, orderGoods[i].getQuantity());
                preparedStatement.addBatch();
            }
            preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, order.getId());
            preparedStatement2.setTimestamp(2, order.getTime());
            preparedStatement2.setInt(3, order.getId());
            preparedStatement.executeBatch();
            int i = preparedStatement2.executeUpdate();
            connection.commit();
            if (i > 0) {
                System.out.println("新增订单成功");
            } else {
                System.out.println("订单已经存在或订单号不合法");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 删除对应订单号的订单
     * @param order
     */
    public static void deleteOrderById(TbOrderDO order) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            String sql1 = "DELETE from `tb_order` where `id`=?";
            String sql2 = "DELETE from `tb_order_goods` where `order_id`=?";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setInt(1, order.getId());
            preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, order.getId());
            int i = preparedStatement.executeUpdate();
            preparedStatement2.executeUpdate();
            connection.commit();
            if (i > 0) {
                System.out.println("取消订单成功");
            } else {
                System.out.println("没有找到要取消的订单");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 删除某个订单中已经存在的货物
     * @param orderGoods
     */
    public static void deleteGoodsInOrderByOrderAndGoodsId(TbOrderGoodsDO orderGoods) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            String sql1 = "UPDATE `tb_order` SET `price`=(select `price` where id=?)" +
                    "-(select `price`*`quantity` from `tb_goods` as g INNER join `tb_order_goods` as og where g.id=og.goods_id and og.goods_id=? and og.order_id=?)";
            String sql2 = "DELETE from `tb_order_goods` where `goods_id`=?";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setInt(1, orderGoods.getOrderId());
            preparedStatement.setInt(2, orderGoods.getGoodsId());
            preparedStatement.setInt(3, orderGoods.getOrderId());
            preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, orderGoods.getGoodsId());
            int i = preparedStatement.executeUpdate();
            preparedStatement2.executeUpdate();
            connection.commit();
            if (i > 0) {
                System.out.println("取消货物成功");
            } else {
                System.out.println("没有找到要取消的货物");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 增加已经存在的订单中的货物
     * @param orderGoods
     */
    public static void addGoodsInOrderByOrderAndGoodsId(TbOrderGoodsDO orderGoods) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            String sql1 = "INSERT INTO `tb_order_goods`(`order_id`,`goods_id`,`quantity`) VALUES(?,?,?)";
            String sql2 = "UPDATE `tb_order` SET `price`=(select `price` where id=?)" +
                    "+(select `price`*`quantity` from `tb_goods` as g INNER join `tb_order_goods` as og where g.id=og.goods_id and og.goods_id=? and og.order_id=?)";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setInt(1, orderGoods.getOrderId());
            preparedStatement.setInt(2, orderGoods.getGoodsId());
            preparedStatement.setInt(3, orderGoods.getQuantity());
            preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, orderGoods.getOrderId());
            preparedStatement2.setInt(2, orderGoods.getGoodsId());
            preparedStatement2.setInt(3, orderGoods.getOrderId());
            int i = preparedStatement.executeUpdate();
            preparedStatement2.executeUpdate();
            connection.commit();
            if (i > 0) {
                System.out.println("新增货物成功");
            } else {
                System.out.println("没有找到要增加的货物");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改对应订单号订单中货物号对应货物的数量
     * @param orderGoods
     */
    public static void updateQuantityByOrderAndGoodsId(TbOrderGoodsDO orderGoods) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            String sql1 = "UPDATE `tb_order_goods` SET `quantity`=? where `goods_id`=? and `order_id`=?";
            String sql2 = "UPDATE `tb_order` SET `price`=(select sum(`price`*`quantity`) from `tb_goods` as g " +
                    "INNER join `tb_order_goods` as og where g.id=og.goods_id and og.order_id=?)";
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.setInt(1, orderGoods.getQuantity());
            preparedStatement.setInt(2, orderGoods.getGoodsId());
            preparedStatement.setInt(3, orderGoods.getOrderId());
            preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, orderGoods.getOrderId());
            int i = preparedStatement.executeUpdate();
            preparedStatement2.executeUpdate();
            connection.commit();
            if (i > 0) {
                System.out.println("更改货物数量成功");
            } else {
                System.out.println("没有找到要更改的货物");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查找所有的货物
     */
    public static void selectAllGoods() {
        try {
            connection = getConnection();
            String sql = "select `Id`,`name`,`price` from `tb_goods`";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    System.out.println("商品编号为：" + resultSet.getObject("Id"));
                    System.out.println("商品名称为：" + resultSet.getObject("name"));
                    System.out.println("商品价格为：" + resultSet.getObject("price") + "元");
                } while (resultSet.next());
            } else {
                System.out.println("你的商品列表空空如也");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 利用商品名称查找货物
     * @param goods
     */
    public static void selectGoodsByName(TbGoodsDO goods) {
        if (goods.getName() == null) {
            System.out.println("请输入商品的名称");
            return;
        }
        try {
            connection = getConnection();
            String sql = "select `id`,`Name`,`price` from `tb_goods` where `name` like ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + goods.getName() + "%");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    System.out.println("商品编号为：" + resultSet.getObject("Id"));
                    System.out.println("商品名称为：" + resultSet.getObject("name"));
                    System.out.println("商品价格为：" + resultSet.getObject("price") + "元");
                } while (resultSet.next());
            } else {
                System.out.println("没有找到你想要的货物");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 新增货物
     * @param goods
     */
    public static void addGoods(TbGoodsDO... goods) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            String sql = "INSERT INTO `tb_goods`(`name`,`price`) VALUES(?,?)";
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < goods.length; i++) {
                preparedStatement.setString(1, goods[i].getName());
                preparedStatement.setBigDecimal(2, goods[i].getPrice());
                preparedStatement.addBatch();
            }
            int[] i = preparedStatement.executeBatch();
            connection.commit();
            if (i.length > 0) {
                System.out.println("新增商品成功");
            } else {
                System.out.println("订单已经存在或订单号不合法");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 利用货物号删除一个货物
     * @param goods
     */
    public static void deleteGoodsById(TbGoodsDO... goods) {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            String sql = "DELETE from `tb_goods` where `id`=?";
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < goods.length; i++) {
                preparedStatement.setInt(1, goods[i].getId());
                preparedStatement.addBatch();
            }
            int[] i = preparedStatement.executeBatch();
            connection.commit();
            if (i.length > 0) {
                System.out.println("删除商品成功");
            } else {
                System.out.println("没有找到要删除的商品");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 订单价格降序查找所有的订单
     */
    public static void selectAllOrderPriceDesc() {
        try {
            connection = getConnection();
            String sql = "select `Id`,`time`,`price` from `tb_order` order by `price` desc ";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    System.out.println("订单编号为：" + resultSet.getObject("Id"));
                    System.out.println("下单时间为：" + resultSet.getObject("time"));
                    System.out.println("订单价格为：" + resultSet.getObject("price") + "元");
                } while (resultSet.next());
            } else {
                System.out.println("你的订单列表空空如也");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 订单价格升序查找所有的订单
     */
    public static void selectAllOrderPriceAsc() {
        try {
            connection = getConnection();
            String sql = "select `Id`,`time`,`price` from `tb_order` order by `price` ASC ";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    System.out.println("订单编号为：" + resultSet.getObject("Id"));
                    System.out.println("下单时间为：" + resultSet.getObject("time"));
                    System.out.println("订单价格为：" + resultSet.getObject("price") + "元");
                } while (resultSet.next());
            } else {
                System.out.println("你的订单列表空空如也");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 下单时间降序查找所有的订单
     */
    public static void selectAllOrderTimeDesc() {
        try {
            connection = getConnection();
            String sql = "select `Id`,`time`,`price` from `tb_order` order by `time` desc ";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    System.out.println("订单编号为：" + resultSet.getObject("Id"));
                    System.out.println("下单时间为：" + resultSet.getObject("time"));
                    System.out.println("订单价格为：" + resultSet.getObject("price") + "元");
                } while (resultSet.next());
            } else {
                System.out.println("你的订单列表空空如也");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 下单时间升序查找所有的订单
     */
    public static void selectAllOrderTimeAsc() {
        try {
            connection = getConnection();
            String sql = "select `Id`,`time`,`price` from `tb_order` order by `time` Asc ";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    System.out.println("订单编号为：" + resultSet.getObject("Id"));
                    System.out.println("下单时间为：" + resultSet.getObject("time"));
                    System.out.println("订单价格为：" + resultSet.getObject("price") + "元");
                } while (resultSet.next());
            } else {
                System.out.println("你的订单列表空空如也");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 货物价格降序查找所有的货物
     */
    public static void selectAllGoodsPriceDesc() {
        try {
            connection = getConnection();
            String sql = "select `Id`,`name`,`price` from `tb_goods` order by `price` desc";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    System.out.println("商品编号为：" + resultSet.getObject("Id"));
                    System.out.println("商品名称为：" + resultSet.getObject("name"));
                    System.out.println("商品价格为：" + resultSet.getObject("price") + "元");
                } while (resultSet.next());
            } else {
                System.out.println("你的商品列表空空如也");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 货物价格升序查找所有的货物
     */
    public static void selectAllGoodsPriceAsc() {
        try {
            connection = getConnection();
            String sql = "select `Id`,`name`,`price` from `tb_goods` order by `price` Asc";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    System.out.println("商品编号为：" + resultSet.getObject("Id"));
                    System.out.println("商品名称为：" + resultSet.getObject("name"));
                    System.out.println("商品价格为：" + resultSet.getObject("price") + "元");
                } while (resultSet.next());
            } else {
                System.out.println("你的商品列表空空如也");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 货物价格升序查找相关名称的货物
     * @param goods
     */
    public static void selectGoodsByNamePriceAsc(TbGoodsDO goods) {
        if (goods.getName() == null) {
            System.out.println("请输入商品的名称");
            return;
        }
        try {
            connection = getConnection();
            String sql = "select `id`,`Name`,`price` from `tb_goods` where `name` like ? order by `price` asc ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + goods.getName() + "%");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    System.out.println("商品编号为：" + resultSet.getObject("Id"));
                    System.out.println("商品名称为：" + resultSet.getObject("name"));
                    System.out.println("商品价格为：" + resultSet.getObject("price") + "元");
                } while (resultSet.next());
            } else {
                System.out.println("没有找到你想要的货物");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 货物价格降序查找相应名称的货物
     * @param goods
     */
    public static void selectGoodsByNamePriceDesc(TbGoodsDO goods) {
        if (goods.getName() == null) {
            System.out.println("请输入商品的名称");
            return;
        }
        try {
            connection = getConnection();
            String sql = "select `id`,`Name`,`price` from `tb_goods` where `name` like ? order by `price` Desc";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + goods.getName() + "%");
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                do {
                    System.out.println("商品编号为：" + resultSet.getObject("Id"));
                    System.out.println("商品名称为：" + resultSet.getObject("name"));
                    System.out.println("商品价格为：" + resultSet.getObject("price") + "元");
                } while (resultSet.next());
            } else {
                System.out.println("没有找到你想要的货物");
            }
            release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
