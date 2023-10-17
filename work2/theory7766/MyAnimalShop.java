package com.west2.work2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {

    /**
     * 余额
     */
    private double balance;
    /**
     * 宠物列表
     */
    private ArrayList<Animal> pets;
    /**
     * 顾客列表
     */
    private ArrayList<Customer> customers;
    /**
     * 正在营业
     */
    private boolean isBusiness;

    /**
     * 利润
     */
    private double profit;

    /**
     * 类的全参构造函数
     *
     * @param balance    余额
     * @param pets       宠物列表
     * @param customers  顾客列表
     * @param isBusiness 正在营业参数
     * @param profit     利润
     */
    public MyAnimalShop(double balance, ArrayList<Animal> pets, ArrayList<Customer> customers, boolean isBusiness, double profit) {
        this.balance = balance;
        this.pets = (pets == null) ? new ArrayList<>() : new ArrayList<>(pets);
        this.customers = (customers == null) ? new ArrayList<>() : new ArrayList<>(customers);
        this.isBusiness = isBusiness;
        this.profit = profit;
    }


    /**
     * 获取余额
     *
     * @return balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * 设置余额
     *
     * @param balance 余额
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 获取宠物列表
     *
     * @return pets
     */
    public ArrayList<Animal> getPets() {
        return pets;
    }

    /**
     * 设置宠物列表
     *
     * @param pets 宠物列表
     */
    public void setPets(ArrayList<Animal> pets) {
        this.pets = pets;
    }

    /**
     * 获取顾客列表
     *
     * @return customers
     */
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    /**
     * 设置顾客列表
     *
     * @param customers 顾客列表
     */
    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    /**
     * 获取正在营业参数
     *
     * @return isBusiness
     */
    public boolean isIsBusiness() {
        return isBusiness;
    }

    /**
     * 设置正在营业参数
     *
     * @param isBusiness 正在营业参数
     */
    public void setIsBusiness(boolean isBusiness) {
        this.isBusiness = isBusiness;
    }

    /**
     * 获取利润
     *
     * @return balance
     */
    public double getProfit() {
        return profit;
    }

    /**
     * 设置利润
     *
     * @param profit 利润
     */
    public void setProfit(double profit) {
        this.profit = profit;
    }

    /**
     * 显示宠物菜单
     */
    public void showAnimalMenu() {
        System.out.println("***宠物菜单***");
        for (int i = 0; i < pets.size(); i++) {
            System.out.println(i + ":" + pets.get(i).toString());
        }
        System.out.println();
    }

    /**
     * 购买新宠物
     *
     * @param animal 新宠物
     */
    @Override
    public void buyNewAnimal(Animal animal) {
        if (animal.getPrice() > this.balance) {
            throw new InsufficientBalanceException(animal, this.balance);
        } else {
            balance -= animal.getPrice();
            pets.add(animal);
            System.out.println("成功购买" + animal.toString());
        }
    }


    /**
     * 招待新顾客，其可买宠物
     *
     * @param c 要招待的顾客
     */
    @Override
    public void entertainCustomers(Customer c) {
        if (!this.isBusiness) {
            throw new ShopClosedException(LocalDate.now());
        }
        // 将顾客加入顾客列表
        customers.add(c);
        // 更新顾客到店次数
        c.setCnt(c.getCnt() + 1);
        // 更新顾客到店时间
        c.setTime(LocalDate.now());
        Scanner sc = new Scanner(System.in);
        System.out.println("尊敬的顾客您好，购买宠物输入true，否则输入false：");
        boolean choice = sc.nextBoolean();

        if (!choice) {
            System.out.println("希望下次会有您喜欢的宠物！");
            return;
        }

        while (choice) {
            if (pets.isEmpty()) {
                throw new AnimalNotFoundException("宠物店没有宠物");
            } else {
                int index;
                // 显示菜单
                showAnimalMenu();
                System.out.print("请输入您想要购买的宠物下标：");
                index = sc.nextInt();
                if (index < 0 && index >= pets.size()) {
                    throw new AnimalNotFoundException("下标错误，找不到动物！");
                } else {
                    System.out.println(pets.get(index).toString());
                    balance += pets.get(index).price + 15;
                    this.profit += 15;
                    pets.remove(index);
                }
                System.out.println("如果您还想购买宠物请输入true,否则false:");
                choice = sc.nextBoolean();
            }
        }
        sc.close();
        System.out.println("谢谢惠顾");
    }

    /**
     * 关店
     */
    @Override
    public void Shutdown() {
        this.isBusiness = false;
        for (Customer customer : customers) {
            if (LocalDate.now().equals(customer.getTime())) {
                System.out.println(customer.toString());
            }
        }
        System.out.println("今日总利润：" + this.profit);
    }


    /**
     * 开店
     */
    @Override
    public void setUp() {
        this.isBusiness = true;
        System.out.println("宠物店开店了！");
        // 利润归零
        this.profit = 0;
    }

}