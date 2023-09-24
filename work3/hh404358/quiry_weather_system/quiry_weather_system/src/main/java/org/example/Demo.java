package org.example;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.CityController.*;
import org.example.WeatherController.AddWeather;
import org.example.mapper.CityMapper;
import org.example.mapper.WeatherMapper;
import org.example.pojo.City;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static java.lang.System.exit;

public class Demo {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


        //1. 加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //3. 执行sql
        //参数是一个字符串，该字符串必须是映射配置文件的namespace.id
//        List<User> users = sqlSession.selectList("test.selectAll");
        //3.1获取UserMapper接口的代理对象
        CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
        WeatherMapper weatherMapper = sqlSession.getMapper(WeatherMapper.class);
        //3.2调用sql方法
        /*List<User> user=userMapper.selectByCondition(id);*/
        /*System.out.println(user);*/
        boolean flag=true;
        System.out.println("请选择想要实现的功能：1.增 2.删 3.查 4.改 0.退出");
        int choice=scanner.nextInt();
        while(flag){
            switch (choice){
                case 1:
                    //添加城市信息及三天内天气信息
                    City city=new AddCity().add(cityMapper);
                    new AddWeather().add(weatherMapper,cityMapper,city.getId());
                    break;
                case 2:
                    //删除城市信息及三天内天气信息
                    new DeleteCity().delete(cityMapper,weatherMapper);
                    break;
                case 3:
                    //查询城市信息及三天内天气信息
                    new QueryCity().query(cityMapper,weatherMapper);
                    break;
                case 4:
                    //更新城市信息及三天内天气信息
                    new UpdateCity().update(cityMapper,weatherMapper);
                    break;
                case 0:
                    exit(0);
                default:
                    System.out.println("输入错误");
                    break;
            }
            System.out.println("请选择想要实现的功能：1.增 2.删 3.查 4.改 0.退出");
            if(scanner.hasNext()){
                choice=scanner.nextInt();
            }
            else {
                scanner.nextLine();
                scanner=new Scanner(System.in);
                if(scanner.hasNext()){
                    choice=scanner.nextInt();
                }
            }

        }

        //4. 释放资源
        sqlSession.close();
    }
}