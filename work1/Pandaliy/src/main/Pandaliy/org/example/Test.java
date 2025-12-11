package org.example;

import org.example.Exception.AnimalNotFountException;
import org.example.Exception.InsufficientBalanceExceptio;
import org.example.Exception.NoAnimalsMeetException;
import org.example.Exception.ShopClosedException;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        //初始化宠物店和宠物及其输出
        MyAnimalShop myAnimalShop = new MyAnimalShop(500);
        Cat xiaokai = new Cat("小凯", 5, 1);
        ChineseRuralDog yellowfur = new ChineseRuralDog("黄毛", 3, 1, true);
        Hamster lizi = new Hamster("栗子", 1, 0);
        ArrayList<Cat> cats = new ArrayList<Cat>();
        for (int i = 1; i < 4; i++) {
            Cat cat = new Cat(i + "号小猫", i, i % 2);
            cats.add(cat);
        }
        System.out.println(xiaokai);
        System.out.println(yellowfur);
        System.out.println(lizi);

        //测试购买动物和余额不足的异常处理
        try {
            myAnimalShop.buyNewAnimals(xiaokai);
            myAnimalShop.buyNewAnimals(lizi);
            myAnimalShop.buyNewAnimals(yellowfur);
            myAnimalShop.buyNewAnimals(cats.get(0));
            myAnimalShop.buyNewAnimals(cats.get(1));
        } catch (InsufficientBalanceExceptio e) {
            System.out.println("已处理余额不足的异常。\n");
        }

        //测试客户招待和宠物店没有宠物出售的异常处理
        try {
            myAnimalShop.entertainCustomers("xiaochen", Cat.class, 300);
            myAnimalShop.entertainCustomers("xiaochen", ChineseRuralDog.class, 200);
            myAnimalShop.entertainCustomers("jiaqi", Hamster.class, 200);
            myAnimalShop.entertainCustomers("xiaochen", ChineseRuralDog.class, 200);
        } catch (AnimalNotFountException e) {
            System.out.println("已处理宠物店没有宠物出售的异常。\n");
        }

        //测试宠物店没有顾客所需动物的异常处理
        for (int i = 0; i < 3; i++) {
            myAnimalShop.buyNewAnimals(cats.get(i));//买入,使宠物店里有可出售的宠物
        }

        try {
            myAnimalShop.entertainCustomers("guangliang", Hamster.class, 200);
        } catch (NoAnimalsMeetException e) {
            System.out.println("已处理宠物店没有顾客所需宠物的异常。\n");
        }

        //测试歇业函数和歇业后操作的异常处理
        myAnimalShop.closeDown();
        try {
            myAnimalShop.buyNewAnimals(cats.get(0));
        } catch (ShopClosedException e) {
            System.out.println("已处理宠物店已关闭的异常。\n");
        }

        try {
            myAnimalShop.entertainCustomers("guangliang", Hamster.class, 200);
        } catch (ShopClosedException e) {
            System.out.println("已处理宠物店已关闭的异常。\n");
        }

        try {
            myAnimalShop.closeDown();
        } catch (ShopClosedException e) {
            System.out.println("已处理宠物店已关闭的异常。\n");
        }
    }
}
