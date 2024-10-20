package com.catowl.animalshop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.zip.CheckedOutputStream;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private ArrayList<Animal> animalsList;
    private ArrayList<Customer> customersList;
    private boolean status;

    public MyAnimalShop(double balance, ArrayList<Animal> animalsList, ArrayList<Customer> customersList, boolean status) {
        this.balance = balance;
        this.animalsList = animalsList;
        this.customersList = customersList;
        this.status = status;
    }

    public MyAnimalShop() {
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        try {
            if (animal.getPURCHASE_PRICE() > this.balance) {
                throw new InsufficientBalanceException("InsufficientBalanceExcption");
            }
        } catch (InsufficientBalanceException e) {
            System.out.println("InsufficientBalanceException");
            System.out.println("购买失败");
            return;
        }
        animalsList.add(animal);
        balance -= animal.getPURCHASE_PRICE();

    }

    @Override
    public void entertainCustomer(Customer customer, int index) {
        try {
            if (index < 0 || index >= animalsList.size()) {
                throw new AnimalNotFoundException("AnimalNotFoundException");
            }
        } catch (AnimalNotFoundException e) {
            System.out.println("AnimalNotFoundException");
            System.out.println("店内无该动物");
            return;
        }
        //判断新老顾客
        boolean flag = true;//假设为新顾客
        for (int i = 0; i < customersList.size(); i++) {
            if (customersList.get(i).getName().equals(customer.getName())) {
                customersList.get(i).setNumberOfVisits();
                customersList.get(i).setLatestArrivalTime(customer.getLatestArrivalTime());
                flag = false;//老顾客
                break;
            }
        }
        if (flag) {//新顾客
            customersList.add(customer);
        }
        //找出顾客要购买的动物
        for (int i = 0; i < animalsList.size(); i++) {
            if (animalsList.get(i) == animalsList.get(index)) {
                System.out.println("售出动物：");
                System.out.println(animalsList.get(i).toString());
                System.out.println();
                balance += animalsList.get(index).getPrice();
                animalsList.remove(i);
                break;
            }
        }
    }

    @Override
    public void shutdown(LocalDate today, double profit) {
        System.out.println(today + "的顾客列表：");
        for (int i = 0; i < customersList.size(); i++) {
            if (customersList.get(i).getLatestArrivalTime() == today) {
                System.out.println(customersList.get(i).toString());
            }
        }
        System.out.println("当日余额：" + balance);
        System.out.println("当日盈利：" + profit);
        System.out.println();
        status = false;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Animal> getAnimalsList() {
        return animalsList;
    }

    public ArrayList<Customer> getCustomersList() {
        return customersList;
    }

    public boolean isStatus() {
        return status;
    }
}
