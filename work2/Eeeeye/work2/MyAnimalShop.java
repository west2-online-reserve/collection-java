package com.Eeeeye.base.work2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {


    private double profit = 0;
    private double banlance;

    public double getBanlance() {
        return banlance;
    }

    public void setBanlance(double banlance) {
        this.banlance = banlance;
    }


    Scanner scanner = new Scanner(System.in);
    //动物列表
    private ArrayList<Animal> animalArrayList = new ArrayList<>();
    //客户列表
    private ArrayList<Customer> customerArrayList = new ArrayList<>();

    public ArrayList<Animal> getAnimalArrayList() {
        return animalArrayList;
    }

    public void setAnimalArrayList(ArrayList<Animal> animalArrayList) {
        this.animalArrayList = animalArrayList;
    }

    public ArrayList<Customer> getCustomerArrayList() {
        return customerArrayList;
    }

    public void setCustomerArrayList(ArrayList<Customer> customerArrayList) {
        this.customerArrayList = customerArrayList;
    }



    @Override
    //运行时异常可忽略
    public void buy(Animal animalSpecie) {
        System.out.println("请输入姓名");
        animalSpecie.setName(scanner.next());
        System.out.println("请输入年龄");
        animalSpecie.setAge(scanner.nextInt());
        System.out.println("请输入性别");
        while (true) {
            //使用temp暂存输入内容
            String temp;
            temp = scanner.next();
            //使用正则表达式检验是否输入正确
            if (temp.matches("[a-z]{4,6}")) {
                animalSpecie.setGender(temp);
                break;
            }
            System.out.println("请输入正确的格式");

        }

        try {
            if (banlance < animalSpecie.getPrice()) {
                throw new InsufficientBalanceException("余额不足");
            } else {
                System.out.println("买入" + animalSpecie.getName());
                //需要在cat,doga,panda类中分别补上set,get方法，否则无法正常获取和设置价格
                banlance = banlance - animalSpecie.getPrice();
                animalArrayList.add(animalSpecie);
            }
        } catch (InsufficientBalanceException e) {
            System.out.println("请补充余额");
        }


    }

    //可以用什么方式实现一个客户购买两个动物呢
    @Override
    public void entertain(Customer customer2, Animal animalSpecie) {
        try {


<<<<<<< HEAD
            if (customer2.localDateTime.getHour() >= 21 || customer2.localDateTime.getHour() <= 6) {
=======
            if (customer0.localDateTime.getHour() >= 21 || customer0.localDateTime.getHour() <= 6) {
>>>>>>> 385fdfbdb7a3e3de1fb3d6db4588872b516cfdf8
                throw new NotOpenException("商店歇业");
            } else if (!animalArrayList.contains(animalSpecie)) {
                //简写
                throw new AnimalNotFoundException("商店中暂无此宠物");
            } else {

                customerArrayList.add(customer2);
                //移出列表
                animalArrayList.remove(animalSpecie);
                System.out.println("卖出了" + animalSpecie);
                //增加顾客光顾次数
                customer2.setTime(customer2.getTime() + 1);
                System.out.println("招待客户" + customer2);
                banlance = animalSpecie.getPrice() + banlance + animalSpecie.getProfit();
                profit = animalSpecie.getProfit() + profit;
            }
        } catch (NotOpenException e) {
            System.out.println("请明日再来");
        } catch (AnimalNotFoundException e) {
            System.out.println("请换一个宠物购买吧");
        }


    }


    @Override
    public void close() {
        LocalDateTime localDateTime = LocalDateTime.now();
        if (localDateTime.getHour() >= 21 || localDateTime.getHour() <= 6) {
            System.out.println("歇业中");
            //输出利润
            System.out.println("利润为" + profit);
            //遍历输出顾客列表
            for (int i = 0; i < customerArrayList.size(); i++) {
                Customer customeri = customerArrayList.get(i);
                //默认调用toString方法
                System.out.println(customeri);
            }
            //遍历输出顾客列表
            for (int i = 0; i < animalArrayList.size(); i++) {
                Animal animal = animalArrayList.get(i);
                //默认调用toString方法
                System.out.println(animal);
            }
        } else {
            System.out.println("营业中");
        }
    }


}

