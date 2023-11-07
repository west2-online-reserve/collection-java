package com.west2.javawork2;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        //创建动物与顾客列表
        ArrayList<Animal> animalArrayList = new ArrayList<>();
        ArrayList<Customer> customerArrayList = new ArrayList<>();

        //设定宠物店的动物
        Cat cat = new Cat("Tom",3,"Male",300);
        Mouse mouse = new Mouse("Jerry",3,"Male",110);
        Dog dog = new Dog("Lumian",3,"Male",200,true);


        //设定顾客三人
        Customer c1= new Customer("Yui",0, LocalDate.now());
        Customer c2= new Customer("Mio",0, LocalDate.now());
        Customer c3= new Customer("Mugi",0, LocalDate.now());

        //设定宠物店参数
        MyAnimalShop shop = new MyAnimalShop(0.0,10000.0,true,animalArrayList,customerArrayList);


        //宠物店开业
        shop.open();

        //测试买入宠物
        try{
            shop.purchase(cat);
            shop.purchase(dog);
        }catch (InsufficientBalanceException e){
            System.out.println(e);
        }
        shop.animalArrayList.add(cat);
        shop.animalArrayList.add(dog);
        shop.animalArrayList.add(mouse);

        //测试招待顾客
        //第一位
        try {
            shop.receiveCustomer(c1);
        }catch (AnimalNotFountException e) {
            System.out.println(e);
        }

        //第二位
        try {
            shop.receiveCustomer(c2);
        }catch (AnimalNotFountException e) {
            System.out.println(e);
        }


        //歇业
        shop.close();

        //第三位
        try {
            shop.receiveCustomer(c3);
        }catch (AnimalNotFountException e) {
            System.out.println(e);
        }


    }
}
