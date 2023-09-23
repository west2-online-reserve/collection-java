package org.example.WeatherController;

import org.example.mapper.WeatherMapper;
import org.example.pojo.Weather;

import java.util.List;

public class ShowWeather {
    public void show(WeatherMapper WeatherMapper){
        List<Weather>weathers= WeatherMapper.selectAll();
        for (Weather c:weathers) {
            String s=c.toString();
            System.out.println(s);
        }
    }
}
