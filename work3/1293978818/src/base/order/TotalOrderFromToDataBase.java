package base.order;
/**
 * 此类用于订单总表的增删改查操作
 * @author 1293978818
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.domain.TotalOrder;
import base.util.JdbcUtil;

public class TotalOrderFromToDataBase {

    public TotalOrderFromToDataBase(){
        
    }

    /**
     * 此方法用于增加一个空值订单
     * @return 返回创建好的订单编号,若创建失败，返回-1
     */
    public int addEmptyOrder(Connection connection){
        String sql = "Insert into totalorder(ordertime) values (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis()));
            preparedStatement.executeUpdate();

            sql = "select max(orderid) from totalorder";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
        
    }

    /**
     * 此方法用于删除指定订单号的订单
     * @param 订单号
     * @return 是否删除成功
     */
    public boolean deleteTotalOrder(int orderId,Connection connection){
        String sql = "delete from totalorder where orderid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 此方法用于将指定订单对象传入数据库
     * @param 订单对象
     * @return 是否存入成功
     */
    public boolean updateTotalOrder(TotalOrder totalOrder,Connection connection){
        String sql = "update totalorder set ordertime = ?,totalprice = ? where orderid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setTimestamp(1, new java.sql.Timestamp(System.currentTimeMillis()));
            preparedStatement.setDouble(2, totalOrder.getTotalPrice());
            preparedStatement.setInt(3, totalOrder.getOrderId());
            preparedStatement.executeUpdate();
            JdbcUtil.close(null, preparedStatement);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 此方法用于返回指定订单号的订单对象
     * @param 订单号
     * @return 该订单号对应的订单对象,如果不存在，则返回null
     */
    public TotalOrder getTotalOrder(int orderId,Connection connection){
        String sql = "select * from totalorder where orderid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                TotalOrder totalOrder = new TotalOrder();
                totalOrder.setOrderId(orderId);
                totalOrder.setTotalPrice(resultSet.getDouble("totalprice"));
                totalOrder.setOrderTime(resultSet.getTimestamp("ordertime").getTime());
                return totalOrder;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 此方法用于根据页码返回商品总表的集合 */
    public ArrayList<TotalOrder> getTotalOrderList(int page,Connection connection){
        String sql = "select * from totalorder limit ?,?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (page-1)*3);
            preparedStatement.setInt(2, 3);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<TotalOrder> totalOrderList = new ArrayList<>();
            while(resultSet.next()){
                TotalOrder totalOrder = new TotalOrder();
                totalOrder.setOrderId(resultSet.getInt("orderid"));
                totalOrder.setTotalPrice(resultSet.getDouble("totalprice"));
                totalOrder.setOrderTime(resultSet.getTimestamp("ordertime").getTime());
                totalOrderList.add(totalOrder);
            }
            return totalOrderList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 此方法用于返回商品总表的总页数 */
    public int getTotalOrderPage(Connection connection){
        String sql = "select count(*) from totalorder";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return (resultSet.getInt(1)-1)/3+1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
