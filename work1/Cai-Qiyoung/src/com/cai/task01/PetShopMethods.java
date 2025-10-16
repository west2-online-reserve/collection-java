package com.cai.task01;

import java.time.LocalDate;
import java.util.List;

public interface PetShopMethods {
    //买入动物
    void buyAnimal(Animal animal, double cost, double sellPrice) throws InsufficientBalanceException;

    // 招待客户
    void serveCustomer(Customer customer, Animal animal) throws AnimalNotFoundException;

    // 歇业
    void closeShop(LocalDate date);

    // 获取当前动物列表
    List<Animal> getAnimals();

    //
    List<Customer> getCustomers();

    //获取当前余额
    double getBalance();

    //获取当前盈利
    double getTodayProfit();
}
