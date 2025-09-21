package com.cyx.Amimals;

import com.cyx.Exception.AnimalNotFoundException;
import com.cyx.Exception.InsufficientBalanceException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop{
    private double balance;
//    private ArrayList<Animal> animalList;
    // 三个List来存储三种动物
    private ArrayList<ChineseRuralDog> chineseRuralDogList;
    private ArrayList<Cat> catList;
    private ArrayList<Bird> birdList;
    private ArrayList<Customer> customerList;
    private boolean isOpen;
    // 利润
    private double profit;
    private LocalDate openTime;
    private final Scanner sc;

    public MyAnimalShop(double balance){
        this.balance = balance;
        this.chineseRuralDogList = new ArrayList<>();
        this.catList = new ArrayList<>();
        this.birdList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = true;
        this.openTime = LocalDate.now();
        this.profit = 0;
        this.sc = new Scanner(System.in);
    }
    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if(!isOpen){
            System.out.println("宠物店今天已经歇业,无法购买动物！");
            return;
        }
        if(animal.getPurchasePrice() > balance){
            throw new InsufficientBalanceException("余额不足，无法购买" +
                    animal.getName() + "，当前宠物店余额是" + balance +
                    ",购买价格：" + animal.getPurchasePrice());
        }
        if(animal instanceof ChineseRuralDog){
            chineseRuralDogList.add((ChineseRuralDog) animal);
        }
        if(animal instanceof Cat){
            catList.add((Cat) animal);
        }
        if(animal instanceof Bird){
            birdList.add((Bird) animal);
        }

        balance -= animal.getPurchasePrice();
        System.out.println("宠物店成功买入一只动物： " + animal.getName());
        System.out.println("花费费用：" + animal.getPurchasePrice());
        System.out.println("宠物店资金还有：" + balance);
        System.out.println("\n========================================\n");
    }

    @Override
    public void serverCustomer(Customer customer) throws AnimalNotFoundException {
        if(!isOpen){
            System.out.println("宠物店今天已经歇业，无法招待顾客！");
            return;
        }
        if(chineseRuralDogList.isEmpty() && catList.isEmpty() && birdList.isEmpty()){
            System.out.println(("宠物店内没有动物可出售！"));
        }
        System.out.println("欢迎顾客：" + customer.getName() + "，当前时间为：" + LocalDateTime.now());
        System.out.println(customer.getName() + "这是您的第 " + customer.getVisitCount() + " 次来店");
        System.out.println("当前宠物店有 " + chineseRuralDogList.size() + " 只中华田园犬，" +
                catList.size() + " 只猫咪，" + birdList.size() + " 只鸟");
        // 观光次数＋1
        customer.setVisitCount();
        customer.setLastVisitTime(LocalDate.now());
        customerList.add(customer);
        //Scanner sc = new Scanner(System.in);
        System.out.println("请你选择要购买的动物：1 -> 中华田园犬 2 -> 猫 3 -> 鸟");
        int choice = sc.nextInt();

        switch (choice){
            case 1 -> {
                if(chineseRuralDogList.isEmpty()){
                    System.out.println(customer.getName() + "顾客，抱歉，宠物店内没有中华田园犬可出售！");
                    throw new AnimalNotFoundException("宠物店内没有中华田园犬可出售！");
                }
                ChineseRuralDog dog = chineseRuralDogList.remove(0);

                profit = profit + dog.getSellingPrice() - dog.getPurchasePrice();
                balance += dog.getSellingPrice();
                System.out.println("成功卖出一只中华田园犬：" + dog.getName());
                System.out.println("宠物店收入：" + dog.getSellingPrice());
                System.out.println("宠物店资金余额还有：" + balance);
                System.out.println("当前宠物店利润为：" + profit);
            }
            case 2 -> {
                if(catList.isEmpty()){
                    System.out.println(customer.getName() + "顾客，抱歉，宠物店内没有猫猫可出售！");
                    throw new AnimalNotFoundException("宠物店内没有猫可出售！");
                }
                Cat cat = catList.remove(0);
                profit += cat.getSellingPrice() - cat.getPurchasePrice();
                balance += cat.getSellingPrice();
                System.out.println("成功卖出一只猫咪：" + cat.getName());
                System.out.println("宠物店收入：" + cat.getSellingPrice());
                System.out.println("宠物店资金余额还有：" + balance);
                System.out.println("当前宠物店利润为：" + profit);
            }
            case 3 -> {
                if(birdList.isEmpty()){
                    System.out.println(customer.getName() + "顾客，抱歉，宠物店内没有小鸟可出售！");
                    throw new AnimalNotFoundException("宠物店内没有鸟出售！");
                }
                Bird bird = birdList.remove(0);
                profit += bird.getSellingPrice() - bird.getPurchasePrice();
                balance += bird.getSellingPrice();
                System.out.println("成功卖出一只小鸟：" + bird.getName());
                System.out.println("宠物店收入：" + bird.getSellingPrice());
                System.out.println("宠物店资金余额还有：" + balance);
                System.out.println("当前宠物店利润为：" + profit);
            }
            default -> {
                System.out.println("输入错误！没有这个选项！");
                System.out.println(customer.getName() + "没有选择任何动物！");
                return;
            }
        }
        System.out.println("顾客" + customer.getName() + "已经被服务！");

    }

    @Override
    public void close() {
        sc.close();
        isOpen = false;
        System.out.println("=========宠物店今天已经歇业！=========");
        System.out.println("今日光顾的顾客顺序列表：");
        for(Customer customer : customerList){
            System.out.print(customer.getName() + " ");
        }
        System.out.println("\n======================================\n");
        System.out.println("今日收入是：" + profit);
        System.out.println("宠物店今天剩余资金是：" + balance);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<ChineseRuralDog> getChineseRuralDogList() {
        return chineseRuralDogList;
    }

    public void setChineseRuralDogList(ArrayList<ChineseRuralDog> chineseRuralDogList) {
        this.chineseRuralDogList = chineseRuralDogList;
    }

    public ArrayList<Cat> getCatList() {
        return catList;
    }

    public void setCatList(ArrayList<Cat> catList) {
        this.catList = catList;
    }

    public ArrayList<Bird> getBirdList() {
        return birdList;
    }

    public void setBirdList(ArrayList<Bird> birdList) {
        this.birdList = birdList;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public LocalDate getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalDate openTime) {
        this.openTime = openTime;
    }
}
