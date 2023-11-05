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
     * 动物列表
     *
     * @param animal
     */
    private ArrayList<Animal> petsList = new ArrayList<>();

    /**
     * 顾客列表
     *
     * @param customer
     */
    private ArrayList<Customer> customersList = new ArrayList<>();

    /**
     * 是否营业
     */
    private boolean isTrade;

    /**
     * 当日总利润，初始化为0
     */
    private double profit = 0.0;

    public MyAnimalShop(double balance, ArrayList<Animal> petsList, ArrayList<Customer> customersList, boolean isTrade, double profit) {
        this.balance = balance;
        this.petsList = petsList;
        this.customersList = customersList;
        this.isTrade = isTrade;
        this.profit = profit;
    }

    /**
     * 买入新的宠物
     * 单次只能支持一次买一只
     *
     * @param animal
     */
    @Override
    public void buyNewAnimal(Animal animal) throws InsufficientBalanceException {
        //判断余额是否充足
        if (this.balance < animal.getPrice()) {
            throw new InsufficientBalanceException(this.balance);
        }
        System.out.println("您已经成功购买一只新的宠物，姓名为" + animal.getName() + "。");
        //添加到动物列表
        this.petsList.add(animal);
        //扣除余额
        balance -= animal.getPrice();
        //减少利润
        profit -= animal.getPrice();
    }

    /**
     * 招待客户（一个一个招待）接受客户参数，在顾客列表中加入新顾客，
     * 出售动物，如果店内没有动物，抛出AnimalNotFoundException
     *
     * @param customer
     */
    @Override
    public void treatCustomer(Customer customer) {
        Scanner sc = new Scanner(System.in);
        /**
         * 顾客所要的宠物
         */
        String animals;
        /**
         * 顾客要的宠物的数量
         */
        int number = 0;
        /**
         * 用于标记是否找到顾客所要的动物
         */
        int flag = 0;

        //判断是否营业
        if (!isTrade) {
            System.out.println("此时并不在营业，欢迎下次光临噢！！！");
            return;
        }

        //开始招待顾客
        //更新到店次数和时间，并将客户添加到客户列表
        customer.setTime(LocalDate.now());
        customer.setArrivalTimes(customer.getArrivalTimes() + 1);
        this.customersList.add(customer);
        //考虑是否有动物
        if (petsList.isEmpty()) {
            System.out.println("亲爱的顾客不好意思，现在宠物店里没有动物，十分抱歉，欢迎下次光临！");
            throw new AnimalNotFoundException(customer.getCustomerName());
        }

        //开始介绍
        System.out.println("顾客你好，我们店里现在有以下这几只小动物噢：");
        for (Animal animal : petsList) {
            System.out.println(animal.toString());
        }

        //询问顾客要购买几只，分别叫什么，没有就输出没有，有的话将动物在列表中删除并更新利润
        System.out.println("顾客您有没有想购买的呢？如果有的话告诉我要购买几只和他们的名字噢！");
        number = sc.nextInt();
        if (number <= 0) {
            System.out.println("没有想要购买的，那就欢迎下次再来看看呢！");
        } else {
            for (int i = 0; i < number; i++) {
                flag = 0;
                animals = sc.next();
                Animal pet = null;
                //查找宠物
                for (Animal animal : petsList) {
                    if (animal.getName().equals(animals)) {
                        pet = animal;
                        flag = 1;
                    }
                }
                if (flag == 1) {
                    profit += pet.getPrice() * 1.5;
                    balance += pet.getPrice() * 1.5;
                    petsList.remove(pet);
                    System.out.println("顾客" + customer.getCustomerName() + "成功购买" + animals);
                } else {
                    System.out.println("很抱歉，没能找到名字叫做" + animals + "的小宠物噢");
                }
            }
        }
    }

    /**
     * 关店
     * 输出当天光顾的客户的列表和当天的利润
     */
    @Override
    public void outOfBusiness() {
        System.out.println("今日打烊啦 (๑￫ܫ￩)");
        //设置营业状态
        this.isTrade = false;
        //输出当日光顾的顾客
        if (!customersList.isEmpty()) {
            System.out.println("--------今日顾客--------");
            for (Customer customer : customersList) {
                if (LocalDate.now().equals(customer.getTime())) {
                    System.out.println(customer);
                }
            }
        } else {
            System.out.println("哦偶，今天没有顾客光顾，继续加油噢！");
        }
        System.out.println("今日的总利润是：" + this.profit + "！");
    }

    /**
     * 展示现在宠物店所拥有的宠物
     */
    public void showPets() {
        for (Animal animal : petsList) {
            System.out.println(animal.toString1());
        }
    }

    /**
     * 获取
     *
     * @return balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * 设置
     *
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 获取
     *
     * @return petsList
     */
    public ArrayList<Animal> getPetsList() {
        return petsList;
    }

}
