package shop;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private final List<Animal> animalList;
    private final List<Customer> customerList;
    private boolean isOpen;

    // 初始化一天的记录
    private double todayIncome = 0;
    private double todayCost = 0;

    public MyAnimalShop(double initialBalance) {
        this.balance = initialBalance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = false;
    }

    public void open() {
        if (isOpen) {
            System.out.println("店铺开始营业");
            return;
        }
        this.isOpen = true;
        this.todayIncome = 0;
        this.todayCost = 0;
        System.out.println("初始余额: " + balance + "元");
    }

    @Override
    public void buyNewAnimal(Animal animal, int quantity) {
        if (!isOpen) {
            System.out.println("店铺未营业，不能进货！");
            return;
        }

        double costPrice = animal.getPrice() * 0.5;
        double totalCost = costPrice * quantity;

        if (this.balance < totalCost) {
            throw new InsufficientBalanceException("余额不足！进货还差"+(totalCost - balance)+"元");
        }

        this.balance -= totalCost;
        this.todayCost += totalCost;
        for (int i = 0; i < quantity; i++) {
            animalList.add(animal);
        }

        System.out.println("【进货成功】买入 " + quantity + " 只 " + animal.getName() + "，共花费: " + totalCost + "元");
    }

    @Override
    public void serveCustomer(Customer customer) {
        if (!isOpen) {
            System.out.println("店铺已打烊，请明天再来！");
            return;
        }

        customer.visit();
        if (!customerList.contains(customer)) {
            customerList.add(customer);
        }

        System.out.println("\n欢迎光临，" + customer.getName());

        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("店内已经没有动物了，无法招待顾客");
        }

        Animal soldAnimal = animalList.removeFirst();
        //如果是狗则判断疫苗情况
        if (soldAnimal instanceof Dog soldDog) {
            if (soldDog.isVaccineInjected()) {
                System.out.println("提示：这只狗已经打过疫苗");
            } else {
                System.out.println("提示：这只狗还未打过疫苗");
            }
        }

        double sellPrice = soldAnimal.getPrice();

        this.balance += sellPrice;
        this.todayIncome += sellPrice;

        System.out.println("顾客买走了：[" + soldAnimal + "]");
        System.out.println("本次店内收入: " + sellPrice + "元");
        System.out.println("店铺当前余额: " + balance + "元");
    }

    @Override
    public void closeShop() {
        if (!isOpen) {
            System.out.println("店铺早就歇业了。");
            return;
        }
        this.isOpen = false;
        System.out.println("已歇业");

        System.out.println("今日光顾客户有：");
        boolean hasCustomer = false;
        for (Customer c : customerList) {

            if (c.getLastVisitDate() != null && c.getLastVisitDate().isEqual(LocalDate.now())) {
                System.out.println(" " + c);
                hasCustomer = true;
            }
        }
        if (!hasCustomer) {
            System.out.println("今天没有顾客光顾。");
        }


        double profit = todayIncome - todayCost;
        System.out.println("\n【今日营业报表】");
        System.out.println("今日总支出: " + todayCost + " 元");
        System.out.println("今日总收入: " + todayIncome + " 元");
        System.out.println("今日净利润: " + profit + " 元");
        System.out.println("店铺当前总余额: " + balance + " 元");
    }
}