package learn_java1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    private boolean isClosed;//是否营业
    private double balance;//余额
    private double profit;//利润
    private ArrayList<Animal> animals;//存放动物的列表
    private ArrayList<Customer> customers;//存放光临过客户的列表，假设所有客户名字均不相同

    public MyAnimalShop(boolean isClosed, double balance, ArrayList<Animal> animals, ArrayList<Customer> customers) {
        this.isClosed = isClosed;
        this.balance = balance;
        this.animals = animals;
        this.customers = customers;
        profit = 0;
    }

    public void open() {
        isClosed = false;
        System.out.println("该店已营业");
    }

    @Override//歇业
    public void CloseShop(LocalDate date) {
        System.out.println("今日为" + date + "，利润为" + profit + "元" + "，店资金余额为" + balance + "元，下面为该日光临过的客户信息");
        for (Customer customer : customers) {
            if (customer.getLatestArrivalTime() == date) {
                System.out.println(customer.toString());
            }
        }
        isClosed = true;
    }

    @Override//招待客户，假设所有客户名字都不一样
    public void entertainCustomer(Customer customer) {
        if (isClosed) {
            System.out.println("该店还未营业，请下次再来");
            return;
        }
        if (animals.isEmpty()) {
            throw new AnimalNotFountException();
        }
        for (int i = 1; i <= animals.size(); i++)
            System.out.println(i + ":" + animals.get(i - 1).toString());
        System.out.println("选择想要购入的编号");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        while (choice < 1 || choice > animals.size()) {
            System.out.println("错误选项，请重试");
            choice = sc.nextInt();
        }
        System.out.println("尊贵的客户" + customer.getName() + "已完成购入，请下次再来");
        customers.removeIf(p -> p.getName().equals(customer.getName()));
        customer.addNumOfVisits();
        customers.add(customer);

        profit += animals.get(choice - 1).getPrice() - animals.get(choice - 1).getPurchasePrice();
        balance += animals.get(choice - 1).getPrice();
        animals.remove(animals.get(choice - 1));
        Collections.sort(customers);
    }

    @Override//购入动物
    public void purchaseAnimal(Animal animal) {
        if (animal.purchasePrice > balance) {
            throw new InsufficientBalanceException();
        }
        balance -= animal.purchasePrice;
        animals.add(animal);
        System.out.println("已成功购入一只" + animal.toString() + ",购入价为" + animal.purchasePrice + "，店内余额为" + balance);
        Collections.sort(animals);
    }


}
