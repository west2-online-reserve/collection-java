package com.animalshop.model;

import com.animalshop.exception.AnimalNotFoundException;
import com.animalshop.exception.InsufficientBalanceException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
import java.util.Iterator;
public class MyAnimalShop implements AnimalShop {
    protected double balance;
    protected double previousBalance;
    protected List<Animal> animalList = new ArrayList<>();
    protected List<Customer> customerList = new ArrayList<>();
    protected List<Customer> todayCustomerList = new ArrayList<>();
    protected boolean isOpen;
    public MyAnimalShop(double balance) {
        this.balance = balance;
        previousBalance = balance;
        this.isOpen = true;
        System.out.println("创建宠物店，初始余额：" + balance);
        System.out.println("每只宠物将以比进价高100元的价格出售");
    }
    @Override
    public void purchaseAnimal(Animal animal){
        if (balance >= animal.price) {
            animalList.add(animal);
            balance -= animal.price;
            System.out.println("已买入动物：" + animal);
            animal.price += 100;
        }
        else {
            throw new InsufficientBalanceException("余额不足，无法购入：" + animal);
        }
    }
    @Override
    public void serveCustomer(Customer customer, String animalName){
        Iterator<Animal> animalIterator = animalList.iterator();
        if (isOpen){
            if(!customerList.contains(customer)){
                customerList.add(customer);
            }
            customer.setTimes();
            customer.setDate();
            if (animalList.isEmpty()){
                throw new AnimalNotFoundException("店内没有动物可出售");
            }
            else{
                double balanceBefore = balance;
                while (animalIterator.hasNext()){
                    Animal animal = animalIterator.next();
                    if (animal.name.equals(animalName)){
                        System.out.println("出售动物:" + animal);
                        balance += animal.price ;
                        animalIterator.remove();
                        break;
                    }

                }
                if (balanceBefore == balance){
                    throw new AnimalNotFoundException("店内没有你想要的动物");
                }
            }
        }
        else {
            System.out.println("宠物店已歇业,无法出售动物");
        }
    }
    @Override
    public void serveCustomer(Customer customer){
        if (isOpen){
            if(!customerList.contains(customer)){
                customerList.add(customer);
            }
            customer.setTimes();
            customer.setDate();
            if (animalList.isEmpty()){
                throw new AnimalNotFoundException("店内没有动物可出售");
            }
            else{
                System.out.println("欢迎光临，以下是本店的动物列表：");
                for(Animal animal : animalList){
                    System.out.println(animal);
                }
            }
        }
        else{
            System.out.println("宠物店已歇业,无法出售动物");
        }
    }

    @Override
    public void closeShop(){
        List<Customer> tempTodayCustomerList = new ArrayList<>();
        if (isOpen){
            System.out.println("宠物店今日歇业");
            for (Customer customer : customerList){
                if(customer.getLastDate().equals(LocalDate.now())){
                    tempTodayCustomerList.add(customer);
                }
            }
            todayCustomerList.addAll(tempTodayCustomerList);
            System.out.println("今日到访顾客名单：");
            for(Customer customer : todayCustomerList){
                System.out.println("[" + customer + "]");
            }
            todayCustomerList.clear();
            System.out.println("本次营业利润:" + (balance - previousBalance));
            previousBalance = balance;
            isOpen = false;
        }
        else{
            System.out.println("宠物店已歇业，无法再次进行歇业操作");
        }
    }
    public void openShop(){
        isOpen = true;
        System.out.println("宠物店开业");
    }
}
