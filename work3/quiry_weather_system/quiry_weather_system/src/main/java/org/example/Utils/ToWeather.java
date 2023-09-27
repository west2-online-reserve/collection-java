package org.example.Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.CityController.QueryCity;
import org.example.mapper.CityMapper;
import org.example.mapper.WeatherMapper;
import org.example.pojo.City;
import org.example.pojo.Weather;
import java.io.IOException;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class ToWeather {
    public List<Weather> toWeather(WeatherMapper weatherMapper,CityMapper cityMapper,int id) throws IOException {
        OkHttpApi okHttpApi=new OkHttpApi();
        String url="https://devapi.qweather.com/v7/weather/3d?key=29f50da3987a40d0bc6298fd74e9a454&location="+id;
        String run=okHttpApi.run(url);
        System.out.println(run);
        Date fxDate,fxDate1,fxDate2;
        Integer tempMax,tempMax1,tempMax2;
        Integer tempMin,tempMin1,tempMin2;
        String textDay,textDay1,textDay2;
        System.out.println(run);
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(run);
            fxDate= new SimpleDateFormat("yyyy-mm-dd").parse(root.get("daily").get(0).get("fxDate").asText());
            tempMax= root.get("daily").get(0).get("tempMax").asInt();
            tempMin= root.get("daily").get(0).get("tempMin").asInt();
            textDay= root.get("daily").get(0).get("textDay").asText();
            fxDate1= new SimpleDateFormat("yyyy-mm-dd").parse(root.get("daily").get(1).get("fxDate").asText());
            tempMax1= root.get("daily").get(1).get("tempMax").asInt();
            tempMin1= root.get("daily").get(1).get("tempMin").asInt();
            textDay1= root.get("daily").get(1).get("textDay").asText();
            fxDate2= new SimpleDateFormat("yyyy-mm-dd").parse(root.get("daily").get(2).get("fxDate").asText());
            tempMax2= root.get("daily").get(2).get("tempMax").asInt();
            tempMin2= root.get("daily").get(2).get("tempMin").asInt();
            textDay2= root.get("daily").get(2).get("textDay").asText();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Weather w1=new Weather(id, fxDate,  tempMax,tempMin, textDay);
        Weather w2=new Weather(id, fxDate1,  tempMax1,tempMin1, textDay1);
        Weather w3=new Weather(id, fxDate2, tempMax2,tempMin2, textDay2);
        List<Weather>weathers=new ArrayList<>();
        System.out.println(w1.getFxDate()+" "+w2.getFxDate()+" "+w3.getFxDate());
        weathers.add(w1);
        weathers.add(w2);
        weathers.add(w3);
        return weathers;

    }
}
