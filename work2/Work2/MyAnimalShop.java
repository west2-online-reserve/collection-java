package com.WestTwo.work2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class MyAnimalShop implements AnimalShop {
    double money1; // 记录当天初始余额
    double money; // 记录当天最终余额
    ArrayList AnimalList = new ArrayList<>();
    ArrayList CustomerList = new ArrayList<>();
    ArrayList DateList = new ArrayList<>();

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
                this.DateList.add(customer.date);
            }
            for (int i = 0; i < CustomerList.size(); i++) {
                if (Objects.equals(customer, CustomerList.get(i))) {
                    customer.times++;
                    this.DateList.set(i, customer.date);
                } else {
                    this.CustomerList.add(customer);
                    this.DateList.add(customer.date);
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
}
