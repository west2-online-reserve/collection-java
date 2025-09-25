package DAO;

import JDBC.JdbcUtils;
import JDBC.ResultSetHandler;
import MyExceptions.OrderException;
import informationClass.Order;
import informationClass.Product;
import DAO.ProductDAO;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderDAO {
    //判断订单信息是否完整
    private boolean checkOrder(Order o){
        if (o.getOrder_code() == null || o.getUsername() == null || o.getOrder_price() == 0 || o.getOrder_date() == null )
            return false;
        return true;
    }

    /**
     * 添加订单
     * @param  o
     * @return 受影响的行数
     */
    public int addOrderDAO(Order o){
        if (!checkOrder(o)){
            throw new OrderException("订单信息不完整！！！");
        }
        if (!isExist(o)){
            int i1=0;
            int i2=0;
            boolean b = true;
            try {
                JdbcUtils.beginTransaction();
                String sql = "insert into `order`(`order_code`,`username`,`order_price`,`order_date`)values(?,?,?,?)";
                i2 = JdbcUtils.executeUpdate(sql,Long.parseLong(o.getOrder_code()),o.getUsername(),o.getOrder_price(),o.getOrder_date());
                if (i2<=0)
                {
                    throw new RuntimeException("主订单插入失败！！！");
                }
                //将订单信息插入到order_product表中
                for (Product p : o.getProducts()){
                    String sql2 = "insert into `order_product`(`order_code`,`product_code`,`product_name`,`product_price`,`order_price`) values(?,?,?,?,?)";
                    i1 = JdbcUtils.executeUpdate(sql2,Long.parseLong(o.getOrder_code()),Long.parseLong(p.getProduct_code()),p.getProduct_name(),p.getProduct_price(),o.getOrder_price());
                    if (i1==0) {
                        b = false;
                        break;
                    }
                }
                if (b)
                {
                    JdbcUtils.submitTransaction();
                    return i1;
                } else{
                    throw new RuntimeException("订单商品插入失败！！！");
                }
            } catch(RuntimeException e){
                System.out.println(e.getMessage());
                JdbcUtils.rollbackTransaction();
                System.out.println("添加订单失败，已回滚: " + e.getMessage());
                return 0;
            } finally {
                JdbcUtils.overTransaction();
            }
        } else {
            System.out.println("order表中订单号已存在！！！");
        }
        return 0;
    }

    /**
     * 删除订单
     * @param o
     * @return 受影响的行数
     */
    public int deleteOrder(Order o){
        if  (!checkOrder(o)){
            throw new OrderException("订单信息不完整！！！");
        }
        if (isExist(o)){
            int i1=0;
            int i2=0;
            boolean b = true;
            try {
                JdbcUtils.beginTransaction();
                String sql = "delete from `order` where `order_code`=?";
                i2 = JdbcUtils.executeUpdate(sql,Long.parseLong(o.getOrder_code()));
                if (i2<=0)
                {
                    throw new RuntimeException("主订单删除失败！！！");
                }
                //将订单信息从order_product表中删除
                String sql2 = "delete from `order_product` where `order_code`=?";
                i1 = JdbcUtils.executeUpdate(sql2,Long.parseLong(o.getOrder_code()));
                if (i1<=0) {
                    b = false;
                }
                if (b)
                {
                    JdbcUtils.submitTransaction();
                    return i1;
                } else{
                    throw new RuntimeException("订单商品删除失败！！！");
                }
            } catch(RuntimeException e){
                System.out.println(e.getMessage());
                JdbcUtils.rollbackTransaction();
                System.out.println("删除订单失败，已回滚！！！");
                return 0;
            } finally {
                JdbcUtils.overTransaction();
            }
        } else {
            System.out.println("订单号不存在！！！");
        }
        return 0;
    }

    /**
     * 修改订单
     * @param o
     * @param b 判断是添加商品(true)还是删除商品(false)
     * @param p 商品信息
     * @return 受影响的行数
     */
    public int updateOrder(Order o,Product p,boolean b){
        if (!checkOrder(o) && ProductDAO.checkProduct(p)){
            throw new OrderException("订单或者商品信息不完整！！！");
        }
        String sql1 = null;
        String sql2 = null;
        String sql3 = null;
        ArrayList<String> codes = getAllOrderCode();
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        try {
            JdbcUtils.beginTransaction();
            if (b){
                o.getProducts().add(p);
                sql1 = "update `order` set `order_price`=?,`order_date`=? where `order_code`=?";
                sql2 = "insert into `order_product`(`order_code`,`product_code`,`product_name`,`product_price`,`order_price`)values(?,?,?,?,?)";
                if (codes.contains(o.getOrder_code())){
                    o.setOrder_price(p.getProduct_price()+o.getOrder_price());
                    i1 = JdbcUtils.executeUpdate(sql1,o.getOrder_price(), LocalDateTime.now(),Long.parseLong(o.getOrder_code()));
                    sql3 = "update `order_product` set `order_price`=? where `order_code`=?";
                    //将商品信息插入到order_product表中
                    i2 = JdbcUtils.executeUpdate(sql2,Long.parseLong(o.getOrder_code()),Long.parseLong(p.getProduct_code()),p.getProduct_name(),p.getProduct_price(),o.getOrder_price());
                    i3 = JdbcUtils.executeUpdate(sql3,o.getOrder_price(),Long.parseLong(o.getOrder_code()));
                }
                if (i1>0 && i2>0 &&  i3>0){
                    JdbcUtils.submitTransaction();
                    return i1;
                } else {
                    throw new RuntimeException("订单商品插入失败！！！");
                }
            } else {
                sql1 = "update `order` set `order_price`=?,`order_date`=? where `order_code`=?";
                sql2 = "delete from `order_product` where `order_code`=? and `product_code`=?";
                if (codes.contains(o.getOrder_code()) && isExist(o,p)){
                    o.setOrder_price(o.getOrder_price()-p.getProduct_price());
                    i1 = JdbcUtils.executeUpdate(sql1,o.getOrder_price(), LocalDateTime.now(),Long.parseLong(o.getOrder_code()));
                    sql3 = "update `order_product` set `order_price`=? where `order_code`=?";
                    //将商品信息从order_product表中删除
                    i2 = JdbcUtils.executeUpdate(sql2,Long.parseLong(o.getOrder_code()),Long.parseLong(p.getProduct_code()));
                    i3 = JdbcUtils.executeUpdate(sql3,o.getOrder_price(),Long.parseLong(o.getOrder_code()));
                }
                if  (i1>0 && i2>0 &&  i3>0){
                    JdbcUtils.submitTransaction();
                    return i1;
                } else {
                    throw new RuntimeException("订单商品删除失败！！！");
                }

            }
        } catch (RuntimeException e){
            JdbcUtils.rollbackTransaction();
            System.out.println("修改订单失败，已回滚！！！" + e.getMessage());
            return 0;
        } finally {
            JdbcUtils.overTransaction();
        }
    }

    /**
     * 查询所有订单
     * @param sort_order 排序方式
     * @return void
     */
    public void queryAllOrder(String sort_order) {
        String sql1 = "select * from `order` order by `order_code` asc";
        String sql2 = "select * from `order` order by `order_code` desc";

        if ("asc".equals(sort_order)) {
            printToFile(sort_order,sql1);
        } else if ("desc".equals(sort_order)) {
            printToFile(sort_order,sql2);
        }
    }

    /**
     * 查询指定的订单(根据订单号)
     * @param code 订单号
     * @return void
     */
    public void queryOrder(String code) {
        String sql = "select * from `order` where `order_code`=?";
        JdbcUtils.executeQuery(sql,new ResultSetHandler<Void>(){
            @Override
            public Void handle(ResultSet rs){
                StringBuilder sb =new StringBuilder();
                BufferedWriter bw = null;
                try {
                    if (rs.next()){
                        sb.append("订单号: " + rs.getInt("order_code") + "\n");
                        sb.append("用户名: " + rs.getString("username") + "\n");
                        sb.append("订单价格: " + rs.getDouble("order_price") + "\n");
                        sb.append("下单时间: " + rs.getObject("order_date") + "\n");
                        bw = new BufferedWriter(new FileWriter("src/main/java/test/Output.txt", true));
                        bw.write(sb.toString());
                        bw.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        },Long.parseLong(code));
    }

    /**
     * 分页查询订单
     * @param pageNum 页码
     * @param pageSize 页大小
     * @return ArrayList<Order>
     */
    public ArrayList<Order> queryByPage(int  pageNum, int pageSize){
        if (pageNum <1)
            pageNum = 1;
        if (pageSize <10 || pageSize >50)
            pageSize = 10;
        int offset = (pageNum - 1) * pageSize;
        String sql = "select `order_code`,`username`,`order_price`,`order_date` from `order` order by `order_code` asc limit ?,?";
        return JdbcUtils.executeQuery(sql,new ResultSetHandler<ArrayList<Order>>(){
            @Override
            public ArrayList<Order> handle(ResultSet rs){
                ArrayList<Order> orders = new ArrayList<>();
                while (true){
                    try {
                        if (!rs.next())
                            break;
                        Order o = new Order();
                        o.setOrder_code(String.valueOf(rs.getInt("order_code")));
                        o.setUsername(rs.getString("username"));
                        o.setOrder_price(rs.getDouble("order_price"));
                        o.setOrder_date((LocalDateTime) rs.getObject("order_date"));
                        orders.add(o);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                return orders;
            }
        },offset,pageSize);
    }
    //查询订单是否存在
    public boolean isExist(Order o){
        boolean b = false;
        if (checkOrder(o)){
            String sql = "select count(*) from `order` where order_code=?";
            b = Boolean.TRUE.equals( JdbcUtils.executeQuery(sql,new ResultSetHandler<Boolean>(){
                @Override
                public Boolean handle(ResultSet rs){
                    try {
                        if (rs.next())
                            if (rs.getInt(1) > 0)
                                return true;
                        return false;
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            },Long.parseLong(o.getOrder_code())));
        }
        return b;
    }

    //将所有订单号取出
    public ArrayList<String> getAllOrderCode(){
        String sql = "select `order_code` from `order` order by `order_code` asc";
        return JdbcUtils.executeQuery(sql,new ResultSetHandler<ArrayList<String>>(){
            @Override
            public ArrayList<String> handle(ResultSet rs) {
                ArrayList<String> list = new ArrayList<>();
                while (true){
                    try {
                        if (!rs.next())
                            break;
                        list.add(String.valueOf(rs.getInt("order_code")));
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                return list;
            }
        },new Object[0]);
    }

    /**
     * 检查商品是否存在于订单中
     * @param o 订单信息
     * @param p 商品信息
     * @return boolean
     */
    public boolean isExist(Order o,Product p) {
        boolean b = false;
        String sql = "select count(*) from `order_product` where `order_code`=? and `product_code`=?";
        return Boolean.TRUE.equals(JdbcUtils.executeQuery(sql, new ResultSetHandler<Boolean>() {
            @Override
            public Boolean handle(ResultSet rs) {
                try {
                    if (rs.next()) {
                        if (rs.getInt(1) > 0)
                            return true;

                    }
                    return false;
                } catch (SQLException e) {
                    return false;
                }
            }
        }, Long.parseLong(o.getOrder_code()), Long.parseLong(p.getProduct_code())));
    }

    /**
     * 打印订单信息到文件
     * @param sort_order
     * @param sql
     */
    private void printToFile(String sort_order,String sql){
        JdbcUtils.executeQuery(sql, new ResultSetHandler<Void>() {
            @Override
            public Void handle(ResultSet rs) {
                BufferedWriter bw = null;
                try {
                    bw = new BufferedWriter(new FileWriter("src/main/java/test/Output.txt", true));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                while (true) {
                    try {
                        StringBuilder sb = new StringBuilder();
                        if (!rs.next()) break;
                        sb.append("订单号: " + rs.getInt("order_code") + "\n");
                        sb.append("用户名: " + rs.getString("username") + "\n");
                        sb.append("订单价格: " + rs.getDouble("order_price") + "\n");
                        sb.append("下单时间: " + rs.getObject("order_date") + "\n");
                        sb.append("===========================\n");
                        bw.write(sb.toString());
                        bw.flush();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }
        }, new Object[0]);
    }

    /**
     * 打印订单商品信息到文件
     * @param pageNum
     * @param pageSize
     */
    public void printQueryByPage(int pageNum, int pageSize) {
        ArrayList<Order> list = queryByPage(pageNum, pageSize);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("src/main/java/test/Output.txt",true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Order o : list){
            StringBuilder sb = new StringBuilder();
            sb.append("商品编号: "+ o.getOrder_code() + "\n");
            sb.append("用户: "+ o.getUsername() + "\n");
            sb.append("订单价格: "+ o.getOrder_price() + "\n");
            sb.append("下单时间: "+ o.getOrder_date() + "\n");
            sb.append("======================================\n");
            try {
                bw.write(sb.toString());
                bw.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
