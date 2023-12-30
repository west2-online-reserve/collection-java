package bonus.test;

import java.util.Scanner;

import bonus.weather.WeatherManage;

/**
 * 测试类
 * @author 1293978818
 */
public class WeatherTest {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice = 0;
        WeatherManage weatherManage = new WeatherManage();

        weatherManage.updateWeather();
        System.out.println("欢迎来到天气管理系统，有如下几种功能");
        System.out.println("1.添加城市信息");
        System.out.println("2.查询指定城市天气信息");
        System.out.println("3.查看所有可用城市");
        System.out.println("4.删除指定城市");
        System.out.println("5.退出");

        while(true){
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    weatherManage.addCity(sc);
                    break;
                case 2:
                    weatherManage.printWeather(sc);
                    break;
                case 3:
                    weatherManage.printAllCities();
                    break;
                case 4:
                    weatherManage.deleteCity(sc);
                    break;
                case 5:
                    System.out.println("感谢您的使用，再见");
                    System.exit(0);
                    break;
                default:
                    System.out.println("您的输入有误，请重试");
                    break;
            }
            System.out.println("还有什么需要做的吗");
        }

    }
}
