package com.animals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop {
    private double balance;
    private double profit;
    private boolean isClosed;
    private ArrayList<Animals> animalArrayList = new ArrayList<>();
    private ArrayList<Customer> customerArrayList = new ArrayList<>();

    public MyAnimalShop() {
    }

    public MyAnimalShop(double balance, boolean isClosed) {
        this.balance = balance;

        this.isClosed = isClosed;

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean closed) {
        isClosed = closed;
    }

    public ArrayList<Animals> getAnimalArrayList() {
        return animalArrayList;
    }

    public void setAnimalArrayList(ArrayList<Animals> animalArrayList) {
        this.animalArrayList = animalArrayList;
    }

    public ArrayList<Customer> getCustomerArrayList() {
        return customerArrayList;
    }

    public void setCustomerArrayList(ArrayList<Customer> customerArrayList) {
        this.customerArrayList = customerArrayList;
    }

    public void buy(Animals animal) {
        if (animal.getPrice() > this.balance) {
            throw new InsufficientBalanceException("余额不足！");
        } else {
            animalArrayList.add(animal);
            this.profit = this.profit - animal.getPrice();
            this.balance = this.balance - animal.getPrice();
            System.out.println("进货成功！");
        }
    }

    public void sell(Animals animal) {
        if (!animalArrayList.contains(animal)) {
            animalArrayList.remove(animal);
            this.profit = this.profit + animal.getPrice();
            this.balance = this.balance + animal.getPrice();
            System.out.println("卖出成功！");
        }
    }


    public void close() {
        if (!getIsClosed()) {
            this.isClosed = true;
            System.out.println("商店已关闭！");
            //setClosed(true);
        }
        for (int i = 0; i < customerArrayList.size(); i++) {
            if (customerArrayList.get(i).getTime().isEqual(LocalDate.now())) {
                System.out.println("今日顾客：" + customerArrayList.get(i).toString());
            }
        }
        System.out.println("今日利润：" + this.profit);
        System.out.println("余额：" + this.balance);
    }

    public void reopen() {
        if (getIsClosed()) {
            this.isClosed = false;
        }
        this.profit = 0.0;
        System.out.println("今天又是美好的一天~");
    }


    public void reception(Customer customer) {
        if (!getIsClosed()) {

            if (customerArrayList.contains(customer)) {
                System.out.println("欢迎再次光临！");
            } else {
                System.out.println("欢迎光临！");
            }
            customer.setTimes(customer.getTimes() + 1);
            customer.setTime(LocalDate.now());
            System.out.println("我们这里有：");
            for (Animals animal1 : animalArrayList) {
                System.out.println(animal1);
            }

            System.out.println(customer.getCustomername() + "先生（女士），请问您想要哪只宠物？");
            Scanner scanner1 = new Scanner(System.in);
            String animal = scanner1.nextLine();

            int a = 1;
            for (int i = 0; i < animalArrayList.size(); i++) {
                if (animalArrayList.get(i).getName().equals(animal)) {
                    a = 0;
                    System.out.println("好的，谢谢惠顾！");
                    animalArrayList.remove(i);
                    this.profit = this.profit + animalArrayList.get(i).getPrice();
                    this.balance = this.balance + animalArrayList.get(i).getPrice();
                }
            }
            if (a == 1) {
                throw new AnimalNotFountException("店内没有该动物。");
            }

        } else {
            System.out.println("店铺已经歇业。");
        }

    }
}

