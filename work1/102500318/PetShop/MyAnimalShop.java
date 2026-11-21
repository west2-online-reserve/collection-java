package oop.PetShop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyAnimalShop implements AnimalShop{
    private double balance;                    // 余额
    private double todayRevenue;         // 当天卖出总收入
    private double todayCost;            // 当天买入总成本
    private List<Animal> animals;             // 动物列表
    private List<Customer> customers;          // 顾客列表
    private boolean isClosed;// 是否歇业
    public MyAnimalShop(double balance,List<Animal> animals) {
        this.balance = balance;
        this.animals = animals;
        this.customers = new ArrayList<>();
        this.isClosed = false;
        this.todayRevenue = 0;
        this.todayCost = 0;

    }
    @Override
    public void buyAnimal(Animal animal) {
        if(isClosed){return;}
        if(animal.getPrice() > balance){
            throw new InsufficientBalanceException("余额不足");//throw 的作用和 return 类似：立刻结束当前方法
        }
        balance -= animal.getPrice();
        todayCost +=  animal.getPrice();
        animals.add(animal);
    }

    @Override
    public void serveCustomer(Customer customer) {
        if(isClosed){return;}
        if(animals.isEmpty()) {
            throw new AnimalNotFoundException("店铺没有动物");
        }
        boolean exit = false;
        for(int i = 0; i < customers.size(); i++){
            if( Objects.equals(customer.getName(),customers.get(i).getName() ) ){
                exit = true;
                customers.get(i).increaseCount();
                customers.get(i).setNewDate(LocalDate.now());
                break;
            }
        }
        if(!exit) {
            customer.setNewDate(LocalDate.now());
            customers.add(customer);
        }


        balance += animals.getLast().getPrice();
        todayRevenue += animals.getLast().getPrice();
        System.out.println(animals.getLast());
        animals.remove(animals.getLast());
    }


    @Override
    public void closed() {
        isClosed = true;
        LocalDate today = LocalDate.now();
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).getNewDate().isEqual(today)){
                System.out.println(customers.get(i));
            }
        }
        System.out.println(todayRevenue-todayCost);
    }

}
