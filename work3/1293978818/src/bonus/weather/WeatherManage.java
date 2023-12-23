package bonus.weather;

import java.util.ArrayList;
import java.util.Scanner;

import bonus.domain.City;
import bonus.domain.Weather;

/**
 * 此类用于实现天气系统的业务
 * @author 1293978818
 */
public class WeatherManage {
    
    /** 
     * 此方法用于更新数据库中所有的天气信息
     */
    public void updateWeather(){

        /*获得所有城市信息 */
        CityInformationFromToDataBase cityInformationFromToDataBase = new CityInformationFromToDataBase();
        WeatherInformationFromInternet weatherInformationFromInternet = new WeatherInformationFromInternet();
        WeatherInformationFromToDateBase weatherInformationFromToDateBase = new WeatherInformationFromToDateBase();
        ArrayList<City> cities = cityInformationFromToDataBase.getAllCities();
        ArrayList<Weather> weathers = new ArrayList<>();

        /*将所有城市信息的天气对象查询出来并存入数据库中 */
        for (City city : cities) {
            weathers = weatherInformationFromInternet.getWeather(city.getCityId());
            for (Weather weather : weathers) {
                weatherInformationFromToDateBase.updateWeather(weather);
            }
        }

        System.out.println("数据更新成功");
    }

    /**此方法用于添加新的城市 */
    public void addCity(Scanner sc){

        /*从网站获得城市信息 */
        System.out.println("请输入要添加的城市");
        String cityname = sc.next();
        CityInformationFromInternet cityInformationFromInternet = new CityInformationFromInternet();
        City city = cityInformationFromInternet.getCity(cityname);

        /*如果城市不存在，则停止方法 */
        if(city == null){
            System.out.println("您输入的省份有误，请确认后再来");
            return;
        }

        CityInformationFromToDataBase cityInformationFromToDataBase = new CityInformationFromToDataBase();
        City city2 = cityInformationFromToDataBase.getCity(city.getCityId());
        if(city2.getCityName() != null){
            System.out.println("城市已存在");
            return;
        }
        cityInformationFromToDataBase.addCity(city);

        /*将天气信息存入数据库 */
        WeatherInformationFromToDateBase weatherInformationFromToDateBase = new WeatherInformationFromToDateBase();
        Weather weather = new Weather();
        final int maxIterations = 3;
        for(int i = 0;i < maxIterations;i ++){
            weather.setCityId(city.getCityId());
            weather.setCode(i);
            weather.setFxDate("null");
            weather.setTextDay("null");
            weatherInformationFromToDateBase.addWeather(weather);
        }

        /*更新天气 */
        updateWeather();

    }

    /**此方法用于删除指定的城市*/
    public void deleteCity(Scanner sc){

        printAllCities();
        System.out.println("请输入要删除的城市名称");

        String cityName = sc.next();
        CityInformationFromToDataBase cityInformationFromToDataBase = new CityInformationFromToDataBase();
        City city = cityInformationFromToDataBase.getCity(cityName);

        /*判断名称是否存在 */
        if(city == null){
            System.out.println("您输入的城市不存在，请检查后重试");
            return;
        }

        /*删除城市信息 */
        cityInformationFromToDataBase.deleteCity(city.getCityId());

        /*删除天气信息 */
        WeatherInformationFromToDateBase weatherInformationFromToDateBase = new WeatherInformationFromToDateBase();
        weatherInformationFromToDateBase.deleteWeather(city.getCityId());

        System.out.println("删除成功");

    } 
    
    /**此方法用于输出所有城市信息 */
    public void printAllCities(){

        System.out.println("当前所有的城市如下：");

        /*获得所有的城市信息 */
        ArrayList<City> cities = new ArrayList<>();
        CityInformationFromToDataBase cityInformationFromToDataBase = new CityInformationFromToDataBase();
        cities = cityInformationFromToDataBase.getAllCities();
        System.out.println("城市名称\t城市经度\t城市纬度\t城市位置");

        for (City city : cities) {
            System.out.println(city);
        }
        
    }

    /**此方法用于查询城市的天气信息 */
    public void printWeather(Scanner sc){

        printAllCities();

        System.out.println("请输入您要查询的城市名称");
        String cityName = sc.next();

        CityInformationFromToDataBase cityInformationFromToDataBase = new CityInformationFromToDataBase();
        City city = cityInformationFromToDataBase.getCity(cityName);

        /*如果城市名称不存在则结束方法 */
        if(city == null){
            System.out.println("您输入的城市不存在，请重试");
            return;
        }

        System.out.println("城市名称\t城市经度\t城市纬度\t城市位置");
        System.out.println(city);

        WeatherInformationFromToDateBase weatherInformationFromToDateBase = new WeatherInformationFromToDateBase();
        int maxCode = 2;
        for(int i = 0;i <= maxCode;i ++){
            System.out.println(weatherInformationFromToDateBase.getWeather(city.getCityId(), i));
        }
        
    }


}
