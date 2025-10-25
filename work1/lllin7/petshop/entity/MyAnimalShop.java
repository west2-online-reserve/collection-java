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
        dailyProfit = 0;
    }

    @Override
    public void buyAnimal(Animal animal) {
        if (!isOpen) {
            System.out.println("店铺关门，无法购入宠物");
            return;
        }
        if (balance < animal.getPrice()) {
            throw new InsufficientBalanceException("余额不足，无法买入宠物");
        }
        dailyProfit -= animal.getPrice();
        balance -= animal.getPrice();
        animals.add(animal);
        System.out.println("店铺购买" + animal.toString());
    }

    @Override
    public void serveCustomer(String customerName) {
        if (!isOpen) {
            System.out.println("店铺关门,无法购买宠物");
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
            throw new AnimalNotFoundException("店内无宠物可售");
        }
        Animal animal = animals.remove(animals.size() - 1);
        dailyProfit += animal.getPrice();
        balance += animal.getPrice();
        System.out.println(customerName + "购买" + animal.toString());
    }

    @Override
    public void closeShop() {
        if (!isOpen) {
            System.out.println("店铺已歇业");
            return;
        }
        isOpen = false;
        System.out.println("今日到店：");
        if (!customers.isEmpty()) {
            for (Customer c : customers) {
                if (LocalDate.now().equals(c.getLatestArrivalTime())) {
                    System.out.println(c.toString());
                }
            }
        }
        System.out.println("今日收入：" + dailyProfit);
        System.out.println("**************************");
    }
}