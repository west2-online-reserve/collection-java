package bonus.weather;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bonus.domain.City;
import bonus.util.JdbcUtil;

/**
 * 此类用于操作城市对象与数据库之间的关系
 * @author 1293978818
 */
public class CityInformationFromToDataBase {

    private Connection connection;

    public CityInformationFromToDataBase(){
        try {
            connection = JdbcUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 此方法用于根据城市id从数据库中获取城市信息对象
     * @param 城市id
     * @return 该城市id对应的城市对象
     */
    public City getCity(int cityId){
        String sql = "select * from cityinformation where cityid = ?";
        City city = new City();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                city.setCityId(cityId);
                city.setCityName(resultSet.getString("cityname"));
                city.setLatitude(resultSet.getDouble("latitude"));
                city.setLocation(resultSet.getString("location"));
                city.setLongitude(resultSet.getDouble("longitude"));
            }
            JdbcUtil.close(resultSet, preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    /**
     * 此方法用于将指定城市对象存入数据库
     * @param 城市对象
     * @return 是否存入成功
     */
    public boolean addCity(City city){
        String sql = "insert into cityinformation values(?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, city.getCityId());
            preparedStatement.setString(2, city.getCityName());
            preparedStatement.setDouble(3, city.getLatitude());
            preparedStatement.setDouble(4, city.getLongitude());
            preparedStatement.setString(5, city.getLocation());
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
     * 根据城市id删除城市
     * @param 城市id
     * @return 是否删除成功
     */
    public boolean deleteCity(int cityId){
        String sql = "delete  from cityinformation where cityid = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cityId);
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
     * 此方法用于获得所有城市信息组成的集合
     * @return 数据库中所有城市信息的集合
     */
    public ArrayList<City> getAllCities(){
        String sql = "select * from cityinformation";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<City> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                City city = new City();
                city.setCityId(resultSet.getInt("cityid"));
                city.setCityName(resultSet.getString("cityname"));
                city.setLatitude(resultSet.getDouble("latitude"));
                city.setLocation(resultSet.getNString("location"));
                city.setLongitude(resultSet.getDouble("longitude"));
                list.add(city);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                JdbcUtil.close(resultSet, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    /**
     * 此方法用于根据城市名称从数据库中获取城市信息对象
     * @param 城市名称
     * @return 该城市名称对应的城市对象
     */
    public City getCity(String cityName){
        String sql = "select * from cityinformation where cityname = ?";
        City city = new City();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cityName);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                city.setCityId(resultSet.getInt("cityid"));
                city.setCityName(resultSet.getString("cityname"));
                city.setLatitude(resultSet.getDouble("latitude"));
                city.setLocation(resultSet.getString("location"));
                city.setLongitude(resultSet.getDouble("longitude"));
                return city;
            }else{
                return null;
            }
                       
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                JdbcUtil.close(resultSet, preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
        
    }
}
