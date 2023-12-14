package com.Eeeeye.base.work2;

import java.util.ArrayList;

public class AnimalShopTest {
    public static void main(String[] args) {
        Animal dog1 = new Dog();

        Animal cat1 = new Cat();
        Animal cat2 = new Cat();
        Animal panda1= new Panda();
        //在购买前创建一个动物对象
        Animal panda2 = new Panda();
        Customer customer1 = new Customer( "hwy");
        Customer customer2 = new Customer( "zht");


        MyAnimalShop myAnimalShop = new MyAnimalShop();
        myAnimalShop.setBanlance(2400);
        myAnimalShop.getAnimalArrayList().clear();
        myAnimalShop.getAnimalArrayList().clear();
        myAnimalShop.buy(cat1);
        myAnimalShop.buy(cat2);
        myAnimalShop.buy(panda1);

        myAnimalShop.buy(panda2);
        //查看当前余额
        System.out.println(myAnimalShop.getBanlance());
        //测试余额不足异常
        myAnimalShop.buy(dog1);
        for (int i = 0; i < myAnimalShop.getAnimalArrayList().size(); i++) {
            Animal animali = myAnimalShop.getAnimalArrayList().get(i);
            //遍历输出此时的动物列表
            System.out.println(animali);
        }
        myAnimalShop.entertain(customer1,cat1);
        myAnimalShop.entertain(customer2,panda2);
        //测试无动物异常
        myAnimalShop.entertain(customer2,dog1);
        //测试余额盈利是否正常
        System.out.println(myAnimalShop.getBanlance());
        myAnimalShop.close();




    }
}