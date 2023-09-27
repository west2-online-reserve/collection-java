package org.example.WeatherController;

import org.example.mapper.CityMapper;
import org.example.pojo.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Query {
    public void query(CityMapper cityMapper){
        //查询
        System.out.println("请输入你想要查询的方式：1.根据城市id查询  2.根据城市名称查询 3.根据城市经纬度进行多条件查询 （0.退出）");
        Scanner scanner=new Scanner(System.in);
        int choice;
        int n;
        List<City> cities= cityMapper.selectAll();
        boolean flag=true;
        while(flag){
            System.out.println("请输入你想要查询的方式：1.根据城市id查询  2.根据城市名称查询 3.根据城市经纬度进行多条件查询 （0.退出）");
            choice=scanner.nextInt();
            switch(choice){
                case 1:
                    System.out.println("请输入要查询的id：（输入-1退出）");
                    List<Integer>ids = new ArrayList<>();
                    int id=scanner.nextInt();
                    while(id!=-1){
                        ids.add(id);
                        id=scanner.nextInt();
                    }
                    cities= cityMapper.selectByIds(ids);
                    System.out.println("查询结果如下：");
                    new Show().show(cityMapper);
                    break;
                case 2:
                    List<String>names=new ArrayList<>();
                    System.out.println("请输入要查询的姓名：（输入exit退出）");
                    String name=scanner.nextLine();
                    while(!name.equals("exit")){
                        names.add(name);
                        name=scanner.nextLine();
                    }
                    cityMapper.selectByNames(names);
                    System.out.println("查询结果如下：");
                    new Show().show(cityMapper);
                    break;
                case 3:
                    System.out.println("请输入带查询的纬度：");
                    double lat=scanner.nextDouble();
                    System.out.println("请输入带查询的经度：");
                    double lon=scanner.nextDouble();
                    cityMapper.selectByCondition(lat,lon);
                case 0:
                    flag=false;
                    break;
                default:
                    System.out.println("输入错误，请重新输入");
                    flag=false;
                    System.out.println("请输入你想要查询的方式：1.根据城市id查询  2.根据城市名称查询 3.根据城市类型和级别进行多条件查询");
                    choice=scanner.nextInt();
                    break;

            }
        }
    }
}
