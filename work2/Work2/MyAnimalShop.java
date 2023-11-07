package com.WestTwo.work2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class MyAnimalShop implements AnimalShop {
    private double money1; // 记录当天初始余额
    private double money; // 记录当天最终余额
    private ArrayList AnimalList = new ArrayList<>();
    private ArrayList CustomerList = new ArrayList<>();
    private ArrayList DateList = new ArrayList<>();

    public boolean work = true;

    @Override
    public void Buy(Animal animal) {
        if (money < animal.getPrice()) {
            throw new InsufficientBalanceException("Money is not enough!");
        } else {
            this.AnimalList.add(animal);
            this.money -= animal.getPrice();
        }
    }

    @Override
    public void Serve(Customer customer, Animal animal) {
        int i1 = 0;
        while (i1 < AnimalList.size()) {
            if (Objects.equals(animal, AnimalList.get(i1))) {
                break;
            }
            i1++;
        }
        if (AnimalList.size() == 0) {
            throw new AnimalNotFoundException("All the animals have been sold out!");
        } else if (i1 == AnimalList.size()) {
            throw new AnimalNotFoundException("We cannot find this animal in our shop!");
        } else {
            if (CustomerList.size() == 0) {
                this.CustomerList.add(customer);
                this.DateList.add(customer.getDate());
            }
            for (int i = 0; i < CustomerList.size(); i++) {
                if (Objects.equals(customer, CustomerList.get(i))) {
                    customer.setTimes(customer.getTimes()+1);
                    this.DateList.set(i, customer.getDate());
                } else {
                    this.CustomerList.add(customer);
                    this.DateList.add(customer.getDate());
                }
            }
            AnimalList.remove(i1);
            this.money += animal.getPrice();
            System.out.println(animal);
        }
    }

    @Override
    public void Off(LocalDate date) {
        ArrayList arrayList = new ArrayList<>();
        for (int i = 0; i < DateList.size(); i++) {
            if (Objects.equals(date, DateList.get(i))) {
                arrayList.add(CustomerList.get(i));
            }
        }
        System.out.println(arrayList);
        System.out.println(money - money1);
        this.money1 = this.money;
        this.work = false;
    }
    
    public double getMoney1() {
        return money1;
    }

    public void setMoney1(double money1) {
        this.money1 = money1;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public ArrayList getAnimalList() {
        return AnimalList;
    }

    public void setAnimalList(ArrayList animalList) {
        AnimalList = animalList;
    }

    public ArrayList getCustomerList() {
        return CustomerList;
    }

    public void setCustomerList(ArrayList customerList) {
        CustomerList = customerList;
    }

    public ArrayList getDateList() {
        return DateList;
    }

    public void setDateList(ArrayList dateList) {
        DateList = dateList;
    }

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }
}
