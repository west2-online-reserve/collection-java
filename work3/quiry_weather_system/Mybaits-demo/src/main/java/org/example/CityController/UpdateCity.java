package org.example.CityController;

import org.example.mapper.CityMapper;
import org.example.pojo.City;

import java.util.Scanner;

public class Update {
    public void update(CityMapper cityMapper){
        System.out.println("请输入该修改的数据：1.姓名 2.级别");
        boolean flag=true;
        Scanner scanner=new Scanner(System.in);
        int choice;
        City city = new City();
        while(flag){
            choice=scanner.nextInt();
            switch (choice){
                case 1:
                    System.out.println("请输入该修改城市的id：");
                    int id=scanner.nextInt();
                    city.setId(id);
                    System.out.println("请输入想要改成什么名字");
                    String name=scanner.nextLine();
                    city.setName(name);
                    cityMapper.update(city);
                    break;
                case 2:
                    System.out.println("请输入该修改城市的id：");
                    id=scanner.nextInt();
                    city.setId(id);
                    System.out.println("请输入级别变化后：");
                    int rank=scanner.nextInt();
                    city.setRank(rank);
                    cityMapper.update(city);
                    break;
                default:
                    flag=false;
                    break;

            }
        }

    }
}
