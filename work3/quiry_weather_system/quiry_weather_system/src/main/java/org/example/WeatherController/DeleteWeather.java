package org.example.WeatherController;

import org.example.CityController.QueryCity;
import org.example.CityController.ShowCity;
import org.example.mapper.CityMapper;
import org.example.mapper.WeatherMapper;
import org.example.pojo.City;
import org.example.pojo.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteWeather {
    public void delete(WeatherMapper weatherMapper, CityMapper cityMapper,List<Integer>ids){
        weatherMapper.deleteByIds(ids);
       /* //删除
        System.out.println("请输入你想要删除的方式：1.根据城市id删除  2.根据城市名称删除  (0:退出）");
        Scanner scanner=new Scanner(System.in);
        int choice=scanner.nextInt();
        int n;
        List<Weather> weathers= weatherMapper.selectAll();
        boolean flag=true;
        while(flag){
            switch(choice){
                case 1:
                    System.out.println("请输入要查询的id：（输入-1退出）");
                    List<Integer>ids = new ArrayList<>();
                    int id=scanner.nextInt();
                    while(id!=-1){
                        ids.add(id);
                        id=scanner.nextInt();
                    }
                    weatherMapper.deleteByIds(ids);
                    System.out.println("删除成功");
                    new ShowWeather().show(weatherMapper);
                    break;
                case 2:
                    List<String>names=new ArrayList<>();
                    System.out.println("请输入要查询的姓名：（输入exit退出）");
                    String name=scanner.nextLine();
                    while(!name.equals("exit")){
                        names.add(name);
                        name=scanner.nextLine();
                    }
                    //weatherMapper.deleteByIds(ids);
                    System.out.println("删除成功");
                    new ShowWeather().show(weatherMapper);
                    break;
                case 0:
                    flag=false;
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
                    flag=false;
                    break;

            }
            System.out.println("请输入你想要删除的方式：1.根据城市id删除  2.根据城市名称删除  (0:退出）");
            choice=scanner.nextInt();
        }*/
    }
}
