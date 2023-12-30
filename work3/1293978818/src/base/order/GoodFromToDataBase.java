package base.order;
/**
 * 此类用于将good对象存入或取出数据库
 * @author 1293978818
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import base.domain.Good;
import base.util.JdbcUtil;

public class GoodFromToDataBase {



    public GoodFromToDataBase(){
        
    }
    
    /**
     * 此方法用于在数据库中增加指定商品
     * @param 商品对象 
     * @return 增加是否成功
    */
    public boolean addGood(Good good,Connection connection){
        String sql = "insert into good(goodname,goodprice) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, good.getGoodName());
            preparedStatement.setDouble(2, good.getGoodPrice());
            preparedStatement.executeUpdate();
            JdbcUtil.close(null, preparedStatement);
            return true;
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 此方法用于在数据库中删除指定商品
     * @param 商品编号
     * @return 删除是否成功
     */
    public boolean deleteGood(int goodId,Connection connection){
        String sql = "delete from good where goodid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodId);
            preparedStatement.executeUpdate();
            JdbcUtil.close(null, preparedStatement);
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 此方法用于更新商品信息
     * @param 更新后的商品对象
     * @return 是否更新成功
     */
    public boolean updategood(Good good,Connection connection){
        String sql = "update good set goodname = ?,goodprice = ? where goodid = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, good.getGoodName());
            preparedStatement.setDouble(2, good.getGoodPrice());
            preparedStatement.setInt(3, good.getGoodid());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
        
    }

    /**
     * 此方法用于获得指定页码中的商品信息的集合
     */
    public ArrayList<Good> getAllGood(Connection connection,int page){
        String sql = "select * from good limit ?,?";
        ArrayList<Good> list = new ArrayList<>(); 
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (page-1)*3);
            preparedStatement.setInt(2, 3);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Good good = new Good();
                good.setGoodName(resultSet.getString("goodname"));
                good.setGoodPrice(resultSet.getDouble("goodprice"));
                good.setGoodid(resultSet.getInt("goodid"));
                list.add(good);
            }
            JdbcUtil.close(resultSet, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**将指定商品编号的商品从数据库中取出 
     * @param 商品编号
     * @return 该商品的商品对象,若不存在，则返回null
    */
    public Good getGood(int goodId,Connection connection){
        String sql = "select * from good where goodid = ?";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, goodId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Good good = new Good();
                good.setGoodName(resultSet.getString("goodname"));
                good.setGoodPrice(resultSet.getDouble("goodprice"));
                good.setGoodid(resultSet.getInt("goodid"));
                return good;
            }

        } catch (SQLException e) {            
            e.printStackTrace();
        }
        return null;
    }

    /**此方法用于获得商品的个数 */
    public int getGoodNum(Connection connection){
        String sql = "select count(*) from good";
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JdbcUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtil.close(resultSet, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

}
