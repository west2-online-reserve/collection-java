package org.example.CityController;

import org.example.mapper.CityMapper;
import org.example.mapper.WeatherMapper;
import org.example.pojo.City;
import org.example.pojo.Weather;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class QueryCity {
    public void query(CityMapper cityMapper, WeatherMapper weatherMapper){
        //查询
        System.out.println("请输入你想要查询的方式：1.根据城市id查询  2.根据城市名称查询 3.根据城市经纬度进行多条件查询 （0.退出）");
        Scanner scanner=new Scanner(System.in);
        int choice=scanner.nextInt();
        int n;
        List<City> cities= cityMapper.selectAll();
        List<Weather>weathers=weatherMapper.selectAll();
        boolean flag=true;
        while(flag){
            switch(choice){
                case 1:
                    cities=quiryCityByIds(cityMapper,weatherMapper);
                    System.out.println("查询结果如下：");
                    for (City city:cities) {
                        System.out.println(city.toString());
                        weathers=weatherMapper.selectById(city.getId());
                        for(Weather w:weathers){
                            System.out.println(w.toString());
                        }
                        System.out.println("");
                    }
                    break;
                case 2:
                    cities=quiryCityByNames(cityMapper,weatherMapper);
                    System.out.println("查询结果如下：");
                    for (City city:cities) {
                        System.out.println(city.toString());
                        weathers=weatherMapper.selectById(city.getId());
                        for(Weather w:weathers){
                            System.out.println(w.toString());
                        }
                        System.out.println("");
                    }
                    break;
                case 3:
                    System.out.println("请输入带查询的纬度：");
                    double lat=scanner.nextDouble();
                    System.out.println("请输入带查询的经度：");
                    double lon=scanner.nextDouble();
                    City city=cityMapper.selectByCondition(lat,lon);
                    weathers=weatherMapper.selectById(city.getId());
                    for(Weather w:weathers){
                        System.out.println(w.toString());
                    }
                    System.out.println("");
                case 0:
                    return;
                default:
                    System.out.println("输入错误，请重新输入");
                    flag=false;
                    break;

            }
            System.out.println("请输入你想要查询的方式：1.根据城市id查询  2.根据城市名称查询 3.根据城市经纬度进行多条件查询 （0.退出）");
            choice=scanner.nextInt();
        }
    }
    public int quiryId(CityMapper cityMapper,String name){
        List<City>cities=cityMapper.selectAll();
        System.out.println(name);
        for (City city:cities) {
//            System.out.println("city"+city.getName());
//            System.out.println("name"+name);
            if(city.getName().equals(name)){
                return city.getId();
            }
        }
        return -1;
    }
    public List<City> quiryCityByIds(CityMapper cityMapper,WeatherMapper weatherMapper){
        System.out.println("请输入要查询的id：（输入-1退出）");
        List<Integer>ids = new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        int id=scanner.nextInt();
        while(id!=-1){
            ids.add(id);
            id=scanner.nextInt();
        }

        return cityMapper.selectByIds(ids);
    }
    public List<City> quiryCityByNames(CityMapper cityMapper,WeatherMapper weatherMapper){
        List<String>names=new ArrayList<>();
        List<Integer>ids = new ArrayList<>();
        System.out.println("请输入要查询的姓名：（输入exit退出）");
        Scanner scanner=new Scanner(System.in);
        String name=scanner.nextLine();
        while(!name.equals("exit")){
            names.add(name);
            name=scanner.nextLine();
        }

        return cityMapper.selectByNames(names);
    }


}
