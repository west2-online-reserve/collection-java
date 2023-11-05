package com.west2.work2;

public interface AnimalShop {
    void buyNewAnimal(Animal animal) throws InsufficientBalanceException;

    void treatCustomer(Customer customer);

    void outOfBusiness();
}
