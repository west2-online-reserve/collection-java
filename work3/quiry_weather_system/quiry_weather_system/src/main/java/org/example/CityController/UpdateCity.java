package org.example.CityController;

import org.example.WeatherController.UpdateWeather;
import org.example.mapper.CityMapper;
import org.example.mapper.WeatherMapper;
import org.example.pojo.City;
import org.example.pojo.Weather;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class UpdateCity {
    public void update(CityMapper cityMapper,WeatherMapper weatherMapper) throws IOException {
        System.out.println("请输入该修改的数据：1.姓名 2.级别");
        boolean flag=true;
        Scanner scanner=new Scanner(System.in);
        int choice=scanner.nextInt();
        City city = new City();
        while(flag){
            switch (choice){
                case 1:
                    List<City> cities=new QueryCity().quiryCityByIds(cityMapper,weatherMapper);
                    for(City c:cities){
                        scanner.nextLine();
                        System.out.println("请输入想要改成什么名字");
                        String name=scanner.nextLine();
                        c.setName(name);
                        cityMapper.update(c);
                        System.out.println("是否更新天气信息：1：yes 0：no");
                        if(scanner.nextInt()==1){
                            new UpdateWeather().update(cityMapper,weatherMapper,c.getId());
                        }
                    }

                    break;
                case 2:
                    cities=new QueryCity().quiryCityByIds(cityMapper,weatherMapper);
                    for(City c:cities){
                        System.out.println("请输入级别变化后：");
                        int dengji=scanner.nextInt();
                        c.setDengji(dengji);
                        cityMapper.update(c);
                        System.out.println("是否更新天气信息：1：yes 0：no");
                        if(scanner.nextInt()==1){
                            new UpdateWeather().update(cityMapper,weatherMapper,c.getId());
                        }
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("输入错误，请重新输入");
                    break;

            }
            System.out.println("请输入该修改的数据：1.姓名 2.级别 (0：退出)");
            choice=scanner.nextInt();
        }

    }
}
