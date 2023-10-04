package com.huayu.work02;

import java.time.LocalDate;

public interface AnimalShop {
    void purchaseNewAnimal(Animal animal, LocalDate importTime);

    void hospitalizingCustomers(Customer customer, int whichOneToPurchase, Animal animal);

    void closureOfBusiness(Customer... customer);
}
