package org.example.weatherforecast.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.weatherforecast.pojo.po.City;
import org.example.weatherforecast.pojo.po.WeatherTable;

import java.util.List;

@Mapper
public interface WeatherMapper {
    void saveCity(City city);

    void saveWeather(@Param("weatherTableList")List<WeatherTable> weatherTableList);

    void deleteWeather(Long id);

    City getWeatherList(String cityName);
}
