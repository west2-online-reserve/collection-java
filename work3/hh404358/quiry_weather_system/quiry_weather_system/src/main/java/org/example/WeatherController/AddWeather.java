package org.example.WeatherController;

import org.example.Utils.ToWeather;
import org.example.mapper.CityMapper;
import org.example.mapper.WeatherMapper;
import org.example.pojo.Weather;

import java.io.IOException;
import java.util.List;

public class AddWeather {
    public  void add(WeatherMapper weatherMapper, CityMapper cityMapper,int id) throws IOException {
        ToWeather toWeather=new ToWeather();
        List<Weather> weathers= toWeather.toWeather(weatherMapper,cityMapper,id);
        //添加
        for(Weather weather:weathers){
            weatherMapper.add(weather);
        }


    }
}
