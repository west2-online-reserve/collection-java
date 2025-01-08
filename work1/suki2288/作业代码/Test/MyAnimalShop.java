package Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 1.这是一个MyAnimalShop类，不提供有参构造器了，全部由用户自己定义
 * 2.接口类放到该类最底部
 * 3.两个异常类也放到最底部
 */
public class MyAnimalShop implements AnimalShop {
    // 成员变量
    private double balance;
    private List<String> animals = new ArrayList<>();
    private List<String> customers = new ArrayList<>();
    private boolean isOpen = false;
    private LocalDate openingDate = LocalDate.of(2025, 1, 1);

    public MyAnimalShop() {
    }


    /**
     * 这是买入新动物的方法，具体有以下功能：
     * 1.让用户选择起始金额
     * 2.选择动物种类和个数进行购买，实时改变商店余额
     * 3.存放动物的列表
     * 4.当余额不够抛出“零钱不足，无法购买”异常
     * 5.当结束购买成功开业
     */
    @Override
    public void buyNewPet() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n================================");
        System.out.println("请输入商店的起始金额（推荐800-1200）");
        int balance = sc.nextInt();
        int dogNumber = 0;
        int catNumber = 0;

        while (true) {
            System.out.println("请输入要购买的动物种类：[dog（单价150元），cat（单价150元）]");
            System.out.println("（输入“购买完毕”结束购买）：");
            String species = sc.next();
            if (species.equals("购买完毕")) {
                System.out.println("\n================================");
                System.out.println("商店存放动物有：" + animals);
                System.out.println("已完成购买，商店有" + dogNumber + "只狗狗和" + catNumber + "只猫猫，还剩" + balance + "元");

                System.out.println("\n商店开业啦！欢迎光临！");
                break;
            }
            try {
                if (species.equals("dog")) {
                    System.out.println("请输入狗狗个数进行购买：");
                    int number = sc.nextInt();
                    int cost = number * 150;
                    if (balance < cost) {
                        throw new InsufficientBalanceException("零钱不足，无法购买" + number + "只狗狗，当前余额：" + balance + "元");
                    }
                    balance -= cost;
                    dogNumber += number;
                    for (int i = 0; i < number; i++) {
                        animals.add("dog");
                    }
                    System.out.println("成功购买" + number + "只狗狗，商店还剩" + balance + "元");
                } else if (species.equals("cat")) {
                    System.out.println("请输入猫猫个数进行购买：");
                    int number = sc.nextInt();
                    int cost = number * 150;
                    if (balance < cost) {
                        throw new InsufficientBalanceException("零钱不足，无法购买" + number + "只猫猫，当前余额：" + balance + "元");
                    }
                    balance -= cost;
                    catNumber += number;
                    for (int i = 0; i < number; i++) {
                        animals.add("cat");
                    }
                    System.out.println("成功购买" + number + "只猫猫，商店还剩" + balance + "元");
                } else {
                    System.out.println("没有这个宠物，请重新选择");
                }
            } catch (InsufficientBalanceException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 这是接待顾客的方法，具体有以下功能：
     * 1.为了方便，我把开店时间定为2025.1.1，接待三位顾客，这些顾客在这一天到店，以减少代码复杂度
     * 2.创建一个顾客列表ArrayList，用于存放客户
     * 3.接待一位顾客，由用户决定顾客购买什么，有异常类 (AnimalNotFountException) 没找到动物异常，店内没有动物可买时抛出
     * 4.接待完顾客，计算利润，关店，我把两个方法写一次了，懒得去折腾那些变量，反正第三个方法内容也不多
     */
    @Override
    public void receiveGuests() {
        // 初始化顾客列表
        // 假设开店时间为2025.1.1
        System.out.println("\n================================");
        System.out.println("商店开业时间为：" + openingDate);
        System.out.println("今天接待三位顾客，开始接待......");
        int profit = 0;

        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= 3; i++) {
            System.out.println("接待第" + i + "位顾客，请输入顾客姓名：");
            String customerName = sc.next();
            customers.add(customerName);

            while (true) {
                try {
                    System.out.println("请输入要购买的动物种类：[dog（价格250元），cat（价格350元）]  \n" +
                            "（输入“购买完毕”结束购买）：");;
                    String animalToBuy = sc.next();

                    if (animalToBuy.equals("购买完毕")){
                        System.out.println("欢迎" + customerName + "下次光临");
                        break;
                    }

                    // 检查下动物是否在店内
                    if (!animals.contains(animalToBuy)) {
                        throw new AnimalNotFoundException("店内没有 " + animalToBuy + " 可供购买！");
                    }

                    // 如果动物存在，则完成购买，从库存中移除已购买的动物
                    animals.remove(animalToBuy);
                    System.out.println(customerName + " 成功购买了一只 " + animalToBuy);
                    System.out.println("当前商店剩余动物：" + animals);

                    // 并且计算利润
                    if (animalToBuy.equals("dog")){
                        profit += 100;
                        System.out.println("商店目前盈利：" + profit);
                    }else {
                        profit += 200;
                        System.out.println("商店目前盈利：" + profit);
                    }
                } catch (AnimalNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("\n================================");
        }

        /**
         * 歇业 -> (LocalDate判断)我觉得这个没什么必要，无非是设一个日期，判断不相等关闭
         * 我就按照正常逻辑，接待完顾客就关店
         */
        isOpen = false;

        System.out.println("\n================================");
        System.out.println("商店关门了");
        System.out.println("今天接待的顾客列表：" + customers);
        System.out.println("商店剩余动物：" + animals);
        System.out.println("商店今天盈利了：" + profit);
    }
}

/**
 * 这是宠物店接口类
 */
interface AnimalShop {
    void buyNewPet() throws InsufficientBalanceException;
    void receiveGuests();
}


/**
 * 这是两个异常类
 * 1.异常类 (AnimalNotFountException) 没找到动物异常，店内没有动物可买时抛出
 * 2.异常类 (InsufficientBalanceException) 余额不足异常时抛出
 */

// 余额不足异常
class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
// 动物未找到异常
class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}