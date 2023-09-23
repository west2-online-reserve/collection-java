package org.example.WeatherController;

import org.example.CityController.QueryCity;
import org.example.mapper.CityMapper;
import org.example.mapper.WeatherMapper;
import org.example.pojo.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.Demo.scanner;

public class QueryWeather {
    public void query(WeatherMapper weatherMapper, CityMapper cityMapper){
        //查询
        System.out.println("请输入你想要查询的方式：1.根据城市id查询  2.根据城市名称查询  （0.退出）");
        Scanner scanner=new Scanner(System.in);
        int choice= scanner.nextInt();
        int n;
        List<Weather> weathers= weatherMapper.selectAll();
        List<Integer >ids=new ArrayList<>();
        boolean flag=true;
        while(flag){
            switch(choice){
                case 1:
                    quiryWeatherByIds(cityMapper,weatherMapper);
                    break;
                case 2:
                    //quiryWeatherByNames(cityMapper,weatherMapper);
                    break;
                case 0:
                    flag=false;
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
                    flag=false;
                    break;

            }
            System.out.println("请输入你想要查询的方式：1.根据城市id  2.根据城市名称 (0:退出）");
            choice=scanner.nextInt();
        }
    }
    public void quiryWeatherById(CityMapper cityMapper,WeatherMapper weatherMapper,int id) {
        System.out.println("查询结果如下");
        List<Weather> weathers = weatherMapper.selectById(id);
        for (Weather w : weathers) {
            System.out.println(w.toString());
        }
    }

    public void quiryWeatherByIds(CityMapper cityMapper,WeatherMapper weatherMapper) {
        System.out.println("请输入要查询的id：（输入-1退出）");
        List<Integer> ids = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        while (id != -1) {
            ids.add(id);
            id = scanner.nextInt();
        }
        System.out.println("查询结果如下");
        List<Weather> weathers = weatherMapper.selectByIds(ids);
        int id1 = weathers.get(0).getId();
        for (Weather w : weathers) {
            if (id != w.getId()) {
                System.out.println("");
            }
            System.out.println(w.toString());
            id = w.getId();
        }
    }

}
