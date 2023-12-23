package bonus.weather;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bonus.domain.Weather;
import bonus.util.JdbcUtil;

/**
 * 此类用于将天气信息与数据库进行结合
 * @author 1293978818
 */
public class WeatherInformationFromToDateBase {

    private Connection connection;

    public WeatherInformationFromToDateBase(){
        try {
            connection = JdbcUtil.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 此方法用于将指定的天气对象添加至数据库中
     * @param 要添加的天气对象
     * @return 添加是否成功
     */
    public boolean addWeather(Weather weather){
        String sql = "insert into weatherinformation(cityid,code,fxdate,tempmax,tempmin,textday) values (?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, weather.getCityId());
            preparedStatement.setInt(2, weather.getCode());
            preparedStatement.setString(3, weather.getFxDate());
            preparedStatement.setInt(4, weather.getTempMax());
            preparedStatement.setInt(5, weather.getTempMin());
            preparedStatement.setString(6, weather.getTextDay());
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
     * 此方法用于删除指定城市id的所有天气信息
     * @param 要删除的城市id
     * @return 删除是否成功
     */
    public boolean deleteWeather(int cityId){

        String sql = "delete from weatherinformation where cityid = ?";
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
     * 此方法用于根据提供的weather对象的信息修改数据库中weather的内容
     * @param 要在数据库中修改的weather信息
     * @return 修改是否成功
     */
    public boolean updateWeather(Weather weather){
        String sql = "update weatherinformation set fxdate = ?,tempmax = ?,tempmin = ?,textday = ? where cityid = ? and code = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, weather.getFxDate());
            preparedStatement.setInt(2, weather.getTempMax());
            preparedStatement.setInt(3, weather.getTempMin());
            preparedStatement.setString(4, weather.getTextDay());
            preparedStatement.setInt(5, weather.getCityId());
            preparedStatement.setInt(6, weather.getCode());
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
     * 此方法根据城市id与代码获得weather对象
     * @param 城市id
     * @param 代码
     * @return 数据库中的weather对象，若无则返回null
     */
    public Weather getWeather(int cityId,int code){

        String sql = "select * from weatherinformation where cityid = ? and code = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, cityId);
            preparedStatement.setInt(2, code);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                return null;
            }

            Weather weather = new Weather();
            weather.setCityId(cityId);
            weather.setCode(code);
            weather.setFxDate(resultSet.getString("fxdate"));
            weather.setTempMax(resultSet.getInt("tempmax"));
            weather.setTempMin(resultSet.getInt("tempmin"));
            weather.setTextDay(resultSet.getString("textday"));

            return weather;

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
