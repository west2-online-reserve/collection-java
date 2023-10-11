package com.west2.work2;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Animal> pets = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        //初始化动物列表
        Cats betty = new Cats("贝奇", 4, '公');
        pets.add(betty);
        ChinesePastoralDog diudiu = new ChinesePastoralDog("丢丢", 1, '公', false);
        pets.add(diudiu);
        Pigs bengbeng = new Pigs("奔奔", 2, '公');
        //添加第一只动物进列表
        pets.add(bengbeng);

        MyAnimalShop shop = new MyAnimalShop(300, pets, customers, false);
        //测试开店
        shop.setUp();

        ChinesePastoralDog dog = new ChinesePastoralDog("大黄", 2, '公', true);
        Cats cats = new Cats("小花", 1, '母');
        Pigs pigs = new Pigs("大胖", 3, '公');
        //测试买入动物
        try {
            shop.buyNewAnimal(dog);
            shop.buyNewAnimal(pigs);
            shop.buyNewAnimal(cats);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.toString());
        }

        //测试招待顾客
        LocalDate time = LocalDate.of(2023, 10, 6);
        //第一位顾客驾到
        Customer customer1 = new Customer("李泽言", 3, time);
        try {
            shop.entertainCustomers(customer1);
        } catch (ShopClosed e) {
            System.out.println(e.toString());
        } catch (AnimalNotFoundException e) {
            System.out.println(e.toString());
        }
        //第二位顾客驾到
        Customer customer2 = new Customer("张若昀", 1, LocalDate.now());
        try {
            shop.entertainCustomers(customer2);
        } catch (ShopClosed e) {
            System.out.println(e.toString());
        } catch (AnimalNotFoundException e) {
            System.out.println(e.toString());
        }
        //第三位顾客驾到
        Customer customer3 = new Customer("孟宴臣", 1, LocalDate.now());
        try {
            shop.entertainCustomers(customer3);
        } catch (ShopClosed e) {
            System.out.println(e.toString());
        } catch (AnimalNotFoundException e) {
            System.out.println(e.toString());
        }
        //歇业
        shop.Shutdown();

        //第四位顾客驾到
        Customer customer4 = new Customer("神里绫华", 4, LocalDate.now());
        try {
            shop.entertainCustomers(customer4);
        } catch (ShopClosed e) {
            System.out.println(e.toString());
        } catch (AnimalNotFoundException e) {
            System.out.println(e.toString());
        }
    }
}