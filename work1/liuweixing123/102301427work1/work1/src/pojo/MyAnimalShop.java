package pojo;

import base.AbstractAnimal;
import base.AnimalShop;
import exception.AnimalNotFountException;
import exception.InsufficientBalanceException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    private double balance; //余额
    private ArrayList<AbstractAnimal> Pets = new ArrayList<>(); //存放宠物列表
    private ArrayList<Customer> customers = new ArrayList<>(); //顾客列表
    private boolean isOpen; //送否营业
    private HashMap<LocalDate, BigDecimal> profits = new HashMap<>(); //记录每天的利润

    public MyAnimalShop() {
    }

    public MyAnimalShop(double balance, ArrayList<AbstractAnimal> pets, boolean isOpen) {
        this.balance = balance;
        Pets = pets;
        this.isOpen = isOpen;
    }

    @Override
    public void buy(AbstractAnimal pet) {
        try {
            if (balance < pet.getPrice())
                throw new InsufficientBalanceException("余额不足! 还需" + (pet.getPrice() - balance) + "元");
            System.out.println("成功买入一只宠物: " + pet);
            System.out.println();
            balance -= pet.getPrice();
            Pets.add(pet);
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void welcomeClients(Customer customer) {
        try {
            //添加顾客列表
            addCustomer(customer);

            //将宠物售卖给顾客
            selling();

        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void close() {
        LocalDate now = LocalDate.now();
        isOpen = false;
        System.out.println("以下为今日光顾的顾客：");
        for(int i=0;i<customers.size();i++){
            System.out.println(customers.get(i));
        }
        System.out.println(now + "利润为：" + profits.get(now));
    }

    //判断顾客是否来过这个宠物店，决定是否要加入顾客列表（暂定每个人名字是不一样的，通过名字来区分）
    private void addCustomer(Customer customer) {
        customer.setCount(customer.getCount() + 1);//到店次数加1
        customer.setDate(LocalDate.now()); //更新最新到店时间
        String name = customer.getName();
        for (int i = 0; i < customers.size(); i++) {
            String name1 = customers.get(i).getName();
            if (name.equals(name1)) { //判断今日该顾客来过,不需要在添加到顾客列表，只需要对顾客的属性修改
                customers.set(i, customer);
                return;
            }
        }
        customers.add(customer);   //该顾客今日未来过，需要添加到顾客列表
    }

    //售卖宠物
    private void selling() {
            if (Pets.size() == 0)
                throw new AnimalNotFountException("店内已经没有可售卖的宠物了");
            System.out.println("请按序号挑选顾客要购买的宠物");
            System.out.println("0 :顾客暂时不打算购买"); //或者其他宠物序号之外的数字
            for (int i = 0; i < Pets.size(); i++) {
                System.out.print(i + 1 + " ");
                System.out.println(Pets.get(i));
            }
            Scanner sc = new Scanner(System.in);
            //顾客不购买
            while (true) {
                int choose = sc.nextInt();
                if (choose == 0) {
                    System.out.println("顾客未购买任何宠物");
                    System.out.println();
                    break;
                }//顾客购买
                else if (choose >= 1 && choose <= Pets.size()) {
                    System.out.println("请输入顾客所花的费用");
                    double cost = sc.nextDouble();
                    balance += cost;  //小店余额改变
                    BigDecimal profit = BigDecimal.valueOf(cost - Pets.get(choose - 1).getPrice());

                    //添加利润到profits中
                    if (profits.containsKey(LocalDate.now())) {
                        BigDecimal newProfit = profits.get(LocalDate.now()).add(profit);
                        profits.put(LocalDate.now(), newProfit);
                    } else {
                        profits.put(LocalDate.now(), profit);
                    }
                    System.out.println();
                    //将卖出去的宠物从宠物列表中删除
                    Pets.remove(choose - 1);
                    break;
                } else {
                    System.out.println("选择有误,请重新选择！");
                }
            }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<AbstractAnimal> getPets() {
        return Pets;
    }

    public void setPets(ArrayList<AbstractAnimal> pets) {
        Pets = pets;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public String toString() {
        return "MyAnimalShop{" +
                "balance=" + balance +
                ", Pets=" + Pets +
                ", customers=" + customers +
                ", isOpen=" + isOpen +
                ", profits=" + profits +
                '}';
    }
}
