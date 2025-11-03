package com.animalshop.model;

interface AnimalShop{
    void purchaseAnimal(Animal animal);
    void serveCustomer(Customer customer, String animalName);
    void serveCustomer(Customer customer);
    void closeShop();
}