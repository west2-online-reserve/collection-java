package java_work2;

import java.util.ArrayList;
import java.util.List;

/**
 * AnimalShop类
 * 成员变量initialBalance记录初始余额
 *
 * @author formrc
 * @date 2023/10/25
 */
class MyAnimalShop implements AnimalShop {
    private double balance;
    private double initialBalance;
    private List<Animal> animals;
    private List<Customer> customers;
    private boolean isOpen;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.animals = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.isOpen = true;
        this.initialBalance = balance;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        //  将售价的一半作为进价，若余额不足则抛出异常InsufficientBalanceException
        if (animal.getPrice() / 2.0 > balance) {
            throw new InsufficientBalanceException("Insufficient balance to buy animal: " + "\n" + animal);
        }

        animals.add(animal);
        balance -= animal.getPrice() / 2.0;
    }

    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFoundException {
        //  若店内没有动物，抛出异常AnimalNotFoundException。
        if (animals.isEmpty()) {
            throw new AnimalNotFoundException("No animals available for sale." + "\n" + customer);
        }

        Animal animal = animals.remove(0);
        System.out.println("Serving customer: " + "\n" + customer);
        System.out.println("Selling animal: " + "\n" + animal);
        customers.add(customer);
        balance += animal.getPrice();
    }

    @Override
    public void closeBusiness() {
        isOpen = false;
        System.out.println("\n" + "Closing business...");

        for (Customer customer : customers) {
            System.out.println(customer);
        }

        double profit = balance - initialBalance ;
        System.out.println("Profit: " + profit);
    }
}
