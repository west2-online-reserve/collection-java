package com.west2online.petshop;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop myShop = new MyAnimalShop(9999.0);

        System.out.println("测试买入动物----------------------------------------------------------------------------------------------------------------------------");
        try {
            ChineseDog dog1 = new ChineseDog("小黑", 2, "公", "黑色", true);
            ChineseDog dog2 = new ChineseDog("小白", 3, "母", "白色", false);
            Cat cat1 = new Cat("哈基米", 1, "母", "白色");
            Bird bird1 = new Bird("蔡徐坤", 27, "公", "黄色", true);

            myShop.buyNewAnimals(dog1);
            myShop.buyNewAnimals(dog2);
            myShop.buyNewAnimals(cat1);
            myShop.buyNewAnimals(bird1);

            System.out.println("当前余额: " + myShop.getMoney());
            System.out.println("当前动物数量: " + myShop.getAnimalsList().size());
        } catch (InsufficientBalanceException e) {
            System.out.println("买入动物失败: " + e.getMessage());
        }


        System.out.println();
        System.out.println("测试招待顾客----------------------------------------------------------------------------------------------------------------------------");
        myShop.openShop();

        Customer customer1 = new Customer("张三");
        Customer customer2 = new Customer("李四");

        try {
            myShop.serveCustomer(customer1, "小黑");
            myShop.serveCustomer(customer2, "哈基米");
        } catch (AnimalNotFoundException e) {
            System.out.println("出售动物失败: " + e.getMessage());
        }

        System.out.println();
        System.out.println("测试异常情况----------------------------------------------------------------------------------------------------------------------------");
        try {
            myShop.serveCustomer(new Customer("吴迪4"), "苏嘉鑫");
        } catch (AnimalNotFoundException e) {
            System.out.println("预期中的异常: " + e.getMessage());
        }

        System.out.println();
        System.out.println("测试余额不足异常----------------------------------------------------------------------------------------------------------------------------");
        try {
            ChineseDog expensiveDog = new ChineseDog("小黄", 3, "公", "黄", true);
            expensiveDog.setPrice(99999999.9);

            myShop.buyNewAnimals(expensiveDog);
        } catch (InsufficientBalanceException e) {
            System.out.println("预期中的异常: " + e.getMessage());
        }

        System.out.println();
        System.out.println("测试关门----------------------------------------------------------------------------------------------------------------------------");
        myShop.closeShop();

        System.out.println();
        System.out.println("第二天营业测试----------------------------------------------------------------------------------------------------------------------------");
        myShop.openShop();

        Customer customer3 = new Customer("吴迪");
        try {
            myShop.serveCustomer(customer3, "小白");
        } catch (AnimalNotFoundException e) {
            System.out.println("出售动物失败: " + e.getMessage());
        }

        myShop.closeShop();
    }
}