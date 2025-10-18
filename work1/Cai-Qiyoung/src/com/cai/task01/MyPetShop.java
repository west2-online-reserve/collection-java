package com.cai.task01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random ;

public class MyPetShop implements PetShopMethods {

    private String name;
    private List<Animal> animals;
    private List<Customer> allCustomers; // 所有到店的顾客列表
    private List<Customer> todayCustomers; // 今日到带你顾客，每日歇业时清空
    private double balance;
    private double todayProfit;
    private boolean isRunning = true;
    private Random random = new Random(); // 随机数生成器，用于抽取动物

    public MyPetShop(String name , double initialBalance) {
        this.name = name;
        this.animals = new ArrayList<>();
        this.allCustomers = new ArrayList<>();
        this.todayCustomers = new ArrayList<>();
        this.balance = initialBalance;
        this.todayProfit = 0;
    }

    // 判断顾客是否在指定列表中的方法
    private Customer findCustomer(Customer customer, List<Customer> customerList) {
        for (Customer c : customerList) {
            if (c.getID().equals(customer.getID())) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void buyAnimal(List<Animal> animalCanBuyList , Animal animal, double cost, double sellPrice) throws InsufficientBalanceException {
        //检查是否买得起
        if (cost > balance) {
            throw new InsufficientBalanceException(String.format("余额不足，当前余额: %.2f, 需要: %.2f", balance, cost));
        }
        // 检查动物是否在店内
        if (!animalCanBuyList.contains(animal)) {
            throw new AnimalNotFoundException("可买动物列表中没有指定的动物: " + animal.getName());
        }
        animal.cost = cost;
        animalCanBuyList.remove(animal);
        animals.add(animal);
        balance -= cost;
        animal.setPrice(sellPrice);
        System.out.printf("成功买入: %s, 花费: %.2f, 剩余余额: %.2f%n", animal.getName(), animal.getCost(), balance);
    }

    @Override
    public void serveCustomer(Customer customer, Animal animal , LocalDate date) {
        // 检查动物是否在店内
        if (!animals.contains(animal)) {
            throw new AnimalNotFoundException("没有指定的动物: " + animal);
        }

        // 处理今日顾客列表：去重添加
        if (findCustomer(customer, todayCustomers) == null) {
            if (findCustomer(customer, allCustomers)  != null){
                customer = findCustomer(customer, allCustomers);
            }
            todayCustomers.add(customer);
        }

        // 处理总顾客列表：首次到店则添加
        if (findCustomer(customer, allCustomers)  == null) {
            allCustomers.add(customer);
        }

        // 更新顾客信息（无论新老顾客）
        customer.setVisitCount(customer.getVisitCount() + 1);
        customer.setLastVisitTime(date);

        //顾客逗小猫小狗
        System.out.println("==========================================================");
        System.out.println("当前顾客正在和小动物愉快地玩耍～～～");
        animal.makeSound();
        animal.eat();

        // 出售动物
        animals.remove(animal);
        double price = animal.getPrice();
        balance += price;
        todayProfit += (price - animal.getCost());

        // 输出动物信息
        System.out.println("出售动物信息: " + animal);
        System.out.printf("客户 %s 购买成功，收入: %.2f, 当前余额: %.2f%n", customer.getName(), price, balance);
        System.out.println("==========================================================");
    }

    @Override
    public void closeShop(LocalDate date) {
        isRunning = random.nextBoolean(); // 随机生成下一天的营业状态
        System.out.println("\n===== " + date + " " + getName() + "歇业 =====");
        System.out.printf("今日利润: %.2f%n", todayProfit);
        System.out.println("今日光顾的客户列表:");
        for (Customer customer : todayCustomers) {
            System.out.println(customer);
        }
        todayProfit = 0; // 今日利润归零
        todayCustomers.clear(); // 今日顾客列表清理
        System.out.println("==========================");
    }

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return allCustomers;
    }

    @Override
    public List<Customer> getTodayCustomers() { return todayCustomers; }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getTodayProfit() {
        return todayProfit;
    }

    @Override
    public boolean getIsRunning() { return isRunning; }

    @Override
    public String getName() { return name; }
}

