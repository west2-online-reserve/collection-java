package com.petshop.entity;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<>();
        animals.add(new ChineseRuralDog("大黄",5, '公',true));
        animals.add(new Cat("咪咪",2,'母'));
        animals.add(new Rabbit("小白", 1, '母'));

        ArrayList<Customer> customers = new ArrayList<>();

        MyAnimalShop myAnimalShop = new MyAnimalShop(200.0, animals, customers, true);

        try {
            myAnimalShop.serveCustomer("小红");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }finally {
            myAnimalShop.closeShop();
        }

        try {
            myAnimalShop.openShop();
            myAnimalShop.buyAnimal(new Cat("毛毛", 2, '母'));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }finally {
            myAnimalShop.closeShop();
        }

        try {
            myAnimalShop.buyAnimal(new ChineseRuralDog("旺财", 2, '公', false));
            myAnimalShop.openShop();
            myAnimalShop.buyAnimal(new Cat("球球", 1, '公'));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            myAnimalShop.closeShop();
        }

        try {
            myAnimalShop.serveCustomer("小明");
            myAnimalShop.openShop();
            myAnimalShop.serveCustomer("小明");
            myAnimalShop.serveCustomer("小明");
            myAnimalShop.serveCustomer("小明");
            myAnimalShop.serveCustomer("小明");
            myAnimalShop.serveCustomer("小天");
            myAnimalShop.serveCustomer("小天");
            myAnimalShop.serveCustomer("小红");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        } finally {
            myAnimalShop.closeShop();
        }
    }
}
