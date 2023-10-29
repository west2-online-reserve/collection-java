package com.Eeeeye.base.work2;

import java.util.ArrayList;

public class AnimalShopTest {
    public static void main(String[] args) {
        Animal dog1 = new Dog();

        Animal cat1 = new Cat();
        Animal cat2 = new Cat();
        Animal panda1= new Panda();
        Animal panda2 = new Panda();//在购买前创建一个动物对象
        Customer customer1 = new Customer( "hwy");
        Customer customer2 = new Customer( "zht");


        MyAnimalShop myAnimalShop = new MyAnimalShop();
        myAnimalShop.setBanlance(2400);
        myAnimalShop.customerArrayList.clear();
        myAnimalShop.animalArrayList.clear();
        myAnimalShop.buy(cat1);
        myAnimalShop.buy(cat2);
        myAnimalShop.buy(panda1);

        myAnimalShop.buy(panda2);
        System.out.println(myAnimalShop.banlance);
        myAnimalShop.buy(dog1);//测试余额不足异常
        for (int i = 0; i < myAnimalShop.animalArrayList.size(); i++) {
            Animal animali = myAnimalShop.animalArrayList.get(i);
            System.out.println(animali);//遍历输出此时的动物列表
        }
        myAnimalShop.entertain(customer1,cat1);
        myAnimalShop.entertain(customer2,panda2);
        myAnimalShop.entertain(customer2,dog1);//测试无动物异常

        System.out.println(myAnimalShop.banlance);//测试余额盈利是否正常
        myAnimalShop.close();




    }
}