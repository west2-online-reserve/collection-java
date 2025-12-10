package com.petshop.entity;

import com.petshop.exception.AnimalNotFoundException;
import com.petshop.exception.InsufficientBalanceException;

import java.time.LocalDate;
import java.util.List;

class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animals;
    private List<Customer> customers;
    private boolean isOpen;
    private double dailyProfit;

    public MyAnimalShop(double balance, List<Animal> animals, List<Customer> customers, boolean isOpen) {
        this.balance = balance;
        this.animals = animals;
        this.customers = customers;
        this.isOpen = isOpen;
        dailyProfit = 0;
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

    public void openShop() {
        isOpen = true;
        System.out.println("宠物店营业");
    }

    @Override
    public void buyAnimal(Animal animal) {
        if (!isOpen) {
            System.out.println("宠物店已打烊，无法购入宠物");
            return;
        }
        if (balance < animal.getPrice()) {
            throw new InsufficientBalanceException("宠物店余额不足，无法购入宠物");
        }
        dailyProfit -= animal.getPrice();
        balance -= animal.getPrice();
        animals.add(animal);
        System.out.println("宠物店购入" + animal.toString());
    }

    @Override
    public void serveCustomer(String customerName, String type) {
        if (!isOpen) {
            System.out.println("宠物店已打烊,顾客无法购买宠物");
            return;
        }
        boolean isNewCustomer = true;
        for (Customer c : customers) {
            if (c.getName().equals(customerName)) {
                c.setFrequency(c.getFrequency()+1);
                c.setLatestArrivalTime(LocalDate.now());
                isNewCustomer = false;
                break;
            }
        }
        if (isNewCustomer){
            customers.add(new Customer(customerName, 1, LocalDate.now()));
        }
        if (animals.isEmpty()) {
            throw new AnimalNotFoundException("宠物店无宠物可售");
        }
        for (int i = 0; i<animals.size(); i++) {
            Animal animal = animals.get(i);
            if (animal.getType().equals(type)) {
                dailyProfit += animal.getPrice();
                balance += animal.getPrice();
                System.out.println(customerName + "购买" + animal.toString());
                animals.remove(i);
                return;
            }
        }
        System.out.println("宠物店无" + type +"可售");
    }

    @Override
    public void closeShop() {
        System.out.println("宠物店打烊");
        if (!isOpen) {
            return;
        }
        isOpen = false;
        if (!customers.isEmpty()) {
            System.out.println("今日到店顾客：");
            if(!customers.isEmpty()){
                for (Customer c : customers) {
                    System.out.println(c.toString());
                }
            }
        }
        customers.clear();
        System.out.println("宠物店今日收入：" + dailyProfit);
        dailyProfit = 0;
        System.out.println("**************************");
    }
}