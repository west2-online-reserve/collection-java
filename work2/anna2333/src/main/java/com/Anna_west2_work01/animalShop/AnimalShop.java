package com.Anna_west2_work01.animalShop;

import com.Anna_west2_work01.animalShop.customer.Customer;
import com.Anna_west2_work01.animalShop.exception.InsufficientBalanceException;

public interface AnimalShop {
    void buyAnimal(Animal animal) throws InsufficientBalanceException;

    void serveCustomer(Customer customer, Animal animal) throws InsufficientBalanceException;

    void close();

    void open();
}
