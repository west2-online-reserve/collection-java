package com.petstore;

public interface AnimalShop {
    void buyAnimal(String name,int age,String gender,boolean isVaccineInjected);
    void serveCustomer(Customer customer, String animalName);
    void closeShop();
}
