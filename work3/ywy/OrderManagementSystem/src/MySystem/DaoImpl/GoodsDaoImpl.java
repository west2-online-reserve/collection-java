package MySystem.DaoImpl;

import MySystem.Entity.Goods;
import MySystem.MyException.GoodsNotFoundException;
import MySystem.MyException.IllegalPriceException;
import MySystem.Tools.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GoodsDaoImpl extends BaseDaoIml<Goods> {

    private final String Table = "goods";//表名

    private final String Id = "id";//表中元素名

    private final String Name = "name";//表中元素名

    private final String Price = "price";//表中元素名

    private final String allColumns = "`id`,`name`,`price`";

    private final String insertColumns = String.format("%s,%s,%s", Id, Name, Price);

    @Override
    public int insert(Goods... entities) {
        Connection conn = null;
        PreparedStatement ps = null;

        String sql = String.format(SQL_INS, Table, insertColumns, ThreePlaceholders);//"INSERT INTO %s(%s) VALUES (%s);
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.startTransaction(conn);// 关闭自动提交，开启事务
            int n = 0;
            for (Goods entity : entities) {
                if (entity.getPrice() < 0) {
                    throw new IllegalPriceException("非法商品价格");
                }
                n += JDBCUtil.update(conn, ps, sql, entity.getId(), entity.getName(), entity.getPrice());//
            }
            JDBCUtil.commitTransaction(conn);// 提交事务
            return n;

        } catch (Exception e) {
            e.printStackTrace();
            JDBCUtil.rollbackTransaction(conn); // 回滚事务

        } finally {
            JDBCUtil.close(conn, ps, null);
        }
        return 0;
    }

    @Override
    public int update(Goods... entities) {
        Connection conn = null;
        PreparedStatement ps = null;
        //UPDATE %s SET %s=?,%s=?,%s=? WHERE %s;
        String sql = String.format(SQL_UPDATE, Table, Id, Name, Price, String.format("%s=%s", Id, OnePlaceholders));
        try {
            conn = JDBCUtil.getConnection();
            JDBCUtil.startTransaction(conn);// 关闭自动提交，开启事务
            int count=0;
            for (Goods entity : entities) {
                if (entity.getPrice() < 0) {
                    throw new IllegalPriceException("非法价格：" + entity.getPrice());
                }
                if(!entity.equals(getById(entity.getId()))) {//商品表信息不一致需要更新
                    count += JDBCUtil.update(conn, ps, sql, entity.getId(), entity.getName(), entity.getPrice(), entity.getId());
                }
            }
            JDBCUtil.commitTransaction(conn);// 提交事务
            return count;// 返回更新的行数
        } catch (Exception e) {
            e.printStackTrace();
            JDBCUtil.rollbackTransaction(conn); // 回滚事务
        } finally {
            JDBCUtil.close(conn, ps, null);
        }
        return 0;//表示未更新表
    }

    @Override
    public int deleteById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        //"DELETE FROM %s WHERE %s;
        String sql = String.format(SQL_DEL, Table, String.format("%s=%s", Id, OnePlaceholders));
        try {
            conn = JDBCUtil.getConnection();// 关闭自动提交，开启事务
            JDBCUtil.startTransaction(conn);
            int n = JDBCUtil.update(conn, ps, sql, id);
            JDBCUtil.commitTransaction(conn);// 提交事务
            return n;
        } catch (Exception e) {
            e.printStackTrace();
            JDBCUtil.rollbackTransaction(conn);// 回滚事务
        } finally {
            JDBCUtil.close(conn, ps, null);
        }
        return 0;
    }

    @Override
    public Goods getById(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //SELECT %s FROM %S WHERE %s;;
        String sql = String.format(SQL_SEL_BY, allColumns, Table, String.format("%s=%s", Id, OnePlaceholders));
        try {
            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql, id);
            if (rs.next()) {
                return new Goods(rs.getInt(Id), rs.getString(Name), rs.getDouble(Price));
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

    @Override
    public ArrayList<Goods> getByAmountAbove(int amount) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //SELECT %s FROM %s WHERE %s ORDER BY %s;
        String sql = String.format(SQL_SEL_BY_SORT, allColumns, Table, String.format("%s>=%d", Price, amount), Price);
        ArrayList<Goods> goods = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql);

            if (!rs.next()) {
                throw new GoodsNotFoundException("查询不到对应产品金额为：" + amount + "以上的产品");
            }
            do {
                goods.add(new Goods(rs.getInt(Id), rs.getString(Name), rs.getDouble(Price)));
            } while (rs.next());
            return goods;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public ArrayList<Goods> getByAmountBelow(int amount) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //SELECT %s FROM %s WHERE %s ORDER BY %s;
        String sql = String.format(SQL_SEL_BY_SORT, allColumns, Table, String.format("%s<=%d", Price, amount), Price);
        ArrayList<Goods> goods = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql);

            if (!rs.next()) {
                throw new GoodsNotFoundException("查询不到对应产品金额为：" + amount + "以下的产品");
            }
            do {
                goods.add(new Goods(rs.getInt(Id), rs.getString(Name), rs.getDouble(Price)));
            } while (rs.next());
            return goods;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return null;
    }


    @Override
    public ArrayList<Goods> getByPage(int pagenum, int linage) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ArrayList<Goods> goods = new ArrayList<>();
        try {
            int rows = getCount();//行数
            int totalPage = (rows % linage != 0) ? rows / linage + 1 : rows / linage;//总页数
            if (pagenum < 1) {
                throw new GoodsNotFoundException("非法页码"+pagenum);
            }
            if (pagenum > totalPage) {
                throw new GoodsNotFoundException("所查询的页码超出最大页数:" + totalPage);
            }
            // = "SELECT %s FROM %s WHERE %s LIMIT %d, %d;"
            String sql = String.format(SQL_SEL_BY_PAGE, allColumns, Table, true, (pagenum - 1) * linage, linage);
            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql);

            if (!rs.next()) {
                throw new GoodsNotFoundException("表中无任何商品");
            }
            do {
                goods.add(new Goods(rs.getInt(Id), rs.getString(Name), rs.getDouble(Price)));
            } while (rs.next());

            return goods;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }

        return null;
    }

    @Override
    public ArrayList<Goods> getByPage(String sortWay, int pagenum, int linage) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String condition = null;//排序的条件
        sortWay = sortWay.toLowerCase();//转小写以便匹配
        if (sortWay.equals(Price)) {
            condition = Price;
        } else if (sortWay.equals(Name)) {
            condition = Name;
        } else if (sortWay.equals(Id)) {
            condition = Id;
        } else {
            throw new RuntimeException("错误的排序条件");
        }

        ArrayList<Goods> goods = new ArrayList<>();
        try {
            int rows = getCount();//行数
            int totalPage = (rows % linage != 0) ? rows / linage + 1 : rows / linage;

            if (pagenum < 1) {
                throw new GoodsNotFoundException("非法页码"+pagenum);
            }
            if (pagenum > totalPage) {
                throw new GoodsNotFoundException("所查询的页码超出最大页数:" + totalPage);
            }

            String sql = String.format(SQL_SEL_BY_SORT_PAGE, allColumns, Table, true, condition, (pagenum - 1) * linage, linage);// SELECT %s FROM %s WHERE %s ORDER BY %s LIMIT %d, %d

            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql);

            if (!rs.next()) {
                throw new GoodsNotFoundException("表中无任何商品");
            }
            do {
                goods.add(new Goods(rs.getInt(Id), rs.getString(Name), rs.getDouble(Price)));
            } while (rs.next());

            return goods;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }

        return null;
    }

    @Override
    public ArrayList<Goods> getAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = String.format(SQL_SEL, allColumns, Table);//SELECT %S FROM %S;"

        ArrayList<Goods> goods = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql);
            if (!rs.next()) {
                throw new GoodsNotFoundException("表中无任何商品");
            }
            do {
                goods.add(new Goods(rs.getInt(Id), rs.getString(Name), rs.getDouble(Price)));
            } while (rs.next());
            return goods;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public ArrayList<Goods> getAll(String sortWay) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String condition = null;//排序的条件
        sortWay = sortWay.toLowerCase();//转小写以便匹配
        if (sortWay.equals("price")) {
            condition = Price;
        } else if (sortWay.equals("name")) {
            condition = Name;
        } else if (sortWay.equals("id")) {
            condition = Id;
        } else {
            throw new RuntimeException("错误的排序条件");
        }
        //SELECT %S FROM %S ORDER BY %s;
        String sql = String.format(SQL_SEL_SORT, allColumns, Table, condition);
        ArrayList<Goods> goods = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            rs = JDBCUtil.select(conn, ps, sql);

            if (!rs.next()) {
                throw new GoodsNotFoundException("表中无任何商品");
            }
            do {
                goods.add(new Goods(rs.getInt(Id), rs.getString(Name), rs.getDouble(Price)));
            } while (rs.next());
            return goods;
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
        //SELECT COUNT(%s) FROM %s WHERE %s;";
        String sql = String.format(SQL_COUNT, Id, Table, true);
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
