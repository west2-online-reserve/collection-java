package com.animals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*9. 一个Test类, 用于测试你写的类功能是否正常

创建一个宠物店实例，给定余额，初始化动物列表，一个空的顾客列表
测试买入动物，招待顾客，歇业
建议多拿点例子测试，发现bug可以马上改，多考虑代码严谨性*/
public class Test {
    public static void main(String[] args) {
        // Create an instance of the Animal class
        Animals cat1 = new Cat("加菲猫", 1, "雄性", 1200);
        Animals cat2 = new Cat("咪咪", 2, "雌性", 800);
        Animals dog1 = new ChineseDog("旺财", 3, "雄性", 1500, true);
        Animals rabbit = new Rabbit("小白", 2, "雌性", 500);

        Customer customer1 = new Customer("张三", 10);
        Customer customer2 = new Customer("李四", 20);

        System.out.println(cat1);
        System.out.println(customer1);

        MyAnimalShop shop = new MyAnimalShop(200, false);
        shop.getAnimalArrayList().add(cat1);
        shop.getAnimalArrayList().add(cat2);

        try {
            shop.buy(cat1);//进货成功。
            shop.buy(dog1);//InsufficientBalanceException: 余额不足！
        } catch (
                InsufficientBalanceException e) {
            e.printStackTrace();
        }

        shop.setIsClosed(true);


        try {
            shop.reception(customer1);//店铺已经歇业。
        } catch (
                AnimalNotFountException e) {
            e.printStackTrace();
        }

        shop.setIsClosed(false);
        shop.getCustomerArrayList().add(customer2);
        try {
            shop.reception(customer2);//欢迎再次光临！
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }


        try {
            shop.sell(dog1);//AnimalNotFountException: 店里没有该动物
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }
        try {
            shop.sell(cat1);
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }
        try {
            shop.sell(cat2);
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }

        shop.close();

        for (int i = 0; i < shop.getCustomerArrayList().size(); i++) {
            System.out.println(shop.getCustomerArrayList().get(i));
        }
        for (int i = 0; i < shop.getAnimalArrayList().size(); i++) {
            System.out.println(shop.getAnimalArrayList().get(i));
        }

        shop.reopen();
    }
}

