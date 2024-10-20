package com.catowl.animalshop;

import java.time.LocalDate;

public interface AnimalShop {
    void buyAnimal(Animal animal);

    void entertainCustomer(Customer customer, int index);

    void shutdown(LocalDate today, double profit);
}
