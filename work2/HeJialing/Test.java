package src.src;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
      /*
        一个Test类, 用于测试你写的类功能是否正常
        创建一个宠物店实例，给定余额，初始化动物列表，一个空的顾客列表
        测试买入动物，招待顾客，歇业
        建议多拿点例子测试，发现bug可以马上改，多考虑代码严谨性

       */
//创建一个宠物店实例，给定余额，初始化动物列表，一个空的顾客列表
        ArrayList listOfAnimal = new ArrayList();
        ArrayList listOfCustomer = new ArrayList();
        MyAnimalShop shop = new MyAnimalShop(1000, listOfAnimal, listOfCustomer, false);
        //
        //猫实例
        AbstractAnimal cat1 = new Cat(" 喵喵 ", 1, " 母 ", 200, 300);
        AbstractAnimal cat2 = new Cat(" 咪咪 ", 1, " 公 ", 200, 300);

        //狗实例
        AbstractAnimal dog1 = new ChinesePastoralDog(" 旺财 ", 2, " 公 ", 100, 200);
        AbstractAnimal dog2 = new ChinesePastoralDog(" 来福 ", 1, " 母 ", 100, 200);

        //测试买入动物
        System.out.println("测试买入动物");
        try {
            shop.buyNewAnimals(cat1);
            shop.buyNewAnimals(cat2);
            System.out.println(listOfAnimal);
            System.out.println(" 店铺余额 " + shop.getInsufficientBalance());
            System.out.println();
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
        }
        //测试招待客户
        System.out.println("测试招待客户");
        LocalDate date1 = LocalDate.of(2023, 10, 10);
        LocalDate date2 = LocalDate.of(2023, 10, 23);
        //可能出错
        try {
            Customer customer1 = new Customer("小明", 2, date1, cat1);
            shop.sellAnimals(customer1, listOfAnimal);
            shop.serveCustomer(customer1);
            Customer customer2 = new Customer("小红", 3, date2, cat1);
            shop.sellAnimals(customer2, listOfAnimal);
            shop.serveCustomer(customer2);
        } catch (AnimalNotFoundException e) {
            e.printStackTrace();
        }


        //歇业
        System.out.println("测试歇业");
        //当天日期
        LocalDate localDate = LocalDate.of(2023, 10, 23);
        shop.shutdown(localDate, listOfCustomer);


    }
}
