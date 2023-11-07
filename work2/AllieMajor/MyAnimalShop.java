package HomeWork;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    static {
        System.out.println("宠物店开始营业了！");
        System.out.println("-----------------------------");
    }

    private double balance;
    private ArrayList<Animal> list1 = new ArrayList<>();
    private ArrayList<Customer> list2 = new ArrayList<>();
    private boolean shutdown;
    private double profit = 0;

    public MyAnimalShop() {
    }

    public MyAnimalShop(double balance, boolean shutdown) {
        this.balance = balance;
        this.shutdown = shutdown;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Animal> getList1() {
        return list1;
    }

    public void setList1(ArrayList<Animal> list1) {
        this.list1 = list1;
    }

    public ArrayList<Customer> getList2() {
        return list2;
    }

    public void setList2(ArrayList<Customer> list2) {
        this.list2 = list2;
    }

    public boolean isShutdown() {
        return shutdown;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    @Override
    public void buyNewAnimal(Animal a) {
        if (balance < a.getPrice()) {
            //余额不足则抛出异常InsufficientBalanceException
            System.out.println("余额不足，无法再买新动物了");
            throw new InsufficientBalanceException("余额不足，无法再买新动物了");
        } else {
            list1.add(a);
            System.out.println("宠物店买入一只新的动物，成功买入" + a.getName());
            this.balance = this.balance - a.getPrice();
            this.profit = this.profit - a.getPrice();
        }
    }

    @Override
    public void entertainCustomer(Customer c) {
        Animal a = new Animal() {
            @Override
            public String toString() {
                return null;
            }
        };
        System.out.println(c.getName() + ",欢迎您的到来！");
        if (list1.size() == 0) {
            System.out.println("已经没有动物了，请下次再来");
        } else {
            System.out.println("请选择你的英雄:");
            for (int i = 0; i < list1.size(); i++) {
                System.out.println(i + ". " + list1.get(i).getName());
            }
            System.out.println("-------------------------------");
            boolean w = true;
            Scanner sc = new Scanner(System.in);
            while (w) {
                System.out.println("请输入想要的动物编号：");
                int index = sc.nextInt();
                if (index >= 0 && index <= list1.size() - 1) {
                    for (int i = 0; i < list1.size(); i++) {
                        if (i == index) {
                            a = list1.get(i);
                        }
                    }
                    break;
                } else {
                    System.out.println("请输入正确的动物编号！");
                    continue;
                }
            }
            if (a instanceof Cat) {
                System.out.println(c.getName() + "选择了可爱的猫");
                System.out.println("-----------------------------------");
            } else if (a instanceof ChineseRuralDog) {
                System.out.println(c.getName() + "选择了可爱的中华田园犬");
                System.out.println("-----------------------------------");
            }
            //判断顾客是否已经来过
            boolean z = true;
            for (int i = 0; i < list2.size(); i++) {
                if(c == list2.get(i)){
                    z = false;
                }
            }
            if(z){
                list2.add(c);
            }
            //到店次数加一
            int count = c.getCount();
            count++;
            c.setCount(count);
            c.setLastTime(LocalDate.now());
            boolean hava = false;
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i).equals(a)) {
                    //有这只动物
                    hava = true;
                    String information = a.toString();
                    System.out.println(information);
                    //收入
                    this.balance += a.getPrice();
                    this.profit += a.getPrice();
                    //移除动物
                    list1.remove(i);
                    System.out.println(c.getName() + "成功购买" + a.getName());
                    System.out.println("-----------------------------------");
                }
            }
            //店内没有动物，抛出AnimalNotFoundException。
            if (!hava) {
                System.out.println("没有这只动物了");
                throw new AnimalNotFountException("店内无此动物");
            }
        }
    }

    @Override
    public void shutdown() {
        this.shutdown = true;
        for (int i = 0; i < list2.size(); i++) {
            if (LocalDate.now().equals(list2.get(i).getLastTime())) {
                String infomation = list2.get(i).toString();
                System.out.println("第" + (i + 1) + "位顾客的信息为:");
                System.out.println(infomation);
            }
        }
        System.out.println("今日利润为" + this.profit);
        System.out.println("宠物店余额为" + this.balance);
        System.out.println("今日宠物店还剩下：");
        for (int i = 0; i < list1.size(); i++) {
            System.out.println(list1.get(i).getName());
        }
        System.out.println("------------------------------------");
        System.out.println("宠物店正式打烊力！");
    }
}

