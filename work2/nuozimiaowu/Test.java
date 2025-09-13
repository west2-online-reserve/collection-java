package com.sty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ArrayList<Customer> customerList = new ArrayList<>(); // 初始化顾客列表
        ArrayList<Animal> animalList = new ArrayList<>();     // 初始化动物列表

        //初始化创建宠物店
        MyAnimalShop animalShop = new MyAnimalShop(animalList,customerList,500,false);

        //测试购入动物，打印余额和店里有的动物，直到最后余额不足，抛出异常
        Cat cat1 = new Cat("ketty",2,0,200);
        Cat cat2 = new Cat("tom",1,0,200);
        ChineseRuralDog dog1 = new ChineseRuralDog("wowo",2,1,true);
        ChineseRuralDog dog2 = new ChineseRuralDog("wind",7,1,true);

        animalShop.buy(cat1);
        animalShop.buy(cat2);
        animalShop.buy(dog1);
        //下一行用于测试店里的钱不够购买动物的时候，抛出异常
        //animalShop.buy(dog2);



        //测试顾客访问商店，并且购买宠物（默认是宠物集合里下标为0的一只）
        Customer customer1 = new Customer("Alice",2, LocalDate.now());
        Customer customer2 = new Customer("amber",2,LocalDate.now());
        Customer customer3 = new Customer("chiller",2,LocalDate.now());
        Customer customer4 = new Customer("keli",0,LocalDate.now());
        animalShop.serve(customer1);
        animalShop.serve(customer2);
        animalShop.serve(customer3);
        //这时店里已经没有动物了，下一行用于演示找不到动物时，抛出异常
        //animalShop.serve(customer4);

        //演示商店关门时候的情况
        animalShop.setClose(true);
        animalShop.serve(customer3);

        animalShop.setClose(false);

        //
        animalShop.close();
    }

}
