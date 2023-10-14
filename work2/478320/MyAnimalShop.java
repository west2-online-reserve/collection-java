package com.huayu.work02;

import java.time.LocalDate;
import java.util.ArrayList;


public class MyAnimalShop implements AnimalShop {
//这是实现接口的类，也是项目的核心，我将在每个我认为需要注解的地方再次添加我的理解
    double storeBalance;//初始有的钱
    ArrayList<Animal> animalsList = new ArrayList<Animal>();//动物列表
    ArrayList<Customer> customersList = new ArrayList<Customer>();//顾客列表
    ArrayList<Double> soldOnClosedDays = new ArrayList<Double>();//这个集合用于储存当天出口动物的赚的钱，在后期将他遍历相加可以用于计算利润
    ArrayList<Double> importOnClosedDays = new ArrayList<Double>();//这个集合用于存储当天进口的动物的消耗的钱，在后期将它遍历相加可以用于计算利润
    LocalDate closingTime;//关店时间
    boolean businessInProgress;
/*这是购买动物方法，我输入购买的动物后，会把该动物加入我的动物列表，并消耗一部分我原有的钱，如果购买的时间是歇业计算利润的时间
这个动物的购买价格就会进入利润计算用的进口列表，如果已经没钱就会抛出钱不够的异常并捕获，输出异常信息*/
    @Override
    public void purchaseNewAnimal(Animal animal, LocalDate importTime) {
        if (storeBalance < animal.animalImportPrice) {
            try {
                throw new InsufficientBalanceException();
            } catch (InsufficientBalanceException e) {
                System.out.println(e);
            }
        }
        storeBalance = storeBalance - animal.animalImportPrice;
        animalsList.add(animal);
        if (importTime.equals(closingTime)) {
            importOnClosedDays.add(animal.animalImportPrice);
        }
    }
/*这是招待顾客方法，如果已经没有动物可以购买，就会抛出无宠物可购买异常，虽然他啥都没买，但是他只要在当天关顾了鄙店，小店就会把他的信息
列入顾客列表，招待顾客会在动物列表中删除对应的动物，并增加我的总资金，如果顾客是在歇业当天购买的宠物，购买得到的钱，就会进入出口列表，
进行利润计算*/
    @Override
    public void hospitalizingCustomers(Customer customer, int whichOneToPurchase, Animal animal) throws IndexOutOfBoundsException {
        if (animalsList.size() == 0) {
            try {
                throw new AnimalNotFountException();
            } catch (AnimalNotFountException e) {
                System.out.println(e);
            }
        }
        if (animalsList.size() <= 0) {
            throw new IndexOutOfBoundsException();
        }
        if (animalsList.size() > 0) {
            System.out.println(animal.toString());
            storeBalance = storeBalance + animal.animalPrice;
            animalsList.remove(whichOneToPurchase - 1);
        }
        customersList.add(customer);
        if (customer.latestArrivalTime.equals(closingTime) && animalsList.size() > 0) {
            soldOnClosedDays.add(animal.animalPrice);
        }
    }
/*这是歇业方法，里面有两个参数，一个revenue来接收所有的当天进口花费的钱，一个expenditure来接收所有当天出口赚的钱
 ，用这两个参数来计算利润，同时还有输出当天关顾顾客的功能*/
    @Override
    public void closureOfBusiness(Customer... customer) {
        for (int i = 0; i < customer.length; i++) {
            if (customer[i].latestArrivalTime.equals(closingTime)) {
                System.out.println(customer[i].toString());
            }

        }
        double revenue = 0;
        for (int j = 0; j < importOnClosedDays.size(); j++) {
            revenue = revenue + importOnClosedDays.get(j);
        }
        double expenditure = 0;
        for (int j = 0; j < soldOnClosedDays.size(); j++) {
            expenditure = expenditure + soldOnClosedDays.get(j);

        }
        double profit = expenditure - revenue;
        System.out.println(profit);

    }
}
