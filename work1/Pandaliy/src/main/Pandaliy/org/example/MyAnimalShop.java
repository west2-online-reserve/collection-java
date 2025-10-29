package org.example;

import org.example.Exception.AnimalNotFountException;
import org.example.Exception.InsufficientBalanceExceptio;
import org.example.Exception.NoAnimalsMeetException;
import org.example.Exception.ShopClosedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private ArrayList<Animal> animallist;
    private ArrayList<Customer> customerlist;
    private boolean isOpenForBusiness;
    private double dailyProfit;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.animallist = new ArrayList<>(10);
        this.customerlist = new ArrayList<>(10);
        this.isOpenForBusiness = true;
        this.dailyProfit = 0.0;
    }


    @Override
    public void buyNewAnimals(Animal animal) throws InsufficientBalanceExceptio, ShopClosedException {
        if (!isOpenForBusiness) {
            throw new ShopClosedException();
        }
        if (balance + dailyProfit < animal.getPrice()) {
            throw new InsufficientBalanceExceptio();
        }
        dailyProfit -= animal.getPrice();
        animallist.add(animal);
    }

    @Override
    public void entertainCustomers(String customer, Class<? extends Animal> targetAnimalType, double offer) throws AnimalNotFountException {
        if (!isOpenForBusiness) {
            throw new ShopClosedException();
        }
        if (animallist.isEmpty()) {
            throw new AnimalNotFountException();
        }
        boolean isOldCustomer = false;
        for (Customer c : customerlist) {
            if (Objects.equals(c.getName(), customer)) {
                c.addVisitTimes();
                c.setLatestTime(LocalDate.now());
                isOldCustomer = true;
                break;
            }
        }
        if (!isOldCustomer) {
            Customer newcustomer = new Customer(customer);
            customerlist.add(newcustomer);
            newcustomer.addVisitTimes();
            newcustomer.setLatestTime(LocalDate.now());
        }
        boolean isAnimalFound = false;
        for (int i = 0; i < animallist.size(); i++) {
            Animal currentAnimal = animallist.get(i);
            if (targetAnimalType.isInstance(currentAnimal)) {
                System.out.println(currentAnimal);
                dailyProfit += offer;
                animallist.remove(i--);
                isAnimalFound = true;
                break;
            }
        }
        if (!isAnimalFound) {
            throw new NoAnimalsMeetException();
        }
    }

    @Override
    public void closeDown() {
        if (isOpenForBusiness) {
            ArrayList<Customer> todayCustomers = new ArrayList<>();
            for (Customer c : customerlist) {
                if (Objects.equals(c.getLatestTime(), LocalDate.now()))
                    System.out.println(c);
            }
            System.out.println("今日总营收为:" + dailyProfit + "\n");
            balance += dailyProfit;
            isOpenForBusiness = false;
        } else {
            throw new ShopClosedException();
        }
    }

    public double getBalance() {
        return balance + dailyProfit;
    }

    public ArrayList<Customer> getCustomerlist() {
        return customerlist;
    }

    public ArrayList<Animal> getAnimallist() {
        return animallist;
    }
}
