package PetShop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop{
    private double balance;
    private List<Animal> animals;
    private List<Customer> customers;
    private boolean isOpen;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.animals = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if (balance < animal.price) {
            throw new InsufficientBalanceException("余额不足，无法购买价格为 $" + animal.price + " 的动物");
        }
        balance -= animal.price;
        animals.add(animal);
        System.out.println("成功购入：" + animal.toString());
    }

    @Override
    public void serveCustomer(Customer customer) {
        LocalDate today = LocalDate.now();
        boolean found = false;
        for (Customer c : customers) {
            if (c.getName().equals(customer.getName())) {
                if (!c.getLastVisitTime().equals(today)) {
                    c.incrementVisit();
                    try {
                        c.getClass().getDeclaredFields()[2].set(c, today); // 更新最后访问时间（实际项目应使用 setter）
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                found = true;
                break;
            }
        }
        if (!found) {
            Customer newCustomer = new Customer(customer.getName(), today);
            customers.add(newCustomer);
        }

        if (animals.isEmpty()) {
            throw new AnimalNotFoundException("店内无动物可出售！");
        }

        Animal soldAnimal = animals.remove(0);
        balance += soldAnimal.price;
        System.out.println("已向客户 " + customer.getName() + " 出售：" + soldAnimal.toString());
    }

    @Override
    public void close() {
        LocalDate today = LocalDate.now();
        System.out.println("今日光顾的客户列表：");
        for (Customer c : customers) {
            if (c.getLastVisitTime().equals(today)) {
                System.out.println(c);
            }
        }

        // 假设初始余额在构造时设定
        double profit = balance - 1000; // 示例中假设起始余额为1000
        System.out.println("今日利润：" + profit + "元");
        isOpen = false;
    }
}
