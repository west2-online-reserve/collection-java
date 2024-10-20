package com.petstore;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop myAnimalShop = new MyAnimalShop(500.0); // 初始化宠物店，余额500元

        // 测试买入动物
        try {
            myAnimalShop.buyAnimal("小白", 2, "母", true); // 买入中华田园犬
            myAnimalShop.buyAnimal("小黑", 3, "公", false); // 买入中华田园犬
            myAnimalShop.buyAnimal("咪咪", 1, "母", false); // 买入猫
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        // 测试招待顾客
        Customer customer1 = new Customer("张三");
        myAnimalShop.serveCustomer(customer1, "小白"); // 招待顾客张三购买小白

        Customer customer2 = new Customer("李四");
        myAnimalShop.serveCustomer(customer2, "咪咪"); // 招待顾客李四购买咪咪

        // 测试歇业
        myAnimalShop.closeShop();
    }
}
