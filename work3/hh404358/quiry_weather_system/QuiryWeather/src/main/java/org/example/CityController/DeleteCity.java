package org.example.CityController;

import org.example.WeatherController.DeleteWeather;
import org.example.mapper.CityMapper;
import org.example.mapper.WeatherMapper;
import org.example.pojo.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeleteCity {
    public void delete(CityMapper cityMapper, WeatherMapper weatherMapper){
        //删除
        System.out.println("请输入你想要删除的方式：1.根据城市id删除  2.根据城市名称删除  (0:退出）");
        Scanner scanner=new Scanner(System.in);
        int choice=scanner.nextInt();
        int n;
        List<City> cities= cityMapper.selectAll();
        boolean flag=true;
        while(flag){
            switch(choice){
                case 1:
                    //删除城市信息的同时删除城市天气信息
                    List<Integer>ids=deleteCityByIds(cityMapper);
                    new DeleteWeather().delete(weatherMapper,cityMapper,ids);
                    break;
                case 2:
                    ids=deleteCityByNames(cityMapper);
                    new DeleteWeather().delete(weatherMapper,cityMapper,ids);
                    break;
                case 0:
                    return;
                default:
                    flag=false;
                    System.out.println("输入错误，请重新输入");
                    break;

            }
            System.out.println("请输入你想要删除的方式：1.根据城市id删除  2.根据城市名称删除  (0:退出）");
            choice=scanner.nextInt();
        }

    }
    public List<Integer>deleteCityByIds(CityMapper cityMapper){
        System.out.println("请输入要查询的id：（输入-1退出）");
        List<Integer>ids = new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        int id=scanner.nextInt();
        while(id!=-1){
            ids.add(id);
            id=scanner.nextInt();
        }
        cityMapper.deleteByIds(ids);
        System.out.println("删除成功");
        new ShowCity().showAll(cityMapper);
        return ids;
    }
    public List<Integer> deleteCityByNames(CityMapper cityMapper){
        List<String>names=new ArrayList<>();
        System.out.println("请输入要查询的姓名：（输入exit退出）");
        Scanner scanner=new Scanner(System.in);
        String name=scanner.nextLine();
        List<Integer>ids=new ArrayList<>();
        while(!name.equals("exit")){
            names.add(name);
            ids.add(new QueryCity().quiryId(cityMapper,name));
            name=scanner.nextLine();
        }
        cityMapper.deleteByNames(names);
        System.out.println("删除成功");
        new ShowCity().showAll(cityMapper);
        return ids;
    }

}
