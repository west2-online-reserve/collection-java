package org.example.weatherforecast.controller;

import org.example.weatherforecast.pojo.dto.DayApi;
import org.example.weatherforecast.pojo.dto.GeoApi;
import org.example.weatherforecast.pojo.po.City;
import org.example.weatherforecast.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/save")
    public void saveWeather(@RequestParam("city") String cityName) {
        // 调用 Service 层逻辑
        GeoApi geoApi = weatherService.getUrlWeather(cityName);
        //获取xx市的id
        Long id = geoApi.getLocation().get(0).getId();
        System.out.println(id);
        DayApi dayApi = weatherService.getUrlDayWeather(id);
        //拿到了对应城市的温度,接下来就是存到数据库中
        weatherService.save(geoApi,dayApi);
    }
    @GetMapping("/get")
    public City getWeather(@RequestParam("city") String cityName){
        City city =weatherService.getWeatherList(cityName);
        System.out.println(city);
        return city;
    }
}
