package com.west2online.petshop;

public interface AnimalShop {
    void buyNewAnimals(Animal animal);

    void serveCustomer(Customer customer, String petName);

    void closeShop();
}
