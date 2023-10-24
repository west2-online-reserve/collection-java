package com.huayu.work02;

import java.time.LocalDate;

/**
 * Text类是系统的测试类
 *
 *该类包含所有的代码的具体实现
 * @author yusiheng
 * date 2023/10/04
 */
public class Test {
    public static void main(String[] args) {
        MyAnimalShop myAnimalShop = new MyAnimalShop();
        myAnimalShop.setClosingTime(LocalDate.of(2023, 9, 27));
        // 设置初始金额
        myAnimalShop.setStoreBalance(500);
        // 设置正在营业
        myAnimalShop.setBusinessInProgress(true);
        Dog charlie = new Dog("charlie", 1, "male", 120, 80, true);
        Cat blue = new Cat("blue", 1, "female", 200, 160);
        Cat tesla = new Cat("tesla", 1, "female", 190, 20);
        Cat max = new Cat("max", 1, "female", 60, 20);
        Dog smowball = new Dog("smowball", 2, "female", 120, 80, true);
        Dog kimi = new Dog("kimi", 1, "male", 3000, 1600, false);
        myAnimalShop.getAnimalsList().add(charlie);
        myAnimalShop.getAnimalsList().add(blue);
        Customer alice = new Customer();
        // 设置顾客名字
        alice.customerName="alice";
        // 设置到店次数
        alice.setArrivalTime(2);
        // 设置离店时间
        alice.latestArrivalTime=(LocalDate.of(2023, 9, 27));
        Customer landon = new Customer();
        landon.customerName="landon";
        landon.setArrivalTime(2);
        landon.latestArrivalTime=LocalDate.of(2023, 9, 27);
        Customer aron = new Customer();
        aron.customerName="aron";
        aron.setArrivalTime(2);
        aron.latestArrivalTime=LocalDate.of(2023, 9, 26);
        myAnimalShop.purchaseNewAnimal(smowball, LocalDate.of(2023, 9, 26));
        myAnimalShop.purchaseNewAnimal(tesla, LocalDate.of(2023, 9, 27));
        myAnimalShop.purchaseNewAnimal(max, LocalDate.of(2023, 9, 27));
        // 用于测试余额不足的情况
        // myAnimalShop.purchaseNewAnimal(kimi, LocalDate.of(2023,9,27));
        // 用于测试能否正常输出当天光顾的顾客的信息，并确保不会输出非当天光顾的顾客名单,并验证利润可否计算
        try {
            myAnimalShop.hospitalizingCustomers(aron, 1, blue);
            myAnimalShop.hospitalizingCustomers(alice, 1, charlie);
            myAnimalShop.hospitalizingCustomers(landon, 3, max);
            myAnimalShop.closureOfBusiness(alice, landon, aron);
        } catch (IndexOutOfBoundsException e) {
            System.out.println();
        }
        // 验证宠物不足的情况
        /*Customer lan = new Customer();
        lan.customerName="lan";
        lan.setArrivalTime(2);
        lan.latestArrivalTime=LocalDate.of(2023, 9, 28);
        Customer yu = new Customer();
        yu.customerName="yu";
        yu.setArrivalTime(2);
        yu.latestArrivalTime=LocalDate.of(2023, 9, 28);
        Customer chen = new Customer();
        chen.customerName="chen";
        chen.setArrivalTime(2);
        chen.latestArrivalTime=LocalDate.of(2023, 9, 27);
        try {
            myAnimalShop.hospitalizingCustomers(alice, 1, charlie);
            myAnimalShop.hospitalizingCustomers(landon, 3, max);
            myAnimalShop.hospitalizingCustomers(aron, 1, blue);
            myAnimalShop.hospitalizingCustomers(lan, 1, tesla);
            myAnimalShop.hospitalizingCustomers(yu, 1, smowball);
            myAnimalShop.hospitalizingCustomers(chen, 1, kimi);
        } catch (IndexOutOfBoundsException e) {
            System.out.println();

        }
        myAnimalShop.closureOfBusiness(alice, landon, aron, lan, yu, chen);*/
        // 用于验证余额的变化，防止余额变化出错导致购买报错信息出错
        // System.out.println(myAnimalShop.storeBalance);
        myAnimalShop.opening();
    }
}