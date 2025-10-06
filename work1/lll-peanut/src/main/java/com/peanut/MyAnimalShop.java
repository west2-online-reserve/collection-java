package com.peanut;

import com.peanut.constant.ANIMALSHOP;
import com.peanut.Exception.AnimalNotFoundException;
import com.peanut.Exception.InsufficientBalanceException;
import com.peanut.constant.PRICE;

import java.time.LocalDate;
import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {

    private double balance;

    private double beforeBalance;

    private ArrayList<Animal> animals = new ArrayList<Animal>();

    private ArrayList<Customer> customers = new ArrayList<Customer>();

    boolean isOpen;

    public MyAnimalShop() {
        balance = 1000;
        isOpen = true;
    }

    @Override
    public void close() {
        System.out.println(ANIMALSHOP.DIVIDER);
        if (isOpen) {
            System.out.println(ANIMALSHOP.CLOSE);
            for (Customer customer : customers) {
                LocalDate lastTime = customer.getLastTime();
                if (lastTime.equals(LocalDate.now())) {
                    System.out.println(customer);
                }
            }
            System.out.println(ANIMALSHOP.PROFIT + (balance - beforeBalance));
            beforeBalance = balance;
            isOpen = false;
        } else {
            System.out.println(ANIMALSHOP.ALREADY_CLOSE);
        }
        System.out.println(ANIMALSHOP.DIVIDER);
    }

    @Override
    public void entertainCustomer(Customer customer) {
        System.out.println(ANIMALSHOP.DIVIDER);
        customer.addVisitNum();
        customer.setLastTime(LocalDate.now());
        if (isOpen) {
            if (!customers.contains(customer)) {
                customers.add(customer);
            }

            if (animals.size() == 0) {
                throw new AnimalNotFoundException(ANIMALSHOP.ANIMAL_NOT_FOUND);
            } else {
                System.out.println(ANIMALSHOP.SOUD_ANIMAL);
                for (Animal animal : animals) {
                    balance += animal.getPrice() + PRICE.PROFIT_PRICE;
                    System.out.println(animal);
                }
                animals.clear();
            }
        } else {
            System.out.println(ANIMALSHOP.ALREADY_CLOSE);
        }
        System.out.println(ANIMALSHOP.DIVIDER);
    }

    @Override
    public void buyAnimal(Animal animal) {
        double price = animal.getPrice();
        if (animals.contains(animal)) {
            System.out.println(animal.name + ANIMALSHOP.ANIMAL_EXIST);
            return;
        }
        if (price > balance) {
            throw new InsufficientBalanceException(animal.name + ANIMALSHOP.INSUFFICIENT_BALANCE);
        } else {
            animals.add(animal);
            balance -= price;
        }
    }

    @Override
    public void recharge() {
        System.out.println(ANIMALSHOP.RECHARGE);
        balance += 1000;
    }

    @Override
    public void open() {
        this.isOpen = true;
    }
}
