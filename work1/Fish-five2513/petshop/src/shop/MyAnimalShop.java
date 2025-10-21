// MyAnimalShop.java
package shop;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animals;
    private List<Customer> customers;
    private boolean isOpen;
    private LocalDate businessDate;

    public MyAnimalShop(double initialBalance) {
        this.balance = initialBalance;
        this.animals = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.isOpen = true;
        this.businessDate = LocalDate.now();
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if (balance < animal.getPrice()) {
            throw new InsufficientBalanceException("余额不足，无法购买动物: " + animal.name);
        }
        animals.add(animal);
        balance -= animal.getPrice();
        System.out.println("成功买入动物: " + animal.toString());
    }

    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFoundException {
        if (animals.isEmpty()) {
            throw new AnimalNotFoundException("店内没有动物可卖");
        }
        customers.add(customer);
        customer.visit();
        int i=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("今日可卖动物列表:");
        for (int j=0;j<animals.size();j++) {
            System.out.println(j+1 + ". " + animals.get(j).toString());
        }

        System.out.println("请输入要出售的动物编号: ");
        i = sc.nextInt();
        Animal soldAnimal = animals.remove(i-1);

        // 使用顾客偏好价格
        double adjustedPrice = customer.getAdjustedPrice(soldAnimal);
        balance += adjustedPrice;
        System.out.println("成功出售动物给 " + customer.getName() + ": " + soldAnimal.toString());
        System.out.println("调整后价格: ¥" + adjustedPrice);
    }



    @Override
    public void closeShop() {
        System.out.println("今日光顾的顾客:");
        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
        System.out.println("今日利润: " + (balance - 2000)); // 假设初始资金为2000
        isOpen = false;
    }

    // Getter methods for UI
    public double getBalance() {
        return balance;
    }

    public int getAnimalCount() {
        return animals.size();
    }

    public int getCustomerCount() {
        return customers.size();
    }

    public String getName() {
        return "我的宠物店";
    }
}
