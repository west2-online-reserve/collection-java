package com.Eeeeye.base.work2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class MyAnimalShop extends Animal implements AnimalShop {
    Customer customer0 = new Customer();

    Animal dog = new Dog();

    Animal cat = new Cat();
    Animal panda = new Panda();

    double profit = 0;
    double banlance;

    Customer customer = new Customer();
    Scanner scanner = new Scanner(System.in);


    ArrayList<Animal> animalArrayList = new ArrayList<>();//动物列表
    ArrayList<Customer> customerArrayList = new ArrayList<>();//客户列表

    public void setBanlance(double banlance) {
        this.banlance = banlance;
    }

    public boolean open;

    @Override
    public void buy(Animal animalSpecie) {//运行时异常可忽略
        System.out.println("请输入姓名");
        animalSpecie.setName(scanner.next());
        System.out.println("请输入年龄");
        animalSpecie.setAge(scanner.nextInt());
        System.out.println("请输入性别");
        animalSpecie.setGender(scanner.next());
        try {
            if (banlance < animalSpecie.getPrice()) {
                throw new InsufficientBalanceException("余额不足");
            } else {
                System.out.println("买入" + animalSpecie.getName());
                banlance = banlance - animalSpecie.getPrice();//需要在cat,doga,panda类中分别补上set,get方法，否则无法正常获取和设置价格
                animalArrayList.add(animalSpecie);
            }
        } catch (InsufficientBalanceException e) {
            System.out.println("请补充余额");
        }




    }

    @Override
    public void entertain(Customer customer2, Animal animalSpecie) {//可以用什么方式实现一个客户购买两个动物呢
        try {


            if (customer0.localDateTime.getHour() >= 22 || customer0.localDateTime.getHour() <= 6) {
                throw new NotOpenException("商店歇业");
            }


            else if (!animalArrayList.contains(animalSpecie)) {//简写
                throw new AnimalNotFoundException("商店中暂无此宠物");
            }
            else {System.out.println("招待客户" + customer2);
            customerArrayList.add(customer2);

            animalArrayList.remove(animalSpecie);//移出列表
            System.out.println("卖出了" + animalSpecie);


            customer2.setTime(customer2.getTime() + 1);//增加顾客光顾次数
            banlance = animalSpecie.getPrice() + banlance + animalSpecie.getProfit();
            profit = animalSpecie.getProfit() + profit;}
        } catch (NotOpenException e) {
            System.out.println("请明日再来");
        } catch (AnimalNotFoundException e) {
            System.out.println("请换一个宠物购买吧");
        }


    }
    //等待添加异常

    @Override
    public void close() {
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.getHour() >= 20 || localDateTime.getHour() <= 6) {
            System.out.println("歇业中");
            System.out.println("利润为" + profit);//输出利润
            for (int i = 0; i < customerArrayList.size(); i++) {//遍历输出顾客列表
                Customer customeri = customerArrayList.get(i);
                System.out.println(customeri);//默认调用toString方法
            }
            for (int i = 0; i < animalArrayList.size(); i++) {//遍历输出顾客列表
                Animal animal = animalArrayList.get(i);
                System.out.println(animal);//默认调用toString方法
            }
        } else {
            System.out.println("营业中");
        }
    }


}

