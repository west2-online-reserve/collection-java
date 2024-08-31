package com.lovenndme.petstore.animalShop;

import com.lovenndme.petstore.animal.Animal;
import com.lovenndme.petstore.animal.Bird;
import com.lovenndme.petstore.animal.Cat;
import com.lovenndme.petstore.animal.Dog;
import com.lovenndme.petstore.customer.Customer;
import com.lovenndme.petstore.exception.AnimalNotFoundException;
import com.lovenndme.petstore.exception.InsufficientBalanceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {

    private double balance;
    private List<Animal> list;
    private List<Customer> customers;
    private boolean isOpen;

    public MyAnimalShop(double balance, List<Animal> initialAnimalList, List<Customer> initialCustomerList) {
        this.balance = balance;
        this.list = new ArrayList<>(initialAnimalList);
        this.customers = new ArrayList<>(initialCustomerList);
        this.isOpen = true;
    }

    @Override
    public void buyNewAnimal(Animal animal) {
        if (!isOpen) {
            throw new IllegalStateException("The shop is not open now!");
        }
        if (balance < animal.getPrice()) {
            throw new InsufficientBalanceException("Insufficient balance to buy new animal.");
        }
        balance -= animal.getPrice();
        list.add(animal);
        System.out.println("Animal bought: " + animal.toString());
    }

    @Override
    public void serveCustomer(Customer customer) {
        if (!isOpen) {
            throw new IllegalStateException("The shop is not open now!");
        }
        customers.add(customer);
        if (list.isEmpty()) {
            throw new AnimalNotFoundException("There are no animals in the shop to sell.");
        }

        Animal desiredAnimal = findDesiredAnimal(customer);
        if (desiredAnimal == null) {
            throw new AnimalNotFoundException("No animal of the desired type available in the shop.");
        }

        list.remove(desiredAnimal);
        balance += desiredAnimal.getPrice();
        customer.increaseFrequency();
        System.out.println("Animal sold: " + desiredAnimal.toString());
    }

    public Animal findDesiredAnimal(Customer customer) {
        for (Animal animal : list) {
            if (animal instanceof Dog) {
                return animal;
            }
            if (animal instanceof Cat) {
                return animal;
            }
            if (animal instanceof Bird) {
                return animal;
            }
        }
        return null;
    }

    @Override
    public void closeShop() {
        isOpen = false;
        LocalDate today = LocalDate.now();

        double profit = 0;
        for (Customer customer : customers) {
            if (customer.getTime().isEqual(today)) {
                System.out.println(customer);
            }
        }
        profit = balance - 500;
        System.out.println("Today's profit is: " + profit);
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
