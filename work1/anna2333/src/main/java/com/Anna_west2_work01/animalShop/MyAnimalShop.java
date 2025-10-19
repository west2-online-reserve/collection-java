package com.Anna_west2_work01.animalShop;

import com.Anna_west2_work01.animalShop.customer.Customer;
import com.Anna_west2_work01.animalShop.exception.AnimalNotFoundException;
import com.Anna_west2_work01.animalShop.exception.InsufficientBalanceException;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {
    protected double balance;
    protected ArrayList<Animal> AnimalList = new ArrayList<>();
    protected ArrayList<Customer> CustomerList = new ArrayList<>();
    protected boolean isOpen;
    protected double todayNetProfit;

    public MyAnimalShop() {}

    public MyAnimalShop(double balance, ArrayList<Animal> animalList, ArrayList<Customer> customerList, boolean isOpen, double todayNetProfit) {
        this.balance = balance;
        AnimalList = animalList;
        CustomerList = customerList;
        this.isOpen = isOpen;
        this.todayNetProfit = todayNetProfit;
    }

    public double getBalance() {
        return balance;
    }

    public void buyAnimal(Animal animal) {
        if(this.balance < animal.inPrice) {
            throw new InsufficientBalanceException("余额不足以购买哦");
        } else {
            System.out.println("恭喜，已成功购买该宠物！");
            System.out.println("该宠物的具体信息如下：");
            System.out.println(animal.toString());
            AnimalList.add(animal);
            balance -= animal.inPrice;
            todayNetProfit -= animal.inPrice;
        }
    }

    public void serveCustomer(Customer customer, Animal animal) {
        if(AnimalList.isEmpty()) {
            throw new AnimalNotFoundException("");
        }
        if(!isOpen) {
            System.out.println("抱歉哦，宠物店今日已打烊，请明日再光临！");
        }
        CustomerList.add(customer);
        System.out.println("恭喜，已成功售出该宠物！");
        System.out.println("该宠物的具体信息如下：");
        System.out.println(animal.toString());
        AnimalList.remove(animal);
        todayNetProfit += animal.outPrice;
        balance += animal.outPrice;
    }

    public void open() {
        this.isOpen = true;
        System.out.println("开始今天的营业啦！~");
        this.todayNetProfit = 0;
    }

    public void close() {
        LocalDateTime time = LocalDateTime.now();

        if(time.getHour() < 9 || time.getHour() > 20) {
            this.isOpen = false;
            System.out.println("结束今天的营业！~");
            System.out.println("今天宠物店一共接待了" + CustomerList.size() + "位顾客哦");
            System.out.println("以下为今日接待所有顾客的信息：");
            for (int i = 0; i < CustomerList.size(); i++) {
                System.out.println("第" + (1 + i) + "位顾客的信息为：  " + CustomerList.get(i));
            }
            System.out.println("今天宠物商店营业的净利润为：" + todayNetProfit + "元！");
            System.out.println("宠物商店的总余额为" + balance + "元");
            System.out.println("辛苦啦，明天见！");
        } else {
            System.out.println("还没到歇业时间哦，请继续努力~");
        }
    }
}

