package com.huayu.work02;

/**
 * MyAnimal是AnimalShop的实现类，代表宠物店运行的核心
 * <p>
 * 该类包含具体的购买宠物，招待顾客，歇业的实现方法
 *
 * @author yusiheng
 * @date 2023/10/04
 */

import java.time.LocalDate;
import java.util.ArrayList;


public class MyAnimalShop implements AnimalShop {

    protected double storeBalance;
    protected ArrayList<Animal> animalsList = new ArrayList<>();
    protected ArrayList<Customer> customersList = new ArrayList<>();
    protected ArrayList<Double> soldOnClosedDays = new ArrayList<>();
    protected ArrayList<Double> importOnClosedDays = new ArrayList<>();
    protected LocalDate closingTime;
    protected boolean businessInProgress;

    public double getStoreBalance() {
        return storeBalance;
    }

    public void setStoreBalance(double storeBalance) {
        if (storeBalance >= 0) {
            this.storeBalance = storeBalance;
        } else {
            System.out.println("初始余额有误");
        }
    }

    public ArrayList<Animal> getAnimalsList() {
        return animalsList;
    }

    public void setAnimalsList(ArrayList<Animal> animalsList) {
        this.animalsList = animalsList;
    }

    public ArrayList<Customer> getCustomersList() {
        return customersList;
    }

    public void setCustomersList(ArrayList<Customer> customersList) {
        this.customersList = customersList;
    }

    public ArrayList<Double> getSoldOnClosedDays() {
        return soldOnClosedDays;
    }

    public void setSoldOnClosedDays(ArrayList<Double> soldOnClosedDays) {
        this.soldOnClosedDays = soldOnClosedDays;
    }

    public ArrayList<Double> getImportOnClosedDays() {
        return importOnClosedDays;
    }

    public void setImportOnClosedDays(ArrayList<Double> importOnClosedDays) {
        this.importOnClosedDays = importOnClosedDays;
    }

    public LocalDate getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalDate closingTime) {
        this.closingTime = closingTime;
    }

    public boolean isBusinessInProgress() {
        return businessInProgress;
    }

    public void setBusinessInProgress(boolean businessInProgress) {
        this.businessInProgress = businessInProgress;
    }

    /**
     * 这是购买动物方法，可以购买动物，购买后会把该动物加入我的动物列表，并消耗一部分我原有的钱，如果购买的时间是歇业计算利润的时间，这个动物的购买价格就会进入利润计算用的进口列表，如果已经没钱就会抛出钱不够的异常并捕获，输出异常信息
     *
     * @param animal     动物
     * @param importTime 进口时间
     * @return null
     * @throws InsufficientBalanceException 已有的钱不足以支付该宠物的进价
     */
    @Override
    public void purchaseNewAnimal(Animal animal, LocalDate importTime) {
        if (businessInProgress == false) {
            System.out.println("宠物店未在营业");
            return;
        }
        try {
            if (storeBalance >= animal.getAnimalImportPrice()) {
                System.out.println("宠物店购进宠物成功");
            } else {
                throw new InsufficientBalanceException();
            }
        } catch (InsufficientBalanceException e) {
            System.out.println(e);
        }
        // 计算利润
        storeBalance = storeBalance - animal.getAnimalImportPrice();
        // 动物列表添加动物
        animalsList.add(animal);
        // 如果购买宠物时间和当天时间相同，花销列表增这个宠物的进口价
        if (importTime.equals(closingTime)) {
            importOnClosedDays.add(animal.getAnimalImportPrice());
        }
    }

    /**
     * 这是招待顾客方法，可以出售宠物，并把当天光顾的顾客加入顾客列表，赚取一定的钱，如果购买的时间是歇业计算利润的时间，这个动物的出售价格就会进入利润计算用的进口列表，如果已经没有宠物可购买就会抛出钱不够的异常并捕获，输出异常信息
     *
     * @param customer           购买动物的顾客
     * @param whichOneToPurchase 购买哪一只动物
     * @param animal             动物
     * @throws IndexOutOfBoundsException 店内已经没有宠物
     */
    @Override
    public void hospitalizingCustomers(Customer customer, int whichOneToPurchase, Animal animal) throws IndexOutOfBoundsException {
        if (businessInProgress == false) {
            System.out.println("宠物店未在营业");
            return;
        }
        try {
            if (animalsList.size() != 0) {
                System.out.println("宠物出售成功");
            } else {
                throw new AnimalNotFountException();
            }
        } catch (AnimalNotFountException e) {
            System.out.println(e);
            return;
        }
        // 输出顾客购买的宠物信息
        System.out.println("售出的宠物为："+animal.toString());
        storeBalance = storeBalance + animal.getAnimalPrice();
        // 从动物列表移除动物
        animalsList.remove(whichOneToPurchase - 1);
        // 顾客列表增加顾客
        customersList.add(customer);
        // 如果购买顾客光临时间和当天时间相同，售出列表增这个宠物的出口价
        if (customer.getLatestArrivalTime().equals(closingTime) && animalsList.size() > 0) {
            soldOnClosedDays.add(animal.getAnimalPrice());
        }
    }

    /**
     * 这是开业方法，使宠物店营业
     */
    @Override
    public void opening() {
        this.setBusinessInProgress(true);
    }

    /**
     * 这是歇业方法，计算当天利润，关闭宠物店，同时还有输出当天关顾顾客的功能
     *
     * @param customer 顾客
     */
    @Override
    public void closureOfBusiness(Customer... customer) {
        if (businessInProgress == false) {
            System.out.println("宠物店未在营业");
            return;
        }
        for (int i = 0; i < customer.length; i++) {
            if (customer[i].getLatestArrivalTime().equals(closingTime)) {
                System.out.println("今日光临的顾客："+customer[i].toString());
            }
        }
        double revenue = 0;
        // 计算当天总花销
        for (int j = 0; j < importOnClosedDays.size(); j++) {
            revenue = revenue + importOnClosedDays.get(j);
        }
        double expenditure = 0;
        // 计算当天总盈利
        for (int j = 0; j < soldOnClosedDays.size(); j++) {
            expenditure = expenditure + soldOnClosedDays.get(j);
        }
        // 计算利润
        double profit = expenditure - revenue;
        System.out.println("今日的利润为："+profit);
        this.setBusinessInProgress(false);

    }
}
