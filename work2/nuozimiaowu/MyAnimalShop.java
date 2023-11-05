package com.sty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MyAnimalShop implements AnimalShop {
    //对于这个宠物店有顾客列表,动物列表,店里的余额
    private ArrayList<Animal> animalArrayList = new ArrayList<>();
    private ArrayList<Customer> customerArrayList = new ArrayList<>();
    private double save;
    private boolean isClose;

    double todaySaving;

    public MyAnimalShop(ArrayList<Animal> animalArrayList, ArrayList<Customer> customerArrayList, double save, boolean isClose) {
        this.animalArrayList = animalArrayList;
        this.customerArrayList = customerArrayList;
        this.save = save;
        this.isClose = isClose;
    }

    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean close) {
        isClose = close;
    }

    @Override
    public void buy(Animal animal) {
        if (animal.price > save) {
            throw new InsufficientBalanceException("余额不足，无法购入动物");
        }
        //更新余额
        save -= animal.price;
        //添加动物到列表
        animalArrayList.add(animal);
        System.out.println("动物已经购入,店里的余额是：" + save + "现在店里的动物列表为：");
        animalArrayList.forEach(o -> {
            System.out.println(o);
        });
        System.out.println("--------------------------------------------");
    }

    @Override
    public void serve(Customer customer) {
        if (isClose == true) {
            System.out.println("今天关门歇业");
            return;
        }
        System.out.println("正在招待客人qaq");
        //判断是不是新人，如果是新的顾客，就把顾客加入到list里面。
        //如果不是新顾客的话，就更新到店次数和到店铺的最新时间；；

        int getTime = 0;
        for (Customer customer1 : customerArrayList) {
            if (customer1.getName() == customer.getName()) {
                customer1.setArriveTime(customer1.getArriveTime() + 1);
                customer1.setTime(customer.getTime());
                getTime++;
            }
        }
        if(getTime == 0){
            customerArrayList.add(customer);
        }


        if (animalArrayList.size() == 0){
            throw new AnimalNotFoundException("商店里没有动物可供挑选");
        }else {
            //这里默认顾客购买第一只动物，模拟购买的情景，应该传入一个购买第几只动物的index参数，但是serve函数接口里没有这个参数。
            //打印动物信息
            System.out.println("顾客购买的动物是：");
            System.out.println(animalArrayList.get(0));
            //入账
            save = save+animalArrayList.get(0).price;
            todaySaving+=animalArrayList.get(0).price;
            //移除动物
            animalArrayList.remove(0);
            //打印当前的店内所有动物信息和店里的余额
            System.out.println("购买成功");
            System.out.println("店里剩余的动物：");
            animalArrayList.forEach(s->{
                System.out.println(s);
            });
            System.out.println("店里的余额：");
            System.out.println(save);
            System.out.println("--------------------------------------------");
        }
    }

    @Override
    public void close() {
        System.out.println("关门歇业睡大觉");
        isClose = true;
        System.out.println("今天招待的客人有：");
        LocalDate localDate=LocalDate.now();
        for (Customer customer : customerArrayList) {
            if(customer.getTime().equals(localDate)){
                System.out.println(customer);
            }
        }
        System.out.println("今天一整天的营收是："+todaySaving);
    }


}