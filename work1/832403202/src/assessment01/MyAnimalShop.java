package assessment01;

import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double balance;                // 店铺余额
    private List<Animal> animals;          // 动物列表
    private List<Customer> customers;      // 顾客列表
    private boolean isOpen;                // 是否正在营业

    // 构造方法
    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.animals = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.isOpen = true;
    }

    // 买入动物
    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if (balance < animal.getPrice()) {
            throw new InsufficientBalanceException("余额不足，无法购买动物！");
        }
        animals.add(animal);
        balance -= animal.getPrice();
    }

    // 招待顾客
    @Override
    public void attendCustomer(Customer customer) throws AnimalNotFoundException {
        if (animals.isEmpty()) {
            throw new AnimalNotFoundException("店内没有动物可以出售！");
        }
        customers.add(customer);
        Animal animalToSell = animals.remove(0);  // 卖掉第一只动物
        balance += animalToSell.getPrice();       // 收款
        System.out.println("出售动物: " + animalToSell);
    }

    // 歇业
    @Override
    public void closeShop() {
        if (isOpen) {
            System.out.println("店铺歇业，今天光顾的顾客有：");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
            System.out.println("今天的利润是: " + balance);
            isOpen = false;
        } else {
            System.out.println("店铺已经歇业！");
        }
    }

    // 获取余额
    public double getBalance() {
        return balance;
    }

    // 获取动物列表
    public List<Animal> getAnimals() {
        return animals;
    }

    // 获取顾客列表
    public List<Customer> getCustomers() {
        return customers;
    }
}
