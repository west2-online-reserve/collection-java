package com.github.lpx.service;

import com.github.lpx.exception.AnimalNotFountException;
import com.github.lpx.exception.InsufficientBalanceException;
import com.github.lpx.model.Supplier;
import com.github.lpx.model.customer.Customer;
import com.github.lpx.model.pet.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyPetStore implements PetStore {
    private int customerNum;
    private Supplier supplier;
    private double balance;
    private boolean isOpen;
    private List<Animal> animals = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public MyPetStore(double balance, Supplier supplier) {
        this.supplier = supplier;
        this.balance = balance;
        customerNum = 0;
        isOpen = true;
    }

    @Override
    public void serviceToCustomer() throws AnimalNotFountException {
        System.out.println("请输入id(无id则输入0或负数)");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        System.out.println("请输入名字");
        String name = sc.next();
        if (isOpen) {
            Customer customer;
            if (id > customerNum) {
                System.out.println("id输入错误");
            } else if (id <= 0) {
                customer = new Customer(name, ++customerNum);
                customers.add(customer);
                showAllPet();
                System.out.println("请输入你想买的宠物姓名");
                String petName = sc.next();
                double discount = customer.getCustomerLevel().getDiscount();
                customer.setNumOfVisits(customer.getNumOfVisits() + 1);
                customer.setLastVisit();
                sellPet(petName, discount);
            } else {
                for (Customer c : customers) {
                    if (c.getId() == id && c.getName().equals(name)) {
                        customer = c;
                        showAllPet();
                        System.out.println("请输入你想买的宠物姓名");
                        String petName = sc.next();
                        double discount = customer.getCustomerLevel().getDiscount();
                        customer.setNumOfVisits(customer.getNumOfVisits() + 1);
                        customer.setLastVisit();
                        sellPet(petName, discount);
                        return;
                    }
                }
                System.out.println("id与名字不匹配");
            }
        } else {
            System.out.println("抱歉，我们现在暂停营业了");
        }
    }

    @Override
    public void showAllPet() throws AnimalNotFountException {
        if (animals.size() == 0) {
            throw new AnimalNotFountException("本店没宠物了");
        }
        System.out.println("以下是所有宠物：");
        for (Animal a : animals) {
            System.out.println(a);
            a.petShow();
        }
    }

    @Override
    public void sellPet(String name, double discount) throws AnimalNotFountException {
        for (Animal a : animals) {
            if (a.getName().equals(name)) {
                balance += a.getPrice() * discount;
                System.out.println("您已购买宠物" + name + ";原价：" + a.getPrice() + ";您的折扣价：" + discount * a.getPrice());
                animals.remove(a);
                return;
            }
        }
        throw new AnimalNotFountException("本店没有叫作" + name + "的宠物");
    }

    @Override
    public void openStore() {
        setOpen(true);
    }

    @Override
    public void closeStore() {
        LocalDate today = LocalDate.now();
        System.out.println("今天顾客：");
        for (Customer c : customers) {
            if (c.getLastVisit().equals(today)) {
                System.out.print(c.getName() + ";");
            }
        }
        System.out.println();
    }

    @Override
    public void addBalance(double money) {
        setBalance(getBalance() + money);
    }

    @Override
    public void removeBalance(double money) throws InsufficientBalanceException {
        if (getBalance() - money < 0) {
            throw new InsufficientBalanceException("账户钱不够取出");
        } else setBalance(getBalance() - money);
    }

    @Override
    public boolean buyPet() throws RuntimeException {//回头到宠物那把购入价格加一下
        System.out.println("输入数字1~5购入：1：狗狗 2：猫猫 3：鸟 4：龙 5：蜥蜴");
        Scanner sc = new Scanner(System.in);
        int key = sc.nextInt();
        double needPrice;
        Animal animal;
        switch (key) {
            case 1:
                needPrice = supplier.getDogPrice();
                if (checkBalance(needPrice)) {
                    animals.add(createAnimal(1));
                } else {
                    throw new InsufficientBalanceException("账户钱不够了");
                }
                break;
            case 2:
                needPrice = supplier.getCatPrice();
                if (checkBalance(needPrice)) {
                    animals.add(createAnimal(2));
                } else {
                    throw new InsufficientBalanceException("账户钱不够了");
                }
                break;
            case 3:
                needPrice = supplier.getBirdPrice();
                if (checkBalance(needPrice)) {
                    animals.add(createAnimal(3));
                } else {
                    throw new InsufficientBalanceException("账户钱不够了");
                }
                break;
            case 4:
                needPrice = supplier.getDragonPrice();
                if (checkBalance(needPrice)) {
                    animals.add(createAnimal(4));
                } else {
                    throw new InsufficientBalanceException("账户钱不够了");
                }
                break;
            case 5:
                needPrice = supplier.getLizardPrice();
                if (checkBalance(needPrice)) {
                    animals.add(createAnimal(5));
                } else {
                    throw new InsufficientBalanceException("账户钱不够了");
                }
                break;
            default:
                System.out.println("输入不符合");
        }

        return true;
    }

    private boolean checkBalance(double needPrice) {
        if (needPrice <= balance) {
            System.out.println("购入价格为" + needPrice + "元");
            balance -= needPrice;
            return true;
        }
        return false;
    }

    private Animal createAnimal(int k) {
        String name;
        int age;
        String sex;
        double price;
        String color;
        boolean isVaccineInjected;
        Animal animal;
        Scanner sc = new Scanner(System.in);
        System.out.println("请为宠物取名：");
        name = sc.next();
        System.out.println("宠物年龄(整数)：");
        age = sc.nextInt();
        System.out.println("宠物性别：");
        sex = sc.next();
        System.out.println("宠物价格：");
        price = sc.nextDouble();
        System.out.println("宠物颜色：");
        color = sc.next();
        if (k == 1) {
            System.out.println("狗子是否打过疫苗,输入1表示打过，输入其他均表示没打过");
            String str;
            str = sc.next();
            if (str.equals("1")) isVaccineInjected = true;
            else isVaccineInjected = false;
            animal = new Dog(name, age, sex, price, color, isVaccineInjected);
        } else if (k == 2) {
            animal = new Cat(name, age, sex, price, color);
        } else if (k == 3) {
            animal = new Bird(name, age, sex, price, color);
        } else if (k == 4) {
            animal = new Dragon(name, age, sex, price, color);
        } else {
            animal = new Lizard(name, age, sex, price, color);
        }
        return animal;
    }

    public int getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(int customerNum) {
        this.customerNum = customerNum;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
