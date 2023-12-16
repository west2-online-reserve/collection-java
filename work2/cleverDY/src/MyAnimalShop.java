import jdk.internal.icu.text.UnicodeSet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    // 成员变量
    private double balance;
    private ArrayList<Animal> animalList = new ArrayList<Animal>();

    private ArrayList<Customer> customerList = new ArrayList<Customer>();//顾客的列表
    private boolean isOpen;

    private double profit = 0;



    public double getProfit() {
        return profit;
    }

    public MyAnimalShop(double balance,boolean isOpen) {
        this.balance = balance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
    @Override
    // 实现接口中的方法
    public void buyAnimal(Animal a) {
        try {
            double BuyTotal =  a.getPrice();
            if (BuyTotal > this.balance) {
                throw new InsufficientBalanceException("余额不足");
            }
            this.balance -= BuyTotal;
            animalList.add(a);
            System.out.println("购买成功!目前宠物店中有" + animalList.size() + "只宠物，购买的新宠物是：" +a.getName());
        } catch (InsufficientBalanceException i) {
            System.out.println(i.toString());
        }
    }


    @Override
    public void serveCustomer(Customer customer,int i) {
        if (!isOpen) {
            System.out.println("宠物店已歇业，无法招待客户。");
            return;
        }
        customerList.add(customer);
        customer.setVisitCount(customer.getVisitCount()+1);
        customer.setLatestVisit(LocalDate.now());
        if (animalList.isEmpty()) {
            System.out.println("抱歉，宠物店暂时没有动物可供出售。");
            return;
        }

        // 出售动物给客户
        try {
            if (i > animalList.size() || i <= 0) {
                throw new IndexOutOfBoundsException("购买失败，请输入正确的序号");
            }
            if (animalList.size() != 0) {
                System.out.println(customer.getName()+"买第" + i + "只宠物");
                System.out.println(animalList.get(i - 1));
                profit += animalList.get(i - 1).getPrice();//利润是卖宠物的钱
                animalList.remove(i - 1);

            }
        } catch (AnimalNotFoundException e) {
            System.out.println(e);
        }
    }
    @Override
    public void closeShop() {
        if (isOpen) {
            isOpen = false;
            System.out.println("Today's customers:");
            customerList.forEach(System.out::println);
            System.out.println("Profit for today: " + getProfit());
        }
    }
}
