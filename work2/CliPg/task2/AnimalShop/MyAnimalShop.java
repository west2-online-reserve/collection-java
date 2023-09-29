package com.PeanutJava.task2;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import java.util.Scanner;

import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    ArrayList<Animal> AnimalList = new ArrayList<>();
    ArrayList<Customer> CustomerList = new ArrayList<>();
    private boolean isSale;
    private double profit = 0;

    public MyAnimalShop(double balance, boolean isSale) {
        this.balance = balance;
        this.isSale = isSale;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Animal> getAnimalList() {
        return AnimalList;
    }

    public void setAnimalList(ArrayList<Animal> animalList) {
        AnimalList = animalList;
    }

    public ArrayList<Customer> getCustomerList() {
        return CustomerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        CustomerList = customerList;
    }

    public boolean isSale() {
        return isSale;
    }

    public void setSale(boolean sale) {
        isSale = sale;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    //宠物店购买一定数量的宠物
    @Override
    public void Buy(Animal a, int num) {
        try {
            double BuyTotal = num * a.getPrice();
            if (BuyTotal > this.balance) {
                throw new InsufficientBalanceException("余额不足");
            }
            this.balance -= BuyTotal;
            this.profit -= BuyTotal;
            for (int i = 0; i < num; i++) {
                AnimalList.add(a);
            }
            if(num>0) {
                System.out.println("成功购买" + num + "只" + a.getSpecies()+a.toString());
            }
        } catch (InsufficientBalanceException i) {
            System.out.println(i.toString());
        }
    }

    //顾客购买一定数量的宠物
    @Override
    public void Serve(Customer c) {
        //每接待一个顾客前，判断宠物列表是否为空，决定是否打烊
        if(this.getAnimalList().size()==0){
            isSale=false;
        }
        if(!isSale){
            System.out.println("宠物店已经打烊啦！欢迎下次光临！\n");
        }else{
            System.out.println("----------------------------------------");
            System.out.println("欢迎光临！");
            System.out.println("这里有中华田园犬和猫猫");
            for (int i = 0; i < AnimalList.size(); i++) {
                Animal animal=AnimalList.get(i);
                System.out.println(animal);
            }
            //创建动物类型，也可方便添加其他动物
            ChineseRuralDog dog = new ChineseRuralDog(null, 0, null, false);
            Cat cat = new Cat(null, 0, null);
            int num01 = purchase(c,dog);
            int num02 = purchase(c,cat);
            //根据购买数量提供提示词
            if (num02 == 0) {
                System.out.println("谢谢惠顾！");
            }
            if(num01>0||num02>0){
                System.out.println("----------------------------------------");
            } else if (num01==0&&num02==0) {
                System.out.println("欢迎下次光临");
                System.out.println("----------------------------------------");
            }

            int times = c.getNums()+1;
            c.setNums(times);
            CustomerList.add(c);
        }
    }
    //购买动物类型
    public int purchase(Customer c,Animal animal){
        int num0=0;
        boolean flag = false;
        while (!flag) {
            System.out.println("请问"+c.getName()+"需要几只"+animal.getSpecies()+"呢？");
            Scanner s1 = new Scanner(System.in);
            int num1 = s1.nextInt();
            if (num1 > 0) {
                num0 = num1;
                flag = Sell(animal, num1);
            } else if (num1 == 0) {
                num0 = num1;
                flag = true;

            }else{
                System.out.println("请输入大于等于0的数字哦！");
            }
        }
        return num0;
    }
    //卖宠物的过程
    public boolean Sell(Animal a, int num) {
        boolean flag = true;
        try {
            //计数某宠物的数量
            int count1 = 0;
            for (int i = 0; i <AnimalList.size(); i++) {
                if (a.getSpecies()==AnimalList.get(i).getSpecies()) {
                    count1++;
                }
            }
            if (num > count1) {
                if(count1==0){
                    throw new AnimalNotFountException("当前暂无"+a.getSpecies());
                }else if(count1>0){
                    flag = false;
                    throw new AnimalNotFountException("当前只有"+count1+"只"+a.getSpecies());
                }
            }
            //贩卖总金额
            double SellTotal = a.getPrice()*num;
            this.balance+= SellTotal;
            this.profit += SellTotal;
            System.out.println("感谢购买" + num + "只" + a.getSpecies());
            int count2=0;
            //将被购买的宠物移除列表
            while (count2!=num) {
                for (int j = 0; j < AnimalList.size(); j++) {
                    if (a.getSpecies() == AnimalList.get(j).getSpecies()) {
                        AnimalList.remove(j);
                        count2++;
                        break;
                    }
                }
            }

        }catch (AnimalNotFountException e) {
            System.out.println(e.toString());
        }
        return flag;
    }
    @Override
    public  void isClosed () {
            this.isSale = false;
            for (int i = 0; i < CustomerList.size(); i++) {
                System.out.println("第" + (i + 1) + "位顾客的信息为 " + CustomerList.get(i).toString());
            }
            System.out.println("今日的利润为:" + this.profit);
            System.out.println("宠物店余额为："+getBalance());
            System.out.println("宠物店还有：");
            for(int j = 0; j < AnimalList.size(); j++){
                System.out.println(AnimalList.get(j).toString());
            }
        }




}