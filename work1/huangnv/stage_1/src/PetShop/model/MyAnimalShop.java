package PetShop.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class MyAnimalShop implements AnimalShop {
    private double money;
    private double baseMoney;
    private List<Animal> animalList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    private boolean isOpen;

    public MyAnimalShop(double money) {
        this.money = money;
        this.isOpen = false;
    }

    private void ensureOpen() {
        if (!isOpen) {
            throw new ShopClosedException("店铺未营业");
        }
    }

    @Override
    public void buyAnimal(Animal animal) {
        ensureOpen();
        if (this.money < animal.buyPrice) {
            throw new InsufficientBalanceException(
                "没有足够的钱购买，购买价格为" + animal.buyPrice + "，当前余额为" + this.money
            );
        } else {
            animalList.add(animal);
            this.money -= animal.buyPrice;
        }
    }

    @Override
    public void treatCustomer(Customer customer, Animal animal) {
        ensureOpen();
        if (!animalList.contains(animal)) {
            throw new AnimalNotFountException("店内无此动物");
        } else {
            if (!customerList.contains(customer)) {
                customerList.add(customer);
            }
            customer.addVisitCount();
            customer.setLatestVisitDate(LocalDate.now());
            System.out.println(animal.toString());
            animalList.remove(animal);
            this.money += animal.sellPrice;
        }
    }

    @Override
    public void open() {
        this.isOpen = true;
        this.baseMoney = this.money;
    }

    @Override
    public void close() {
        this.isOpen = false;
        LocalDate today = LocalDate.now();
        System.out.println("--- " + today + " 歇业结算 ---");
        System.out.println("今日到店顾客：");
        for (Customer c : customerList) {
            if (c.getLatestVisitDate().equals(today)) {
                System.out.println(c.toString());
            }
        }
        double profit = this.money - this.baseMoney;
        System.out.println("今日利润：" + profit + " 元");
    }
}