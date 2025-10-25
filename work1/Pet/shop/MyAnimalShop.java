package Pet.shop;

import com.sun.org.apache.bcel.internal.generic.BALOAD;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    double balance;
    private final List<Animal> animals;
    private final List<Customer> customers;
    boolean isOpen = true;
    private LocalDate today;

    public MyAnimalShop(double initialBalance) {
        this.balance = initialBalance;
        this.animals = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.today = LocalDate.now();
    }

    @Override
    public void buyNewAnimal(Animal a) {
        if (!isOpen) {
            throw new IllegalStateException("Shop is closed");
        }
        if (balance < a.price) {
            throw new InsufficientBalanceException("余额不足，无法购买" + a);
        }
        balance -= a.price;
        animals.add(a);
        System.out.println("买入动物：" + a);
    }

    @Override
    public void serveCustomer(Customer c) {
        if (!isOpen) {
            throw new IllegalStateException("Shop is closed");
        }
            c.come(today);
            customers.removeIf(old -> old.getName().equals(c.getName()));
            customers.add(c);
        if (animals.isEmpty()) {
            throw new AnimalNotFoundException("店内暂无动物可售");
        }
        Animal sold = animals.remove(0);
        balance += sold.price;
        System.out.println("出售动物" + sold);
        System.out.println("顾客" + c.getName() + "带走一只宠物，入账" + sold.price + "元");
    }

    @Override
    public void close() {
        isOpen = false;
        System.out.println("============歇业中============");
        System.out.println("===========今日结算===========");
        System.out.println("今日日期：" + today);
        System.out.println("今日光顾客户：");
        System.out.println("当前余额：" + balance + "元");
    }

    public void setToday(LocalDate today) {
        this.today = today;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public double getBalance(){
        return balance;
    }
}
