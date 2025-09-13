package com.lovenndme.petstore.animalShop;

import com.lovenndme.petstore.animal.Animal;
import com.lovenndme.petstore.customer.Customer;

public interface AnimalShop {
    void buyNewAnimal(Animal animal);
    void serveCustomer(Customer customer);
    void closeShop();
}
