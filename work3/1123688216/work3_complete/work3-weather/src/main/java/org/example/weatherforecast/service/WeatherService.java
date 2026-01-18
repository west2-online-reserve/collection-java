package org.example.weatherforecast.service;

import org.example.weatherforecast.mapper.WeatherMapper;
import org.example.weatherforecast.pojo.dto.Daily;
import org.example.weatherforecast.pojo.dto.DayApi;
import org.example.weatherforecast.pojo.dto.GeoApi;
import org.example.weatherforecast.pojo.dto.Location;
import org.example.weatherforecast.pojo.po.City;
import org.example.weatherforecast.pojo.po.WeatherTable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {
    private String dayurl = "https://ke5b65hcu7.re.qweatherapi.com/v7/weather/3d?";
    private String geoUrl = "https://ke5b65hcu7.re.qweatherapi.com/geo/v2/city/lookup?";
    private String apikey = "c3079d4274654f49a7b9d78181754013";
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherMapper weatherMapper;
    public GeoApi getUrlWeather(String cityName) {
       String url = geoUrl+"location="+cityName+"&key="+apikey;
       System.out.println("geourl:"+url);
       GeoApi result = restTemplate.getForObject(url,GeoApi.class);
       System.out.println(result);
       return result;
    }

    public DayApi getUrlDayWeather(Long id) {
        String url = dayurl+"location="+id+"&key="+apikey;
        System.out.println("dayurl:"+url);
        DayApi result = restTemplate.getForObject(url,DayApi.class);
        System.out.println(result);
        return result;
    }
    @Transactional
    public void save(GeoApi geoApi,DayApi dayApi){
        System.out.println("保存数据");
        City city = new City();
        Location location = geoApi.getLocation().get(0);
        BeanUtils.copyProperties(location, city);
        city.setUpdateTime(LocalDate.now());
        System.out.println(city);
        Daily daily = new Daily();
        List<WeatherTable> weatherTableList = new ArrayList<>();
        for(int i =0;i<dayApi.getDaily().size();i++){
            WeatherTable weatherTable = new WeatherTable();
            daily = dayApi.getDaily().get(i);
            BeanUtils.copyProperties(daily,weatherTable);
            weatherTable.setCityId(city.getId());
            weatherTableList.add(weatherTable);
        }
        city.setWeatherTable(weatherTableList);
        weatherMapper.saveCity(city);
        weatherMapper.deleteWeather(city.getId());
        weatherMapper.saveWeather(weatherTableList);
    }
    @Transactional
    public City getWeatherList(String cityName) {
        City city = new City();
        city = weatherMapper.getWeatherList(cityName);
        return city;
    }
}
