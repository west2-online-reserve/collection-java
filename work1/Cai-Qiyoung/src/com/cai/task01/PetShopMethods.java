package com.cai.task01;

import java.time.LocalDate;
import java.util.List;

public interface PetShopMethods {
    //买入动物
    void buyAnimal(List<Animal> animalCanBuyList , Animal animal, double cost, double sellPrice) throws InsufficientBalanceException;

    // 招待客户
    void serveCustomer(Customer customer, Animal animal , LocalDate date) throws AnimalNotFoundException;

    // 歇业
    void closeShop(LocalDate date);

    // 获取当前动物列表
    List<Animal> getAnimals();

    //获取所有顾客列表
    List<Customer> getAllCustomers();

    //获取今日顾客列表
    List<Customer> getTodayCustomers();

    //获取当前余额
    double getBalance();

    //获取当前营业额
    double getTodayProfit();

    //获取当前营业状态
    boolean getIsRunning();

    //获取宠物店名
    String getName();
}
