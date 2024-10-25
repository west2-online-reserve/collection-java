package com.YJH.west2.q.chongwudianself;

public interface AnimalShop {
    void buyAnimal(Animal animal) throws InsufficientBalanceException;

    void greetCustomer(Customer customer, Animal animal) throws AnimalNotFoundException, IllegalStateException;

    void close();

    void open();

}
