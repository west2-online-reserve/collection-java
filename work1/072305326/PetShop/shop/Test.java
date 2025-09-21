package com.cyx.Test;

import com.cyx.Amimals.*;
import com.cyx.Exception.AnimalNotFoundException;
import com.cyx.Exception.InsufficientBalanceException;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        System.out.println("===== 宠物店系统测试 =====\n");
        functionTest();
        System.out.println("\n 按下Enter键继续下一个测试...");
        new Scanner(System.in).nextLine();
        functionTest2();
        System.out.println("\n 按下Enter键继续下一个测试...");
        new Scanner(System.in).nextLine();
        functionTest3();
        //  System.out.println("\n 按下Enter键继续下一个测试...");
        //  new Scanner(System.in).nextLine();
        //  functionTest4();
        System.out.println("\n 按下Enter键结束测试...");
        new Scanner(System.in).nextLine();

        completeTest();
    }

    private static void completeTest() {
        System.out.println("\n[测试场景5： 综合测试]\n");
        try {
            // 初始化
            MyAnimalShop shop = new MyAnimalShop(2000.0);
            System.out.println("宠物店创建成功，初始余额：" + shop.getBalance() + "元");
            System.out.println("开始买入动物");
            // 狗
            ChineseRuralDog dog1 = new ChineseRuralDog("旺财",2,"雄",true);
            ChineseRuralDog dog2 = new ChineseRuralDog("来福",3,"雌", true);
            ChineseRuralDog dog3 = new ChineseRuralDog("大黄",4,"雄",true);
            shop.buyAnimal(dog1);
            shop.buyAnimal(dog2);
            shop.buyAnimal(dog3);
            System.out.println("\n=====成功买入三只中华田园犬=====\n");
            // 猫
            Cat cat1 = new Cat("咪咪",1,"雌");
            Cat cat2 = new Cat("小花",2,"雄");
            Cat cat3 = new Cat("小黑",3,"雌");
            shop.buyAnimal(cat1);
            shop.buyAnimal(cat2);
            shop.buyAnimal(cat3);
            System.out.println("\n=====成功买入三只猫咪=====\n");

            // 鸟
            Bird bird1 = new Bird("小黄", 1, "雌", "金丝雀");
            Bird bird2 = new Bird("翠翠",2,"雄","鹦鹉");
            Bird bird3 = new Bird("飞飞",3,"雄","鸽子");
            Bird bird4 = new Bird("彩彩",4,"雌","孔雀");
            shop.buyAnimal(bird1);
            shop.buyAnimal(bird2);
            shop.buyAnimal(bird3);
            shop.buyAnimal(bird4);
            System.out.println("\n=====成功买入四只鸟=====\n");

            // 顾客
            System.out.println("\n开始招待顾客测试...\n");
            Customer customer1 = new Customer("张三");
            Customer customer2 = new Customer("李四");
            Customer customer3 = new Customer("王五");
            Customer customer4 = new Customer("赵六");
            // 第一位顾客第一次
            shop.serverCustomer(customer1);

            // 第二位顾客第一次
            shop.serverCustomer(customer2);
            // 第三位顾客第一次
            shop.serverCustomer(customer3);
            // 第一位顾客第二次
            shop.serverCustomer(customer1);
            // 第三位顾客第二次
            shop.serverCustomer(customer3);
            // 第二位顾客第二、三次
            shop.serverCustomer(customer2);
            shop.serverCustomer(customer2);
            // 第四位顾客
            shop.serverCustomer(customer4);
            shop.serverCustomer(customer4);
            shop.serverCustomer(customer4);
            // 测试没有动物售卖时是否报错
//            shop.serverCustomer(customer4);

            // ======================================
            // 测试歇业功能
            System.out.println("\n 开始歇业测试\n");
            shop.close();
            System.out.println("\n宠物店成功歇业\n");
            System.out.println("所有测试均已完成");

        }catch (InsufficientBalanceException | AnimalNotFoundException e){
            System.out.println("综合测试，成功捕获异常：" + e.getMessage());
        }
    }

    private static void functionTest4() {
        System.out.println("\n[测试场景4： 关门后购买动物和招待顾客测试]\n");
        try {
            MyAnimalShop shop = new MyAnimalShop(1000.0);
            shop.close();
            // 尝试购买动物
            ChineseRuralDog dog = new ChineseRuralDog("小狗1", 8, "雄", true);
            shop.buyAnimal(dog);

            // 尝试关门后招待顾客
            Customer customer = new Customer("张三");
            shop.serverCustomer(customer);
        }catch (InsufficientBalanceException | AnimalNotFoundException e){
            System.out.println("关门后购买动物和招待顾客测试，成功捕获异常：" + e.getMessage());
        }
    }

    private static void functionTest3() {
        System.out.println("\n[测试场景3： 动物NotFound测试]\n");

        try {
            MyAnimalShop shop = new MyAnimalShop(1000.0);
            Bird bird1 = new Bird("小鸟1", 8, "雌", "百合鸟");
            Bird bird2= new Bird("小鸟2", 8, "雌", "麻雀");

            shop.buyAnimal(bird1);
            shop.buyAnimal(bird2);
            // 第一个顾客购买小鸟
            Customer customer1 = new Customer("张三");
            shop.serverCustomer(customer1);
            // 第二个顾客再次尝试购买小鸟
            Customer customer2 = new Customer("李四");
            shop.serverCustomer(customer2);
            // 第三个顾客再次尝试购买小鸟
            Customer customer3 = new Customer("王五");
            shop.serverCustomer(customer3);
        }catch (AnimalNotFoundException e){
            System.out.println("宠物店动物NotFound测试，成功捕获异常：" + e.getMessage());
        }
    }

    private static void functionTest2() {
        System.out.println("\n[测试场景2： 余额不足测试]\n");

        try{
            // 初始化宠物店，初始资金 200 元  （不足以购买所有动物）
            MyAnimalShop shop = new MyAnimalShop(200.0);
            System.out.println("初始资金： " + shop.getBalance());

            // 尝试购买多个动物
            ChineseRuralDog dog1 = new ChineseRuralDog("小狗1", 8, "雄", true);
            ChineseRuralDog dog2 = new ChineseRuralDog("小狗2", 9, "雄", true);
            Cat cat = new Cat("小猫1", 8, "雌");
            shop.buyAnimal(dog1);
            shop.buyAnimal(dog2);
            shop.buyAnimal(cat);
        }catch (InsufficientBalanceException e){
            System.out.println("宠物店余额不足测试，成功捕获异常：" + e.getMessage());
        }
    }

    private static void functionTest(){
        System.out.println("[测试场景1： 基本功能测试]\n");
        try {
            // 初始化一个宠物店 ，初始资金1000元
            MyAnimalShop shop = new MyAnimalShop(1000.0);
            System.out.println("宠物店已开启，初始资金为：" + shop.getBalance());
            // 买入动物
            ChineseRuralDog dog = new ChineseRuralDog("小狗1", 8, "雄", true);
            Cat cat = new Cat("小猫1", 8, "雌");
            Bird bird = new Bird("小鸟1", 8, "雌", "小");
            shop.buyAnimal(dog);
            shop.buyAnimal(cat);
            shop.buyAnimal(bird);
            // 定义顾客1
            Customer customer1 = new Customer("张三");
            shop.serverCustomer(customer1);
            // 定义顾客2
            Customer customer2 = new Customer("李四");
            shop.serverCustomer(customer2);
            Customer customer3 = new Customer("王五");
            shop.serverCustomer(customer3);

            // 宠物店关门
            // shop.close();
        }catch (InsufficientBalanceException | AnimalNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
