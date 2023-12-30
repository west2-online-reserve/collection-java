package base.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.domain.OrderGood;
import base.util.JdbcUtil;

/**
 * 此类用于将购买商品的目录存入数据库或从数据库取出
 * @author 1293978818
 */
public class OrderGoodFromToDataBase {
    

    public OrderGoodFromToDataBase(){
        
    }

    /**
     * 此方法用于增加购买商品的目录至数据库中
     * @param 将要增加的目录对象
     * @return 是否增加成功
     */
    public boolean addOrderGood(OrderGood orderGood,Connection connection){
        String sql = "insert into ordergood(orderid,goodid,goodnum,goodprice) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderGood.getOrderId());
            preparedStatement.setInt(2, orderGood.getGoodId());
            preparedStatement.setInt(3, orderGood.getGoodNum());
            preparedStatement.setDouble(4, orderGood.getGoodPrice());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;
    }

    /**
     * 此方法根据商品id和订单id删除指定目录
     * @param 要删除的商品id
     * @param 要删除的订单id
     * @return 删除是否成功
     */
    public boolean deleteOrderGood(int goodId,int orderId,Connection connection){
        String sql = "delete from ordergood where goodid = ? and orderid = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodId);
            preparedStatement.setInt(2, orderId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                JdbcUtil.close(null, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
        
    }

    /**
     * 此方法根据商品id删除目录
     * @param 商品id
     * @return 删除是否成功
     */
    public boolean deleteOrderGoodByGoodId(int goodId,Connection connection){

        String sql = "delete from ordergood where goodid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 此方法根据订单id删除目录
     * @param 商品id
     * @return 删除是否成功
     */
    public boolean deleteOrderGoodByOrderId(int orderId,Connection connection){
        String sql = "delete from ordergood where orderid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();
            JdbcUtil.close(null, preparedStatement);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 此方法用于将目录对象存入数据库中
     * @param 目录对象
     * @return 存入是否成功
     */
    public boolean updateOrderGood(OrderGood orderGood,Connection connection){
        String sql = "update ordergood set goodnum = ?,goodprice = ? where goodid = ? and orderid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderGood.getGoodNum());
            preparedStatement.setDouble(2, orderGood.getGoodPrice());
            preparedStatement.setInt(3, orderGood.getGoodId());
            preparedStatement.setInt(4, orderGood.getOrderId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        
    }

    /**
     * 此方法用于根据订单编号和商品编号从数据库中获得目录对象
     * @param 订单编号
     * @param 商品编号
     * @return 该目录的对象
     */
    public OrderGood getOrderGood(int orderId,int goodId,Connection connection){
        String sql = "select * from ordergood where goodid = ? and orderid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodId);
            preparedStatement.setInt(2, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                OrderGood orderGood = new OrderGood();
                orderGood.setGoodId(goodId);
                orderGood.setGoodNum(resultSet.getInt("goodnum"));
                orderGood.setGoodPrice(resultSet.getDouble("goodprice"));
                orderGood.setOrderId(orderId);
                JdbcUtil.close(resultSet, preparedStatement);
                return orderGood;
            }
            JdbcUtil.close(resultSet, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 此方法用于将含有指定商品信息的目录对象打包成集合
     * @param 商品id
     * @return 含有该商品的目录的集合
     */
    public ArrayList<OrderGood> getOrderGoodArrayListByGoodId(int goodId,Connection connection){

        String sql = "select * from ordergood where goodid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<OrderGood> list = new ArrayList<>();
            while(resultSet.next()){
                OrderGood orderGood = new OrderGood();
                orderGood.setGoodId(goodId);
                orderGood.setGoodNum(resultSet.getInt("goodnum"));
                orderGood.setGoodPrice(resultSet.getDouble("goodprice"));
                orderGood.setOrderId(resultSet.getInt("orderid"));
                list.add(orderGood);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 此方法用于将含有指定订单信息的目录对象打包成集合
     * @param 订单信息
     * @return 此订单的所有集合
     */
    public ArrayList<OrderGood> getOrderGoodArrayListByOrderId(int orderId,Connection connection){
        ArrayList<OrderGood> list = new ArrayList<>();
        String sql = "select * from ordergood where orderid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                OrderGood orderGood = new OrderGood();
                orderGood.setGoodId(resultSet.getInt("goodid"));
                orderGood.setGoodNum(resultSet.getInt("goodnum"));
                orderGood.setGoodPrice(resultSet.getDouble("goodprice"));
                orderGood.setOrderId(orderId);
                list.add(orderGood);
            }
            JdbcUtil.close(resultSet, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
