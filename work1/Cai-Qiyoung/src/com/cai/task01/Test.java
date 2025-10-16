package com.cai.task01;

import java.time.LocalDate;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        MyPetShop myPetShop = new MyPetShop("JAVA PetShop",2000);

        Animal.Dog D0 = new Animal.Dog("旺财",2,"Boy","中华田园犬",true);
        Animal.Dog D1 = new Animal.Dog("旺旺",1,"Girl","金毛",true);
        Animal.Dog D2 = new Animal.Dog("啵啵",3,"Girl","拉布拉多",true);
        Animal.Dog D4 = new Animal.Dog("凯勒",2,"Boy","纯种杜宾犬",true);
        D4.setPrice(5000);

        Animal.Cat C0 = new Animal.Cat("黑糖",1,"Girl","狸猫");
        Animal.Cat C1 = new Animal.Cat("薯条",3,"Girl","橘猫");
        Animal.Cat C2 = new Animal.Cat("可乐",2,"Girl","布偶猫");
        Animal.Cat C3 = new Animal.Cat("白糖",1,"Girl","英短");

        Customer customer0 = new Customer("锦智",0, LocalDate.of(0, 1, 1));
        Customer customer1 = new Customer("俊凡",5, LocalDate.of(2025, 9, 30));
        Customer customer2 = new Customer("荣琛",1, LocalDate.of(2024, 5, 30));
        Customer customer3 = new Customer("志航",0, LocalDate.of(0, 1, 1));

        myPetShop.getAnimals().add(D0);
        D0.setPrice(200);
        myPetShop.getAnimals().add(C0);
        C0.setPrice(400);
        myPetShop.getAnimals().add(C1);
        C1.setPrice(300);


        myPetShop.getCustomers().add(customer1);
        myPetShop.getCustomers().add(customer2);

        try {
            // 买入动物
            myPetShop.buyAnimal(D1,D1.getPrice(),400);
            myPetShop.buyAnimal(D2,D2.getPrice(),600);
            myPetShop.buyAnimal(D4,D4.getPrice(),40000);
            myPetShop.buyAnimal(C2,C2.getPrice(),2000);
            myPetShop.buyAnimal(C3,C3.getPrice(),4000);

            // 尝试买入一个超出余额的动物
            myPetShop.buyAnimal(D4,D4.getPrice(),5000); // 抛出异常
        } catch (InsufficientBalanceException e) {
            System.out.println("买入失败: " + e.getMessage());
        }


        try {
            // 招待客户，出售动物
            myPetShop.serveCustomer(customer0, C1);
            myPetShop.serveCustomer(customer3, D2);
            myPetShop.serveCustomer(customer2, C2);

            // 尝试出售不存在的动物
            myPetShop.serveCustomer(customer1, new Animal.Cat("不存在", 1, "Girl","UnKnown")); // 抛出异常
        } catch (AnimalNotFoundException e) {
            System.out.println("出售失败: " + e.getMessage());
        }

        // 歇业
        myPetShop.closeShop(LocalDate.now());
    }


    }
