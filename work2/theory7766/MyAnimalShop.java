package com.west2.work2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {

    private double balance;//余额
    private ArrayList<Animal> pets;//存放宠物
    private ArrayList<Customer> customers;//顾客
    private boolean isBusiness;//正在营业

    public MyAnimalShop(double balance, ArrayList<Animal> pets, ArrayList<Customer> customers, boolean isBusiness) {
        this.balance = balance;
        this.pets = pets;
        this.customers = customers;
        this.isBusiness = isBusiness;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Animal> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Animal> pets) {
        this.pets = pets;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public boolean isBusiness() {
        return isBusiness;
    }

    public void setBusiness(boolean business) {
        isBusiness = business;
    }

    public void showAnimalMenu() {
        System.out.println("***宠物菜单***");
        for (int i = 0; i < pets.size(); i++) {
            System.out.println(i + ":" + pets.get(i).toString());
        }
    }

    @Override
    public void buyNewAnimal(Animal animal) {
        if (animal.getPrice() > this.balance) {
            throw new InsufficientBalanceException(animal, this.balance);
        } else {
            balance -= animal.getPrice();
            pets.add(animal);
            System.out.println("成功购买" + animal.toString());
        }
    }

    @Override
    public void entertainCustomers(Customer c) {
        //将顾客加入顾客列表
        customers.add(c);
        //更新顾客到店次数
        c.setCnt(c.getCnt() + 1);

        Scanner sc = new Scanner(System.in);
        System.out.println("购买宠物输入true，否则输入false：");
        boolean choice = sc.nextBoolean();

        if (choice) {
            if (pets.isEmpty()) {
                throw new AnimalNotFoundException("宠物店没有宠物");
            } else {
                showAnimalMenu();
                System.out.println("请输入您想要购买的宠物下标：");
                int index = sc.nextInt();
                if (index < 0 && index >= pets.size()) {
                    throw new AnimalNotFoundException("下标错误，找不到动物！");
                } else {
                    System.out.println(pets.get(index).toString());
                    //利润每只15
                    balance += pets.get(index).price + 15;
                    pets.remove(index);
                    System.out.println("谢谢惠顾");
                }
            }
        } else {
            System.out.println("希望下次会有您喜欢的宠物！");
        }

    }

    @Override
    public void Shutdown() {
        this.isBusiness = false;
        int profit = 0;
        for (Customer customer : customers) {
            if (LocalDate.now().equals(customer.getTime())) {
                profit += 15;
                System.out.println(customer.toString());
            }
        }
        System.out.println("今日总利润：" + profit);
    }
}