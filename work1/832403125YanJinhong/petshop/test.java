package com.YJH.west2.q.chongwudianself;

import java.time.LocalDate;

public class test {
    public static void main(String[] args) {

        // 创建一个宠物店实例
        Myanimalshop shop = new Myanimalshop(88888);

        Chinesedog dog1 = new Chinesedog("小花狗", 1,0, true);
        Chinesedog dog2 = new Chinesedog("旺财狗", 2, 1, false);
        Chinesedog dog3 = new Chinesedog("哈士奇", 3, 0, true);
        Chinesedog dog4 = new Chinesedog("柴犬", 4, 1, false);

        Cat cat1 = new Cat("汤姆猫", 1, 0);
        Cat cat2 = new Cat("小花猫", 2, 1);

        Rabbit rabbit1 = new Rabbit("兔兔", 1, 0);

        try {
            shop.buyAnimal(dog1);
            shop.buyAnimal(dog2);
            shop.buyAnimal(dog3);
            shop.buyAnimal(cat1);
            shop.buyAnimal(cat2);
            shop.buyAnimal(rabbit1);
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage());
        }

        shop.setBalance(10.0);

        // 测试余额不足
        try {
            shop.buyAnimal(dog4);
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage());
        }

        // 测试招待客户
        Customer customer1 = new Customer("张三", 1, LocalDate.of(2024, 10, 23));
        Customer customer2 = new Customer("李四", 2, LocalDate.of(2024, 10, 23));
        Customer customer3 = new Customer("王五", 3, LocalDate.of(2024, 10, 23));
        Customer customer4 = new Customer("赵六", 4, LocalDate.of(2024, 10, 23));

        // 测试营业
        try {
            shop.greetCustomer(customer1, dog1);
            shop.greetCustomer(customer1, dog2);
            shop.greetCustomer(customer2, cat1);
            shop.greetCustomer(customer2, cat2);
            shop.greetCustomer(customer3, rabbit1);
            shop.greetCustomer(customer4, dog1);
            shop.greetCustomer(customer4, dog3);
        } catch (AnimalNotFoundException ex) {
            System.out.println(ex.getMessage());
        }


        // 测试关店
        shop.close();
        try {
            shop.greetCustomer(customer4, dog3);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }

        // 测试营业
        shop.open();
        try {
            shop.buyAnimal(dog4);
            shop.greetCustomer(customer1, dog4);
            shop.greetCustomer(customer1, dog4);
        } catch (InsufficientBalanceException | AnimalNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        shop.close();
    }
}
