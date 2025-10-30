package com.west2online.petshop;

import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {
    private double money;
    private boolean isOpen;
    private double profit;
    private ArrayList<Animal> animalsList;
    private ArrayList<Customer> customersList;
    private ArrayList<Customer> todayCustomerList;

    public MyAnimalShop() {

    }

    public MyAnimalShop(double money) {
        this.money = money;
        this.isOpen = true;
        this.animalsList = new ArrayList<>();
        this.customersList = new ArrayList<>();
        this.todayCustomerList = new ArrayList<>();
        this.profit = 0;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public ArrayList<Animal> getAnimalsList() {
        return new ArrayList<>(animalsList);
    }

    public ArrayList<Customer> getCustomersList() {
        return new ArrayList<>(customersList);
    }


    //方法containsAnimal判断顾客要购买的动物是否在列表中
    public boolean containsAnimal(String name) {
        for (int i = 0; i < animalsList.size(); i++) {
            Animal animal = animalsList.get(i);
            String aName = animal.getName();
            if (aName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    //方法containsCustomers判断顾客是否在列表中
    public boolean containsCustomers(String name) {
        for (int i = 0; i < customersList.size(); i++) {
            Customer customer = customersList.get(i);
            String cName = customer.getName();
            if (cName.equals(name)) {
                return true;
            }
        }
        return false;
    }

    //购入动物
    @Override
    public void buyNewAnimals(Animal animal) {
        if (animal.getPrice() > money) {
            throw new InsufficientBalanceException("当前余额只剩" + money + "元,无法购买!");
        }
        money = money - animal.getPrice();
        animalsList.add(animal);
        System.out.println("成功购买" + animal.toString());

    }

    //招待客户
    @Override
    public void serveCustomer(Customer customer, String petName) {
        if (!isOpen) {
            System.out.println("本宠物店已关门,请下次再来!");
            return;
        }

        if (animalsList.isEmpty()) {
            throw new AnimalNotFoundException("店内没有动物可出售！");
        }

        //判断顾客要购买的动物是否在列表中
        boolean animalFlag = containsAnimal(petName);
        if (!animalFlag) {
            throw new AnimalNotFoundException("店内没有名字叫" + petName + "的动物!");
        }

        //卖出动物,同时移出列表

        Animal sellAnimal = null;
        double sellPrice = 0;

        for (int i = 0; i < animalsList.size(); i++) {
            Animal animal = animalsList.get(i);
            String aName = animal.getName();
            if (aName.equals(petName)) {
                sellAnimal = animalsList.get(i);
                sellPrice = sellAnimal.getPrice() * 1.5;
                animalsList.remove(sellAnimal);
                profit += (sellPrice - sellAnimal.getPrice());
                money += profit;
                break;
            }
        }

        //判断顾客是否在列表中,如果没有,将顾客添加到列表
        boolean customersFlag = containsCustomers(customer.getName());
        if (!customersFlag) {
            customersList.add(customer);
        }
        customer.addVisit();

        boolean customersTodayFlag = containsCustomers(customer.getName());
        if (!customersTodayFlag) {
            todayCustomerList.add(customer);
        }

        System.out.println(customer.getName() + "花费了" + sellPrice + "成功购买了" + sellAnimal.getName());
        System.out.println("宠物信息为" + sellAnimal.toString());

    }

    //关门大吉
    @Override
    public void closeShop() {
        isOpen = false;
        System.out.println("宠物店关门!");

        //打印今天的顾客列表
        if (todayCustomerList.isEmpty()) {
            System.out.println("今天无人光顾!");
        } else {
            for (int i = 0; i < todayCustomerList.size(); i++) {
                System.out.println(todayCustomerList.get(i));
            }
        }

        //统计今天的钱
        System.out.println("今天赚了" + profit + "元");
        System.out.println("目前余额:" + money + "元");
        profit = 0;
        todayCustomerList.clear();
    }

    //开门大吉
    public void openShop() {
        isOpen = true;
        System.out.println("宠物店开门!");
    }

}
