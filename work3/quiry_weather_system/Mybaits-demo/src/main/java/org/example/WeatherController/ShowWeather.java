package org.example.WeatherController;

import org.example.mapper.CityMapper;
import org.example.pojo.City;

import java.util.List;

public class Show {
    public void show(CityMapper cityMapper){
        List<City>cities= cityMapper.selectAll();
        for (City c:cities) {
            String s=c.toString();
            System.out.println(s);
        }
    }
}
