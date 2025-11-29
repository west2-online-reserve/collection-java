package org.example;

import org.example.Exception.AnimalNotFountException;
import org.example.Exception.InsufficientBalanceExceptio;
import org.example.Exception.ShopClosedException;

public interface AnimalShop {
    void buyNewAnimals(Animal animal) throws InsufficientBalanceExceptio, ShopClosedException;

    void entertainCustomers(String customer, Class<? extends Animal> targetAnimalType, double offer) throws AnimalNotFountException;

    void closeDown();
}
