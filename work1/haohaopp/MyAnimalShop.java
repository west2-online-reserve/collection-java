package chongwudian;

import  java.time.LocalDate;
import  java.util.ArrayList;
import  java.util.Scanner;
public class MyAnimalShop implements AnimalShop {
    private  double money;
    private ArrayList<Animal> animals;
    private  ArrayList<Customer> customers;
    private  boolean isOpen;
    private double profit=0;

    public MyAnimalShop(double money, ArrayList<Animal> animals, ArrayList<Customer> customers) {
        this.money = money;
        this.animals = animals;
        this.customers = customers;
    }

    @Override
    public void buyAnimal(Animal a) {
        double price=a.getPrice();
    if(money>price){
        animals.add(a);
        System.out.println("当前余额："+money);
        money=money-price;
        System.out.println("购买成功！");
        System.out.println("剩余余额："+money);
    }
    else{
        throw new InsufficientBalanceException("余额不足！购买失败！");
    }

    }

    @Override
    public void comeCustomer(Customer c) {
        boolean flag=false;
    for(int i=0;i<customers.size();i++){
        if(c.getName().equals(customers.get(i).getName())){
            flag=true;
            customers.get(i).setCount(customers.get(i).getCount()+1);
        }
    }
    if(!flag){
        c.setCount(1);
        customers.add(c);
    }
    Scanner sc=new Scanner(System.in);
        System.out.println("欢迎！"+c.getName());
    System.out.println("请输入要购买宠物的类型：猫请输入1，狗输入2，鸟输入3");
    int animal=sc.nextInt();
    while(animal!=1&&animal!=2&&animal!=3){
        System.out.println("请输入有效的数字！");
        animal=sc.nextInt();
    }
    boolean flag2=false;
    for(int i=0;i< animals.size();i++){
            if(animals.get(i).getKind()==animal){
                System.out.println(animals.get(i).toString());
                flag2=true;
            }
        }
    if(!flag2){
        throw new AnimalNotFountException("该宠物卖完了！");
    }
    System.out.println("输入要购买宠物的名字：");
    sc.nextLine();
    String buyname=sc.nextLine();
    for(int i=0;i< animals.size();i++){
            if(animals.get(i).getName().equals(buyname)){
                System.out.println("成功卖出！");
                animals.get(i).toString();
                profit+=animals.get(i).getPrice();
                animals.remove(i);
            }
        }
    }

    @Override
    public void closed(LocalDate date) {
        System.out.println("歇业！！");
        System.out.println("今天的顾客有：");
    for(int i=0;i<customers.size();i++){
        if(date.equals(customers.get(i).getTime())){
            System.out.println(customers.get(i).toString());
        }
    }
        System.out.println("今天的利润是："+profit);
        money+=profit;
        profit=0;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
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

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
