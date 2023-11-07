package com.west2.javawork2;
//变量:
//店的余额double
//一个存放动物的列表 (使用ArrayList、LinkedList或其他你喜欢的List实现)
//一个顾客列表留作纪念
//是否正在营业


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    private double profit;
    private double balance;
    private boolean isOpened;

    // 获取与设置余额参数
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    //获取与设置正在营业的参数
    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }


    //获取与设置利润参数

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    //创建宠物列表
    protected List<Animal> animalArrayList = new ArrayList<>();

    //获取与设置宠物列表
    public List<Animal> getAnimalArrayList() {
        return animalArrayList;
    }

    public void setAnimalArrayList(List<Animal> animalArrayList) {
        this.animalArrayList = animalArrayList;
    }


    //创建顾客列表
    protected List<Customer> customerArrayList = new ArrayList<>();
    //获取与设置顾客列表

    public List<Customer> getCustomerArrayList() {
        return customerArrayList;
    }

    public void setCustomerArrayList(List<Customer> customerArrayList) {
        this.customerArrayList = customerArrayList;
    }


    //无参构造
    public MyAnimalShop() {
    }

    //有参构造


    public MyAnimalShop(double profit, double balance, boolean isOpened,ArrayList<Animal> animalArrayList,ArrayList<Customer> customerArrayList) {
        this.profit = profit;
        this.balance = balance;
        this.isOpened = isOpened;
        this.animalArrayList = (animalArrayList == null) ? new ArrayList<>() : new ArrayList<>(animalArrayList);
        this.customerArrayList = (customerArrayList == null) ? new ArrayList<>() : new ArrayList<>(customerArrayList);
    }

    @Override
    /*
         买入一只动物，记得在动物列表中添加，
     */
    public void purchase(Animal animal) {
        if (animal.getPrice() > this.balance) {
            throw new InsufficientBalanceException(animal, balance,"买不起呢，太贵了！");
        } else {
            balance -= animal.getPrice();
            animalArrayList.add(animal);
            System.out.println("已购买" + animal.toString() + ",余额为：" + balance);
        }
    }
    /*
    招待客户 -> 接受客户参数，在顾客列表中加入新顾客，
     */

    @Override
    public void receiveCustomer(Customer customer) {

        //将顾客添加进列表
        customerArrayList.add(customer);

        //顾客到店次数增加
        customer.numberVisits++;

        //更新顾客到店时间
        customer.setLatestTime(LocalDate.now());
        if (!this.isOpened) {
            System.out.println("请下次光临，本店已关门。");
            return;
        }
        //询问顾客是否要购买动物
        Scanner s1 = new Scanner(System.in);
        System.out.println("欢迎光临本店，如您需购买宠物请输入“1”，否则输入“0”。");
        int type = s1.nextInt();
        if (type !=1) {
            System.out.println("期待您下次光临本店");
            return;
        }
        //如果店内没有动物，抛出AnimalNotFoundException。
        if(animalArrayList.isEmpty()){
            throw new AnimalNotFountException("非常抱歉没有宠物了，下次再来吧。");
        }else {
            //输入想要购买的动物
            System.out.println("请输入想要购买的动物的编号：");
            int number = s1.nextInt();
            if (number <= animalArrayList.size() && number > 0) {
                //通过toString输出动物信息
                System.out.println(animalArrayList.get(number));
                //并把钱入账
                this.balance += animalArrayList.get(number).price;
                this.profit +=100;
                //将动物移除列表
                animalArrayList.remove(number);
            } else {
                throw new AnimalNotFountException("非常抱歉，您所购买的宠物编号不存在。");
            }
        }
        System.out.println("谢谢惠顾，欢迎下次再来！");
    }
    /*
      歇业
     */

    @Override
    public void close() {
        this.isOpened = false;
        //输出当天光顾的客户的列表信息
        for(Customer customer : customerArrayList){
            if (LocalDate.now().equals(customer.getLatestTime())) {
                System.out.println(customer);
            }
        }
        //输出一天的利润
        System.out.println("今天总利润" +this.profit);
    }

     /*
       开业
     */
    @Override
    public void open() {
        this.isOpened = true;
        //将利润清零
        this.profit = 0.0;
    }
}

