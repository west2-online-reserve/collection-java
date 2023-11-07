import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private ArrayList<Animal> animalArrayList;
    private ArrayList<Customer> customerArrayList;
    //是否正在营业
    private boolean isClosed;
    private double profit = 0;

    MyAnimalShop() {
    };

    MyAnimalShop(double balance) {
        this.balance = balance;
        this.animalArrayList = new ArrayList<Animal>();
        this.customerArrayList = new ArrayList<Customer>();
        this.isClosed = false;
    }

    public void showProfit() {
        System.out.println("当前宠物店的余额为：" + profit);
    }

    public double getBalance() {
        return balance;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void showbalance() {
        System.out.println("宠物店的余额为：" + balance);
    }

    public void showAnimal() {

        System.out.println("宠物店现有的宠物有:");
        for (int i = 0; i < animalArrayList.size(); i++) {
            System.out.println("序号: " + i);
            //便于输出目前已有的宠物
            System.out.println(animalArrayList.get(i).toString());
            System.out.println();
        }
    }

    public void buyAnimal() {
        System.out.println("客户要购买的动物序号为：");
        Scanner order = new Scanner(System.in);
        int orderNumber = order.nextInt();

        if (orderNumber >= 0 && orderNumber < animalArrayList.size()) {
            System.out.println("卖出的动物为:\n" + animalArrayList.get(orderNumber).toString());
            //余额、利润都增加
            balance += animalArrayList.get(orderNumber).getAnimalPrice();
            balance += animalArrayList.get(orderNumber).getAnimalPrice();
            animalArrayList.remove(orderNumber);
        } else {
            System.out.println("要购买的动物序号有误，动物数量不足！");
        }
    }

    public void showCustomer() {
        System.out.println("宠物店的客户有:");
        for (int i = 0; i < customerArrayList.size(); i++) {
            System.out.println(customerArrayList.get(i).toString());
            System.out.println();
        }
    }

    @Override
    //实现接口中的方法
    //1.买入动物
    public void buyNewAnimal(Animal animal) {
        try {
            if (isClosed) {
                System.out.println("关门了，请明天再来~");
                return;
            }

            //如果要购买的动物价钱已经超过了余额
            if (animal.getAnimalPrice() <= balance) {
                animalArrayList.add(animal);
                balance -= animal.getAnimalPrice();
                System.out.println("购买成功！宠物店目前已经有" + animalArrayList.size() + "只宠物了！");
            }
            //购买成功
            else {
                throw new InsufficientBalanceException("宠物店余额不足......");
            }
        } catch (InsufficientBalanceException exception) {
            System.out.println(exception.toString());
        }
    }

    @Override
    //实现接口中的方法
    //2.招待客户
    public void entertainCustomer(Customer customer) {
        try {
            if (isClosed) {
                System.out.println("关门了，请明天再来~");
                return;
            }

            if (customerArrayList.isEmpty()) {
                customerArrayList.add(customer);
            } else {
                boolean judge = false;
                for (int i = 0; i < customerArrayList.size(); i++) {
                    if (customer.getCustomerName() == customerArrayList.get(i).getCustomerName()) {
                        customerArrayList.get(i).addTimes();
                        customerArrayList.get(i).setDate();
                        judge = true;
                        break;
                    }
                }
                if (!judge) {
                    customerArrayList.add(customer);
                }
            }

            if (animalArrayList.isEmpty()) {
                throw new AnimalNotFountException("\nsorry,店内已经没有动物了~");
            } else {
                showAnimal();
                buyAnimal();
            }
        } catch (AnimalNotFountException exception) {
            System.out.println(exception.toString());
        }

    }

    @Override
    //实现接口中的方法
    //3.歇业
    public void closeShop() {

        this.isClosed = true;
        System.out.println("-----今日顾客-----\n");

        for (int i = 0; i < customerArrayList.size(); i++) {
            if (customerArrayList.get(i).getDate().equals(LocalDate.now())) {
                System.out.println(customerArrayList.get(i).toString());
            }
        }

        System.out.println("日期: " + LocalDate.now() + "的利润为：" + profit);
        profit = 0;
    }
}
