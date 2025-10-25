package com.animalshop;

import com.animalshop.exception.*;
import com.animalshop.model.*;
import com.animalshop.model.MyAnimalShop;

import java.util.ArrayList;
import java.util.List;



public class Test {
    public static void main(String[] args) {
        MyAnimalShop myAnimalShop = new MyAnimalShop(300);
        List<Animal> animalList = new ArrayList<>();
        List<Customer> customerList = new ArrayList<>();
        ChineseRuralDog dog = new ChineseRuralDog("大黄", 2, Gender.MALE, true);
        Cat cat = new Cat("小猫", 3, Gender.FEMALE);
        Pig pig = new Pig("小猪", 1, Gender.MALE);
        //测试买入动物
        try{
            myAnimalShop.purchaseAnimal(dog);
        }catch (InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }
        try{
            myAnimalShop.purchaseAnimal(pig);
        }catch (InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }
        //余额不足
        try{
            myAnimalShop.purchaseAnimal(cat);
        }catch (InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }

        Customer ZhangSan = new Customer("张三");
        Customer LiSi = new Customer("李四");
        Customer WangWu = new Customer("王五");
        //测试招待顾客，出售动物
        //测试顾客有想买的动物的情况
        try{
            myAnimalShop.serveCustomer(ZhangSan, "大黄");
        }catch (AnimalNotFoundException e){
            System.out.println(e.getMessage());
        }
        //测试查找已出售的动物
        try{
            myAnimalShop.serveCustomer(LiSi, "大黄");
        }catch (AnimalNotFoundException e){
            System.out.println(e.getMessage());
        }
        //测试找不到动物的情况
        try{
            myAnimalShop.serveCustomer(WangWu, "鼠王");
        }catch (AnimalNotFoundException e){
            System.out.println(e.getMessage());
        }
        //测试顾客没有明确想买的动物的情况
        try{
            myAnimalShop.serveCustomer(WangWu);
        }catch (AnimalNotFoundException e){
            System.out.println(e.getMessage());
        }

        myAnimalShop.closeShop();
        //测试歇业后顾客再访问的情况
        try{
            myAnimalShop.serveCustomer(LiSi, "小猫");
        }catch (AnimalNotFoundException e){
            System.out.println(e.getMessage());
        }
        //测试歇业后再进行歇业操作的情况
        myAnimalShop.closeShop();
        //测试重新开业后的营业
        myAnimalShop.openShop();
        try{
            myAnimalShop.serveCustomer(LiSi, "小猫");
        }catch (AnimalNotFoundException e){
            System.out.println(e.getMessage());
        }

        myAnimalShop.closeShop();
    }

}


