package com.cai.task01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MyPetShop implements PetShopMethods {

    private String name;
    private List<Animal> animals;
    private Map<String, Customer> allCustomers; // 所有到店的顾客Map（ID为键）
    private Map<String, Customer> todayCustomers; // 今日到店顾客Map（ID为键）
    private double balance;
    private double todayProfit;
    private boolean isRunning = true;
    private Random random = new Random();

    public MyPetShop(String name, double initialBalance) {
        this.name = name;
        this.animals = new ArrayList<>();
        this.allCustomers = new HashMap<>(); // 初始化HashMap
        this.todayCustomers = new HashMap<>(); // 初始化HashMap
        this.balance = initialBalance;
        this.todayProfit = 0;
    }

    @Override
    public void buyAnimal(List<Animal> animalCanBuyList, Animal animal, double cost, double sellPrice) throws InsufficientBalanceException {
        if (cost > balance) {
            throw new InsufficientBalanceException(String.format("余额不足，当前余额: %.2f, 需要: %.2f", balance, cost));
        }
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
    public void serveCustomer(Customer customer, Animal animal, LocalDate date) {
        String customerId = customer.getID(); // 获取顾客ID作为键

        //先判断顾客是否在allCustomers中，如果不在，那么向allCustomers 和 todayCustomers中一起添加
        if (!allCustomers.containsKey(customerId)) {
            allCustomers.put(customerId, customer);
            todayCustomers.put(customerId, customer);
        }else {
            //如果在allCustomers中，则使用allCustomers中的数据来添加，保持数据的一致性
            customer = allCustomers.get(customerId);
            todayCustomers.put(customerId, customer);
        }

        // 更新顾客信息（无论新老顾客）
        Customer targetCustomer = allCustomers.get(customerId); // 从总Map中获取最新实例
        targetCustomer.setVisitCount(targetCustomer.getVisitCount() + 1);
        targetCustomer.setLastVisitTime(date);

        if (!animals.contains(animal)) {
            throw new AnimalNotFoundException("没有指定的动物");
        }

        // 顾客与动物互动
        System.out.println("==========================================================");
        System.out.println("当前顾客正在和小动物愉快地玩耍～～～");
        animal.makeSound();
        animal.eat();

        // 出售动物
        animals.remove(animal);
        double price = animal.getPrice();
        balance += price;
        todayProfit += (price - animal.getCost());

        // 输出交易信息
        System.out.println("出售动物信息: " + animal);
        System.out.printf("客户 %s 购买成功，收入: %.2f, 当前余额: %.2f%n", targetCustomer.getName(), price, balance);
        System.out.println("==========================================================");
    }

    @Override
    public void closeShop(LocalDate date) {
        isRunning = random.nextBoolean();
        System.out.println("\n===== " + date + " " + getName() + "歇业 =====");
        System.out.printf("今日利润: %.2f%n", todayProfit);

        // 判断今日顾客Map是否为空，为空则输出提示，否则遍历顾客
        if (todayCustomers.isEmpty()) {
            System.out.println("今日没有顾客光临小店～～～");
        } else {
            // 遍历今日顾客Map的值
            System.out.println("今日光顾的客户列表:");
            for (Customer customer : todayCustomers.values()) {
                System.out.println(customer);
            }
        }

        todayProfit = 0;
        todayCustomers.clear(); // 清空今日顾客Map
        System.out.println("======================================");
    }

    @Override
    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public List<Customer> getAllCustomers() {
        // 转换为List返回（保持接口兼容性）
        return new ArrayList<>(allCustomers.values());
    }

    @Override
    public List<Customer> getTodayCustomers() {
        // 转换为List返回（保持接口兼容性）
        return new ArrayList<>(todayCustomers.values());
    }

    // 新增根据ID获取顾客的方法（可选，方便直接通过ID操作）
    public Customer getCustomerById(String id) {
        return allCustomers.get(id);
    }

    public Customer getTodayCustomerById(String id) {
        return todayCustomers.get(id);
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public double getTodayProfit() {
        return todayProfit;
    }

    @Override
    public boolean getIsRunning() {
        return isRunning;
    }

    @Override
    public String getName() {
        return name;
    }
}