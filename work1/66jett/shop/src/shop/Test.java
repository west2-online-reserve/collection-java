package shop;

import java.util.List;

/**
 * 测试类
 */
public class Test {
    public static void main(String[] args) {
        // 创建宠物店实例，初始余额1000元
        MyAnimalShop myShop = new MyAnimalShop(1000.0);

        System.out.println("=== 测试开始 ===");

        try {
            // 测试1: 买入动物
            System.out.println("\n--- 测试买入动物 ---");
            myShop.buyNewAnimal(new ChineseRuralDog("小黑", 2, "公", true));
            myShop.buyNewAnimal(new Cat("咪咪", 1, "母", "白色"));
            myShop.buyNewAnimal(new Hamster("小仓", 1, "公", "金丝熊"));

            // 测试余额不足异常
            // myShop.buyNewAnimal(new Cat("贵猫", 1, "母", "金色")); // 这会抛出异常

        } catch (InsufficientBalanceException e) {
            System.out.println("买入动物异常: " + e.getMessage());
        }

        // 显示当前动物列表
        System.out.println("\n当前动物列表:");
        List<Animal> animals = myShop.getAnimalList();
        for (Animal animal : animals) {
            System.out.println(animal.toString());
        }

        // 创建顾客
        Customer customer1 = new Customer("张三");
        Customer customer2 = new Customer("李四");

        try {
            // 测试2: 招待顾客（店铺未营业）
            System.out.println("\n--- 测试招待顾客（未营业） ---");
            myShop.serveCustomer(customer1, "小黑");

            // 开店
            System.out.println("\n--- 开店 ---");
            myShop.openShop();

            // 正常招待顾客
            System.out.println("\n--- 测试正常招待顾客 ---");
            myShop.serveCustomer(customer1, "小黑");
            myShop.serveCustomer(customer2, "咪咪");

            // 测试动物不存在异常
            // myShop.serveCustomer(customer1, "不存在的动物"); // 这会抛出异常

        } catch (AnimalNotFoundException e) {
            System.out.println("招待顾客异常: " + e.getMessage());
        }

        // 测试3: 歇业
        System.out.println("\n--- 测试歇业 ---");
        myShop.closeShop();

        // 测试再次开店和歇业（无顾客情况）
        System.out.println("\n--- 测试无顾客情况 ---");
        myShop.openShop();
        myShop.closeShop();

        System.out.println("\n=== 测试结束 ===");

        // 显示最终状态
        System.out.println("\n最终状态:");
        System.out.println("店铺余额: " + myShop.getBalance() + "元");
        System.out.println("剩余动物数量: " + myShop.getAnimalList().size());
        System.out.println("总顾客数量: " + myShop.getCustomerList().size());
    }
}