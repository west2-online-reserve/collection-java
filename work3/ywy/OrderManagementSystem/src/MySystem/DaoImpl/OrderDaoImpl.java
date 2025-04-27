package MySystem.DaoImpl;

import MySystem.Entity.Goods;
import MySystem.Entity.Order;
import MySystem.MyException.GoodsNotFoundException;
import MySystem.MyException.IllegalOrderInformationException;
import MySystem.MyException.IllegalPriceException;
import MySystem.MyException.OrdersNotFoundException;
import MySystem.Tools.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class OrderDaoImpl extends BaseDaoIml<Order> {//采用两个表储存信息，一个为订单信息，另一个为订单与商品联合信息

    private final String Table1 = "orders";//主表名

    private final String Table2 = "orders_goods";//副表名

    private final String Id = "id";//主副表中元素名

    private final String Date = "date";//主表中元素名

    private final String Account = "account";//主表中元素名

    private final String OrderId = "order_id";//副表中元素名

    private final String GoodsId = "goods_id";//副表中元素名

    private final String Quantity = "quantity";//副表中元素名

    private final String allColumnsTable1 = "`id`,`date`,`account`";

    private final String allColumnsTable2 = "`id`,`order_id`,`goods_id`,`quantity`";

    private final String insertColumnsTable1 = String.format("%s,%s,%s", Id, Date, Account);

    private final String insertColumnsTable2 = String.format("%s,%s,%s", OrderId, GoodsId, Quantity);

    @Override
    public int insert(Order... entities) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = String.format(SQL_INS, Table1, insertColumnsTable1, ThreePlaceholders);//"INSERT INTO %s(%s) VALUES (%s);
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.startTransaction(conn);// 关闭自动提交，开启事务
            GoodsDaoImpl goodsDao = new GoodsDaoImpl();//用于查询商品表
            int count = 0;
            for (Order entity : entities) {
                if (entity.getAmount() < 0) {
                    throw new IllegalPriceException("错误订单金额价格："+entity.getAmount());
                }
                count += JDBCUtil.update(conn, ps, sql, entity.getId(), entity.getDate(), entity.getAmount());//插入至主表中
                Map<Goods, Integer> ordergoods = entity.getOrdergoods();//获取订单中每个商品和数量
                for (Map.Entry<Goods, Integer> entry : ordergoods.entrySet()) {//把订单中商品信息插入至副表
                    if (entry.getValue() <= 0) {//商品数量异常
                        throw new IllegalOrderInformationException("订单中商品数量错误：" + entry.getValue());
                    } else if (!goodsDao.getById(entry.getKey().getId()).equals(entry.getKey())) {//订单中存的商品的信息异常
                        throw new GoodsNotFoundException("订单中商品信息" + entry.getKey().toString() + "不存在于商品表中");
                    } else
                        count += insertInOrders_Goods(conn, entity.getId(), entry.getKey().getId(), entry.getValue());//插入副表中
                }

            }
            JDBCUtil.commitTransaction(conn);// 提交事务
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            JDBCUtil.rollbackTransaction(conn); // 回滚事务
        } finally {
            JDBCUtil.close(conn, ps, null);
        }
        return 0;
    }

    private int insertInOrders_Goods(Connection conn, int order_id, int goods_id, int quantity) {//将entry插入至副表中,只能由类中方法调用，故不做事务的开启和提交，均跟随调用方法
        PreparedStatement ps = null;

        String sql = String.format(SQL_INS, Table2, insertColumnsTable2, ThreePlaceholders);//"INSERT INTO %s(%s) VALUES (%s);
        try {
            int count = JDBCUtil.update(conn, ps, sql, order_id, goods_id, quantity);//插入至副表中，//正确性应在调用方法中检测
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalOrderInformationException("插入订单中货物信息至副表时错误");
        }
    }

    @Override
    public int update(Order ...entities) {//对于新的订单只要求符合常理（如数量为正数），副表中（与订单号一致）旧的信息全部删除或修改,后插入新订单中的商品信息
        Connection conn = null;
        PreparedStatement ps = null;
        //UPDATE %s SET %s=?,%s=?,%s=? WHERE %s;
        String sql = String.format(SQL_UPDATE, Table1, Id, Date, Account, String.format("%s=%s", Id, OnePlaceholders));
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.startTransaction(conn);// 关闭自动提交，开启事务

            GoodsDaoImpl goodsDao = new GoodsDaoImpl();//用于查询商品表
            int count = 0;
            for (Order entity : entities) {
                if (entity.getAmount() < 0) {
                    throw new IllegalPriceException("非法订单金额"+entity.getAmount());
                }
                if (!getById(entity.getId()).equals(entity)) {//主表信息不一致需要更新
                    count += JDBCUtil.update(conn, ps, sql, entity.getId(), entity.getDate(), entity.getAmount(), entity.getId());//更新主表的信息
                }
                for (Map.Entry<Goods, Integer> entry : entity.getOrdergoods().entrySet()) {//遍历检测新订单中商品信息是否异常
                    if (entry.getValue() <= 0) {//数量异常
                        throw new IllegalOrderInformationException("订单的商品数量错误：" + entry.getValue());
                    } else if (!goodsDao.getById(entry.getKey().getId()).equals(entry.getKey())) {//订单中存的商品异常
                        throw new GoodsNotFoundException("订单中商品信息" + entry.getKey().toString() + "不存在于商品表中");
                    }
                }
                count += updateInOrders_Goods(conn, entity.getId(), entity.getOrdergoods());//修改副表中信息
            }
            JDBCUtil.commitTransaction(conn);// 提交事务
            return count;// 返回两表更新的行数
        } catch (Exception e) {
            e.printStackTrace();
            JDBCUtil.rollbackTransaction(conn); // 回滚事务
            System.out.println("sdsdsdssdds");
        } finally {
            JDBCUtil.close(conn, ps, null);
        }
        return 0;//表示未更新表
    }

    private int updateInOrders_Goods(Connection conn, int orderId, Map<Goods, Integer> newOrdergoods) {//更新副表，只能由类中方法调用，故不做事务的开启和提交，均跟随调用方法
        PreparedStatement ps = null;
        Map<Goods, Integer> combineOrdergoods = getById(orderId).getOrdergoods();//新map与旧map的结合，先初始为原订单的商品信息
        Map<Goods, Integer> oldOrdergoods = getById(orderId).getOrdergoods();//旧map
        combineOrdergoods.putAll(newOrdergoods);//若已存在会被替换
        System.out.println(combineOrdergoods);
        System.out.println(oldOrdergoods);
        System.out.println(newOrdergoods);
        //"UPDATE %s SET %s=?,%s=?,%s=? WHERE %s;
        String sql = String.format(SQL_UPDATE, Table2, OrderId, GoodsId, Quantity, String.format("%s=%s AND %s=%s", OrderId, OnePlaceholders, GoodsId, OnePlaceholders));
        try {//信息正确性应在调用方法中检测
            int count = 0;
            for (Map.Entry<Goods, Integer> entry : combineOrdergoods.entrySet()) {//遍历结合表的信息，其信息正确性应在调用方法中检测
                if (oldOrdergoods.containsKey(entry.getKey())&&newOrdergoods.containsKey(entry.getKey())) {//旧MAP中的key值若在新MAp中存在，则更新
                    if (!oldOrdergoods.get(entry.getKey()).equals(newOrdergoods.get(entry.getKey()))) {//信息不相同则更新
                        count += JDBCUtil.update(conn, ps, sql, orderId, entry.getKey().getId(), entry.getValue(),orderId,entry.getKey().getId());//修改副表，返回更新的行数
                    }
                } else
                    if (newOrdergoods.containsKey(entry.getKey())) {//在新MAP中存在且旧MAP中不存在则插入
                        count+=insertInOrders_Goods(conn, orderId, entry.getKey().getId(), entry.getValue());
                    }else {
                        count += deleteByIdOrders_Goods(conn, orderId, entry.getKey().getId());//在新MAP中不存在且旧MAP中存在则删除，删除副表中的信息
                    }
            }
            return count;// 返回副表更新的行数
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalOrderInformationException("更新副表信息时错误");
        }
    }

    @Override
    public int deleteById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = String.format(SQL_DEL, Table1, String.format("%s=%s", Id, OnePlaceholders));//"DELETE FROM %s WHERE %s;
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.startTransaction(conn);// 关闭自动提交，开启事务
            int count = JDBCUtil.update(conn, ps, sql, id);//删除主表
            count += deleteByIdOrders_Goods(conn, id);//删除副表
            JDBCUtil.commitTransaction(conn);// 提交事务
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            JDBCUtil.rollbackTransaction(conn);// 回滚事务
        } finally {
            JDBCUtil.close(conn, ps, null);
        }
        return 0;
    }

    private int deleteByIdOrders_Goods(Connection conn, int orderId) throws Exception {//删除副表中选择订单中的所有商品,只能由类中方法调用，故不做事务的开启和提交，均跟随调用方法
        PreparedStatement ps = null;

        String sql = String.format(SQL_DEL, Table2, String.format("%s=%s", OrderId, OnePlaceholders));//"DELETE FROM %s WHERE %s;

        try {
            int count = JDBCUtil.update(conn, ps, sql, orderId);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("删除副表时出现异常"+sql);
        }
    }

    private int deleteByIdOrders_Goods(Connection conn, int orderId, int goodsId) throws Exception {//删除副表中一行,只能由类中方法调用，故不做事务的开启和提交，均跟随调用方法
        PreparedStatement ps = null;
        //"DELETE FROM %s WHERE %s;
        String sql = String.format(SQL_DEL, Table2, String.format("%s=%s AND %s=%s", OrderId, OnePlaceholders, GoodsId, OnePlaceholders));
        try {
            int count = JDBCUtil.update(conn, ps, sql, orderId, goodsId);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("删除副表时出现异常"+sql);
        }
    }

    @Override
    public Order getById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = String.format(SQL_SEL_BY, allColumnsTable1, Table1, String.format("%s=%s", Id, OnePlaceholders));//SELECT %s FROM %S WHERE %s;;
        try {
            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql, id);
            if (rs.next()) {
                return new Order(rs.getInt(Id), getByIdInOrders_Goods(id), rs.getDate(Date), rs.getDouble(Account));
            } else {
                throw new GoodsNotFoundException("查询不到对应产品ID为：" + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return null;
    }

    public HashMap<Goods, Integer> getByIdInOrders_Goods(int id) {//在副表中通过订单ID获取map的商品信息
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = String.format(SQL_SEL_BY, allColumnsTable2, Table2, String.format("%s=%s", OrderId, OnePlaceholders));//SELECT %s FROM %S WHERE %s;;
        try {
            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql, id);

            GoodsDaoImpl dao = new GoodsDaoImpl();//用于查询商品表
            HashMap<Goods, Integer> map = new HashMap<>();
            if (!rs.next()) {
                throw new IllegalOrderInformationException("在副表中查询不到对应订单ID为：" + id);
            }
            do {
                map.put(dao.getById(rs.getInt(GoodsId)), rs.getInt(Quantity));
            } while (rs.next());
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public ArrayList<Order> getByAmountAbove(int amount) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = String.format(SQL_SEL_BY_SORT, allColumnsTable1, Table1, String.format("%s>=%d", Account, amount), Account);//SELECT %s FROM %s WHERE %s ORDER BY %s;
        ArrayList<Order> orders = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql);

            if (!rs.next()) {
                throw new GoodsNotFoundException("查询不到对应产品金额为：" + amount + "以上的商品");
            }
            do {
                orders.add(new Order(rs.getInt(Id), getByIdInOrders_Goods(rs.getInt(Id)), rs.getDate(Date), rs.getDouble(Account)));
            } while (rs.next());
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public ArrayList<Order> getByAmountBelow(int amount) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = String.format(SQL_SEL_BY_SORT, allColumnsTable1, Table1, String.format("%s<=%d", Account, amount), Account);//SELECT %s FROM %s WHERE %s ORDER BY %s;

        ArrayList<Order> orders = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql);

            if (!rs.next()) {
                throw new GoodsNotFoundException("查询不到对应产品金额为：" + amount + "以上的商品");
            }
            do {
                orders.add(new Order(rs.getInt(Id), getByIdInOrders_Goods(rs.getInt(Id)), rs.getDate(Date), rs.getDouble(Account)));
            } while (rs.next());
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return null;
    }


    @Override
    public ArrayList<Order> getByPage(int pagenum, int linage) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Order> orders = new ArrayList<>();
        try {
            int rows = getCount();//行数
            int totalPage = (rows % linage != 0) ? rows / linage + 1 : rows / linage;//总页数

            if (pagenum < 1) {
                throw new OrdersNotFoundException("非法页码");
            }
            if (pagenum > totalPage) {
                throw new OrdersNotFoundException("所查询的页码超出最大页数:" + totalPage);
            }

            String sql = String.format(SQL_SEL_BY_PAGE, allColumnsTable1, Table1, true, (pagenum - 1) * linage, linage);// = "SELECT %s FROM %s WHERE %s LIMIT %d, %d;"

            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql);

            if (!rs.next()) {
                throw new GoodsNotFoundException("表中无任何商品");
            }
            do {
                orders.add(new Order(rs.getInt(Id), getByIdInOrders_Goods(rs.getInt(Id)), rs.getDate(Date), rs.getDouble(Account)));
            } while (rs.next());
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }

        return null;
    }

    @Override
    public ArrayList<Order> getByPage(String sortWay, int pagenum, int linage) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String condition = null;//排序的条件
        sortWay = sortWay.toLowerCase();//转小写以便匹配
        if (sortWay.equals(Account)) {
            condition = Account;
        } else if (sortWay.equals(Date)) {
            condition = Date;
        } else if (sortWay.equals(Id)) {
            condition = Id;
        } else {
            throw new RuntimeException("错误的排序条件");
        }

        ArrayList<Order> orders = new ArrayList<>();
        try {
            int rows = getCount();//行数
            int totalPage = (rows % linage != 0) ? rows / linage + 1 : rows / linage;//总页数

            if (pagenum < 1) {
                throw new GoodsNotFoundException("非法页码");
            }
            if (pagenum > totalPage) {
                throw new GoodsNotFoundException("所查询的页码超出最大页数:" + totalPage);
            }

            String sql = String.format(SQL_SEL_BY_SORT_PAGE, allColumnsTable1, Table1, true, condition, (pagenum - 1) * linage, linage);// SELECT %s FROM %s WHERE %s ORDER BY %s LIMIT %d, %d

            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql);

            if (!rs.next()) {
                throw new GoodsNotFoundException("表中无任何商品");
            }
            do {
                orders.add(new Order(rs.getInt(Id), getByIdInOrders_Goods(rs.getInt(Id)), rs.getDate(Date), rs.getDouble(Account)));
            } while (rs.next());

            return orders;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }

        return null;
    }

    @Override
    public ArrayList<Order> getAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = String.format(SQL_SEL, allColumnsTable1, Table1);//SELECT %S FROM %S;"

        ArrayList<Order> orders = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql);
            if (!rs.next()) {
                throw new GoodsNotFoundException("表中无任何商品");
            }
            do {
                orders.add(new Order(rs.getInt(Id), getByIdInOrders_Goods(rs.getInt(Id)), rs.getDate(Date), rs.getDouble(Account)));
            } while (rs.next());
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public ArrayList<Order> getAll(String sortWay) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String condition = null;//排序的条件
        sortWay = sortWay.toLowerCase();//转小写以便匹配
        if (sortWay.equals(Account)) {
            condition = Account;
        } else if (sortWay.equals(Date)) {
            condition = Date;
        } else if (sortWay.equals("id")) {
            condition = Id;
        } else {
            throw new RuntimeException("错误的排序条件");
        }

        String sql = String.format(SQL_SEL_SORT, allColumnsTable1, Table1, condition);//SELECT %S FROM %S ORDER BY %s;

        ArrayList<Order> orders = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql);

            if (!rs.next()) {
                throw new GoodsNotFoundException("表中无任何商品");
            }
            do {
                orders.add(new Order(rs.getInt(Id), getByIdInOrders_Goods(rs.getInt(Id)), rs.getDate(Date), rs.getDouble(Account)));
            } while (rs.next());
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public int getCount() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int count = 0;
        String sql = String.format(SQL_COUNT, Id, Table1, true);//SELECT COUNT(%s) FROM %s WHERE %s;";
        try {
            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql, null);

            if (rs.next()) {
                count = rs.getInt(String.format("COUNT(%s)", Id));
            } else {
                throw new GoodsNotFoundException("查询不到产品记录的数量");

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return count;
    }
}

