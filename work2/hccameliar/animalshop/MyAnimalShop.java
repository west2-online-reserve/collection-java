package animalshop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * MyAnimalShop类表示自己的宠物店
 *
 * 该类包含宠物店的基本信息和操作
 * @author 102301412
 */

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private boolean isOpen;
    private final List<BaseAnimal> animalList;
    private final List<Customer> customerList;
    ArrayList<BaseAnimal> todayExpense = new ArrayList<>();
    ArrayList<BaseAnimal> todayIncome = new ArrayList<>();

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    /**
     * 购买动物方法
     */
    @Override
    public void purchase(BaseAnimal animal) {
        if (balance < animal.getPrice()) {
            throw new InsufficientBalanceException("余额不足，无法买入动物");
        }
        else {
            balance -= animal.getPrice();
            animalList.add(animal);
            System.out.println("购买成功，新宠物是：" + animal);
        }
        todayExpense.add(animal);
    }


    /**
     * 招待顾客方法
     */
    @Override
    public void treatCustomer(Customer customer, String petName) {
        if (isOpen) {
            System.out.println("不好意思，本店休息中");
        } else {
            customerList.add(customer);
            if (animalList.isEmpty()) {
                throw new AnimalNotFountException("不好意思，动物已售空乀(ˉεˉ乀)");
            } else {
                // 输出店内宠物信息
                System.out.println("欢迎光临，店内宠物有: ");
                for (BaseAnimal a : animalList) {
                    System.out.println(a.toString());
                }
                BaseAnimal theAnimal = null;
                // 根据宠物名字查找动物
                for (BaseAnimal a : animalList) {
                    if (a.getPetName().equals(petName)) {
                        theAnimal = a;
                        break;
                    }
                    //如果没有该动物直接抛出异常
                    if (theAnimal == null) {
                        customer.visitTimes++;
                        throw new AnimalNotFountException("没有找到该动物╮(╯Д╰)╭");
                    }
                }
                //卖出动物
                todayIncome.add(theAnimal);
                animalList.remove(theAnimal);
                balance += theAnimal.getPrice();
                customer.visitTimes++;
                System.out.println("顾客：" + customer.getCustomerName() + " 已成功购买:" + theAnimal.getPetName());

            }
        }
    }


     /**
     * 歇业方法
     */
    @Override
    public void close() {
    // 关门
        LocalDateTime currentDay = LocalDateTime.now();
        int currentHour = LocalDateTime.MAX.getHour();
        if (currentHour >= 21 || currentHour < 6) {
            isOpen = false;
            System.out.println("本店暂停营业！");
    // 输出今天客户信息
            System.out.println("今天关顾的客户：");
            ArrayList<Customer> s = new ArrayList<>();
            for (Customer customer : customerList) {
                if (customer.getVisitDate().isEqual(LocalDate.now())) {
                    s.add(customer);
                    System.out.println(s);
                }
            }
            if(s.isEmpty()){
                System.out.println("无");
            }
    // 计算利润
            double profit = 0;
            double expense = 0;
            double income = 0;
            for (BaseAnimal animal : todayExpense) {
                expense += animal.getPrice();
            }
            for (BaseAnimal animal : todayIncome) {
                income += animal.getPrice();
            }
            profit = income - expense;
            System.out.println("今天的利润：" + profit);
            todayExpense.clear();
            todayIncome.clear();
        } else {
            System.out.println("还没到时间，不能下班哟ㄟ(▔▽▔)ㄏ");
        }
    }

}
