package com.peanut;

import com.peanut.Exception.AnimalNotFoundException;
import com.peanut.Exception.InsufficientBalanceException;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop myAnimalShop = new MyAnimalShop();
        Cat cat = new Cat("小白", 2, false);
        ArrayList<Hamster> hamsters = new ArrayList<>();
        ChineseRuralDog dog = new ChineseRuralDog("小黑", 5, true, true);
        for (Integer i = 0; i < 3 ;i++) {
            Hamster hamser = new Hamster("仓鼠" + i + "号", 5, false, 300, "白");
            hamsters.add(hamser);
        }

        //注册买动物，充值以及animalNotFound异常的功能
        try {
            myAnimalShop.buyAnimal(cat);
            myAnimalShop.buyAnimal(dog);
            myAnimalShop.buyAnimal(dog);
            for (Hamster hamster : hamsters) {
                myAnimalShop.buyAnimal(hamster);
            }
        } catch (InsufficientBalanceException e) {
            myAnimalShop.recharge();
        }

        Customer customer = new Customer("小王");

        //测试招待顾客以及异常
        myAnimalShop.entertainCustomer(customer);
        myAnimalShop.close();
        myAnimalShop.entertainCustomer(customer);
        myAnimalShop.open();
        Customer xiaomei = new Customer("小美");
        Customer xiaoli = new Customer("小李");
        try {
            myAnimalShop.entertainCustomer(xiaomei);
        } catch (AnimalNotFoundException e) {
        }

        hamsters.clear();
        for (Integer i = 0; i < 20 ;i++) {
            Hamster hamser = new Hamster("仓鼠_" + i + "号", 5, false, 300, "白");
            hamsters.add(hamser);
        }
        //测试歇业功能
        myAnimalShop.buyAnimal(cat);
        myAnimalShop.buyAnimal(dog);
        myAnimalShop.buyAnimal(cat);
        myAnimalShop.entertainCustomer(xiaomei);
        myAnimalShop.close();
        myAnimalShop.close();
        myAnimalShop.open();
        try {
            for (Hamster hamster : hamsters) {
                myAnimalShop.buyAnimal(hamster);
            }
        } catch (InsufficientBalanceException e) {
        }
        myAnimalShop.close();
    }
}
