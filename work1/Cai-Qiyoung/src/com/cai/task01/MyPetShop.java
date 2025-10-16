package com.cai.task01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random ;

public class MyPetShop implements PetShopMethods {

    private String name;
    private List<Animal> animals;
    private List<Customer> customers;
    private double balance;
    private double todayProfit;
    private boolean isRunning = true;
    private Random random = new Random(); // 随机数生成器，用于抽取动物

    public MyPetShop(String name , double initialBalance) {
        this.name = name;
        this.animals = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.balance = initialBalance;
        this.todayProfit = 0;
    }

    @Override
    public void buyAnimal(Animal animal, double cost, double sellPrice) throws InsufficientBalanceException {
        if (cost > balance) {
            throw new InsufficientBalanceException(String.format(
                    "余额不足，当前余额: %.2f, 需要: %.2f", balance, cost));
        }
        animals.add(animal);
        balance -= cost;
        animal.setPrice(sellPrice);
        System.out.printf("成功买入: %s, 花费: %.2f, 剩余余额: %.2f%n", animal, cost, balance);
    }

    // 随机抽取动物的方法
    private Animal randomSelectAnimal() {
        if (animals.isEmpty()) {
            return null;
        }
        // 生成0到动物数量之间的随机索引
        int randomIndex = random.nextInt(animals.size());
        return animals.get(randomIndex);
    }

    @Override
    public void serveCustomer(Customer customer, Animal animal) {
        // 检查动物是否在店内
        if (!animals.contains(animal)) {
            throw new AnimalNotFoundException("店内没有指定的动物: " + animal);
        }

        // 添加客户到顾客列表
        customers.add(customer);
        customer.setLastVisitTime(LocalDate.now());
        customer.setVisitCount(customer.getVisitCount() + 1);

        //顾客逗小猫小狗
        System.out.println("==========================================================");
        System.out.println("当前顾客正在和小动物愉快地玩耍～～～");
        Animal selectedAnimal = randomSelectAnimal();
        selectedAnimal.eat();
        selectedAnimal.makeSound();

        // 出售动物
        animals.remove(animal);
        double price = animal.getPrice();
        balance += price;
        todayProfit += price;

        // 输出动物信息
        System.out.println("出售动物信息: " + animal);
        System.out.printf("客户 %s 购买成功，收入: %.2f, 当前余额: %.2f%n", customer.getName(), price, balance);
        System.out.println("==========================================================");
    }

    @Override
    public void closeShop(LocalDate date) {
        isRunning = false;
        System.out.println("\n===== " + date + " 宠物店歇业 =====");
        System.out.printf("今日利润: %.2f%n", todayProfit);
        System.out.println("今日光顾的客户列表:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        System.out.println("==========================");
    }

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public List<Customer> getCustomers() {
        return customers;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getTodayProfit() {
        return todayProfit;
    }
}

