package XWBN3.dao.impl;

import XWBN3.dao.OrderDao;
import XWBN3.entity.*;
import XWBN3.utils.jdbcUtils;
import  java.sql.SQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @Author：XWBN
 * @Package：XWBN3.dao.impl
 * @Project：MyCodes
 * @name：OrderDaoimpl
 * @Date：2023/12/2 20:37
 * @Filename：OrderDaoimpl
 */
public class OrderDaoimpl implements OrderDao {

    Connection conn =null;
    @Override
    public void commit() {
        //Connection conn = null;
        try {
            if (conn != null && !conn.isClosed()) {
                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 回滚事务
    @Override
    public void rollback() {
        //Connection conn =null;
        try {
            if (conn != null && !conn.isClosed()) {
                conn.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<target> selectAll(){

        //Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<target> list = new ArrayList<>();

        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
             ps = conn.prepareStatement("select commoditylinkorder_id,orders.order_id,commodity.commodity_id,commodity_name,commodity_num,order_time,order_price,totalPrice from commoditylinkorder,orders,commodity where commoditylinkorder.commodity_id = commodity.commodity_id and commoditylinkorder.order_id = orders.order_id;");
             rs = ps.executeQuery();
            while(rs.next()){
                int coid = rs.getInt(1);
                int oid = rs.getInt(2);
                int cid = rs.getInt(3);
                String cname = rs.getString(4);
                int cnum = rs.getInt(5);
                String otime = rs.getString(6);
                double oprice = rs.getDouble(7);
                double tprice = rs.getDouble(8);

                target target1 = new target(coid,oid,cid,cname,cnum,otime,oprice,tprice);
                list.add(target1);
                commit();

            }
        }catch(SQLException e){
            rollback();
            e.printStackTrace();
        }finally{
            jdbcUtils.release(conn,ps,rs);
        }
        return list;
    }


    @Override
    public List<target> selectOne(int order_id){

        //Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<target> list = new ArrayList<>();

        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("select commoditylinkorder_id,commoditylinkorder.order_id,commoditylinkorder.commodity_id,commodity_name,commodity_num,order_time,order_price,totalPrice from commoditylinkorder,orders,commodity where commoditylinkorder.commodity_id = commodity.commodity_id and commoditylinkorder.order_id = orders.order_id and orders.order_id = ?");
            ps.setInt(1,order_id);
            rs = ps.executeQuery();
            while(rs.next()){
                int coid = rs.getInt(1);
                int oid = rs.getInt(2);
                int cid = rs.getInt(3);
                String cname = rs.getString(4);
                int cnum = rs.getInt(5);
                String otime = rs.getString(6);
                double oprice = rs.getDouble(7);
                double tprice = rs.getDouble(8);

                target target2 = new target(coid,oid,cid,cname,cnum,otime,oprice,tprice);
                list.add(target2);
                commit();
            }
        }catch(SQLException e){
            rollback();
            e.printStackTrace();
        }finally{
            jdbcUtils.release(conn,ps,rs);
        }
        return list;
    }


    @Override
    public  List<commodity> selectCommodityAll(){
        //Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<commodity> list = new ArrayList<>();

        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("select commodity_id,commodity_name,commodity_price from commodity");
            rs = ps.executeQuery();
            while(rs.next()){
                int cid = rs.getInt(1);
                String cname =rs.getString(2);
                double cprice = rs.getDouble(3);
                commodity commodity1 = new commodity(cid,cname,cprice);
                list.add(commodity1);
            }
            commit();

        }catch (SQLException e){
            rollback();
            e.printStackTrace();
        }finally{
            jdbcUtils.release(conn, ps ,rs);
        }
        return list;
    }

    @Override
    public commodity selectCommodityOne(int commodity_id){
        //Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        commodity commodity2 = new commodity();

        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement("select commodity_id,commodity_name,commodity_price from commodity where commodity_id= ?;");
            ps.setInt(1,commodity_id);
            rs = ps.executeQuery();
            while(rs.next()){
                int cid = rs.getInt(1);
                String cname =rs.getString(2);
                double cprice = rs.getDouble(3);
                commodity2 = new commodity(cid,cname,cprice);
            }
            commit();
        }catch(SQLException e){
            rollback();
            e.printStackTrace();

        }finally{
            jdbcUtils.release(conn,ps,rs);
        }

        return commodity2;
    }

    @Override
    public orders selectOrderOne(int order_id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select order_id,order_time,totalPrice from orders where order_id = ?";
        orders order = new orders();

        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1,order_id);
            rs = ps.executeQuery();
            while(rs.next()){
                int oid = rs.getInt(1);
                String odate = rs.getString(2);
                double totalPrice = rs.getDouble(3);
                order = new orders(oid,odate,totalPrice);
            }
            commit();
        }catch(SQLException e){
            rollback();
            e.printStackTrace();
        }finally{
            jdbcUtils.release(conn,ps,rs);
        }
        return order;
    }

    @Override
    public int insertOrder(orders order1) {
        //Connection conn = null;
        String sql1 = "insert into orders values(?,?,?)";
        PreparedStatement ps = null;
        int len = 0;
        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql1);
            ps.setInt(1,order1.getOrderId());
            ps.setString(2,order1.getOrderTime());
            ps.setDouble(3,order1.getToatalPrice());
            len = ps.executeUpdate();
            commit();
        }catch(SQLException e){
            rollback();
            e.printStackTrace();
        }finally{
            jdbcUtils.release(conn,ps,null);
        }
        return len;
    }

    @Override
    public int insertCommoditylinkOrder(commoditylinkorder commoditylinkorder1) {
        //Connection conn = null;
        String sql = "insert into commoditylinkorder values(?,?,?,?,?)";
        PreparedStatement ps = null;
        int len = 0;
        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1,commoditylinkorder1.getCommoditylinkorderId());
            ps.setInt(2,commoditylinkorder1.getCommodityId());
            ps.setInt(3,commoditylinkorder1.getOrderId());
            ps.setInt(4,commoditylinkorder1.getCommodityNum());
            ps.setDouble(5,commoditylinkorder1.getOrderPrice());
            len = ps.executeUpdate();
            commit();
        }catch(SQLException e){
            rollback();
            e.printStackTrace();
        }finally{
            jdbcUtils.release(conn,ps,null);
        }
        return len;
    }

    @Override
    public int insertCommodity(commodity commodity1) {
        //Connection conn = null;
        String sql = "insert into commodity values(?,?,?)";
        PreparedStatement ps = null;
        int len = 0;
        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1,commodity1.getCommodityId());
            ps.setString(2,commodity1.getCommodityName());
            ps.setDouble(3,commodity1.getCommodityPirce());
            len = ps.executeUpdate();
            commit();
        }catch(SQLException e){
            rollback();
            e.printStackTrace();
        }finally{
            jdbcUtils.release(conn,ps,null);
        }

        return len;
    }

    @Override
    public int updateCommodity(commodity commodity1) {
        //Connection conn = null;
        String sql = "update commodity set commodity_name = ?, commodity_price = ? where commodity_id =?;";
        PreparedStatement ps = null;
        int len = 0;
        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setString(1,commodity1.getCommodityName());
            ps.setDouble(2,commodity1.getCommodityPirce());
            ps.setInt(3,commodity1.getCommodityId());
            len = ps.executeUpdate();
            commit();
        }catch(SQLException e){
            rollback();
            e.printStackTrace();
        }finally{
            jdbcUtils.release(conn,ps,null);
        }
        return len;
    }

    @Override
    public int updateCommodityLinkOrder(commoditylinkorder commoditylinkorder1) {
       //Connection conn = null;
        String sql = "update commoditylinkorder set commodity_id = ?,commodity_num = ?,order_price = ? where commoditylinkorder_id = ?";
        PreparedStatement ps = null;
        int len = 0;
        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1,commoditylinkorder1.getCommodityId());
            ps.setInt(2,commoditylinkorder1.getCommodityNum());
            ps.setDouble(3,commoditylinkorder1.getOrderPrice());
            ps.setInt(4,commoditylinkorder1.getCommoditylinkorderId());
            len = ps.executeUpdate();
            commit();
        }catch(SQLException e){
            rollback();
            e.printStackTrace();
        }finally{
            jdbcUtils.release(conn,ps,null);
        }
        return len;
    }

    @Override
    public int updateOrder(orders order1) {
        PreparedStatement ps = null;
        String sql = "update orders set totalPrice = ? where order_id = ?";
        int len = 0;
        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setDouble(1,order1.getToatalPrice());
            ps.setInt(2,order1.getOrderId());
            len = ps.executeUpdate();
            commit();
        }catch(SQLException e){
            rollback();
            e.printStackTrace();
        }finally{
            jdbcUtils.release(conn,ps,null);
        }
        return len;
    }

    @Override
    public int deleteOrder(int order_id) {
        //Connection conn = null;
        PreparedStatement ps = null;
        String sql1 = "delete from orders where order_id = ?";
        int len = 0;
        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql1);
            ps.setInt(1,order_id);
            len = ps.executeUpdate();
            commit();
        }catch(SQLException e){
            rollback();
            e.printStackTrace();
        }finally{
            jdbcUtils.release(conn,ps,null);
        }


        return len;
    }

    @Override
    public int deleteCommodity(int commodity_id) {
        //Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from commodity where commodity_id  = ?";
        int len = 0;
        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            ps.setInt(1,commodity_id);
            len = ps.executeUpdate();
            commit();
        }catch(SQLException e){
            rollback();
            e.printStackTrace();
        }finally{
            jdbcUtils.release(conn,ps,null);
        }
        return len;
    }

    @Override
    public List<target> SortOrderByTime() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select commoditylinkorder_id,orders.order_id,commodity.commodity_id,commodity_name,commodity_num,order_time,order_price,totalPrice from commoditylinkorder,orders,commodity where commoditylinkorder.commodity_id = commodity.commodity_id and commoditylinkorder.order_id = orders.order_id order by order_time asc;";
        List<target> list5 = new ArrayList<>();
        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int coid = rs.getInt(1);
                int oid = rs.getInt(2);
                int cid = rs.getInt(3);
                String cname = rs.getString(4);
                int cnum = rs.getInt(5);
                String otime = rs.getString(6);
                double oprice = rs.getDouble(7);
                double tprice = rs.getDouble(8);

                target target1 = new target(coid,oid,cid,cname,cnum,otime,oprice,tprice);
                list5.add(target1);
                commit();
            }
        }catch(SQLException e){
            rollback();
            e.printStackTrace();
        }finally{
            jdbcUtils.release(conn,ps,rs);
        }
        return list5;
    }

    @Override
    public List<target> SortOrderByPrice() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select commoditylinkorder_id,orders.order_id,commodity.commodity_id,commodity_name,commodity_num,order_time,order_price,totalPrice from commoditylinkorder,orders,commodity where commoditylinkorder.commodity_id = commodity.commodity_id and commoditylinkorder.order_id = orders.order_id order by totalPrice asc;";
        List<target> list6 = new ArrayList<>();
        try{
            conn = jdbcUtils.getConnection();
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                int coid = rs.getInt(1);
                int oid = rs.getInt(2);
                int cid = rs.getInt(3);
                String cname = rs.getString(4);
                int cnum = rs.getInt(5);
                String otime = rs.getString(6);
                double oprice = rs.getDouble(7);
                double tprice = rs.getDouble(8);

                target target1 = new target(coid,oid,cid,cname,cnum,otime,oprice,tprice);
                list6.add(target1);
                commit();
            }
        }catch(SQLException e){
            rollback();
            e.printStackTrace();
        }finally{
            jdbcUtils.release(conn,ps,rs);
        }
        return list6;

    }
}
