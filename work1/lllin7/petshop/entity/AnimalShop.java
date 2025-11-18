package com.petshop.entity;

interface AnimalShop {
    void buyAnimal(Animal animal);
    void serveCustomer(String customerName, String type);
    void closeShop();
}