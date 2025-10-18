package com.cai.task01;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.Scanner;

public class Test {
    private static Scanner scanner = new Scanner(System.in);
    private static final String ID_REGEX = "^\\d{5}$"; // 正则表达式确保ID是五位数字

    public static void main(String[] args) {
        MyPetShop myPetShop = new MyPetShop("JAVA PetShop", 2000);
        List<Animal> animalsCanBuy = new ArrayList<>();

        // 初始化动物
        Animal.Dog D0 = new Animal.Dog("钛镁", 2, "Boy", "中华田园犬", true);
        Animal.Dog D1 = new Animal.Dog("基尼", 1, "Girl", "金毛", true);
        Animal.Dog D2 = new Animal.Dog("啵啵", 3, "Girl", "拉布拉多", true);
        Animal.Dog D3 = new Animal.Dog("兰博", 1, "Boy", "金毛", true);
        Animal.Dog D4 = new Animal.Dog("凯勒", 2, "Boy", "纯种杜宾犬", true);
        D4.setPrice(5000);

        Animal.Cat C0 = new Animal.Cat("黑糖", 1, "Girl", "狸猫");
        Animal.Cat C1 = new Animal.Cat("薯条", 3, "Girl", "橘猫");
        Animal.Cat C2 = new Animal.Cat("可乐", 2, "Girl", "布偶猫");
        Animal.Cat C3 = new Animal.Cat("白糖", 1, "Girl", "英短");
        Animal.Cat C4 = new Animal.Cat("小白", 4, "Girl", "小花猫");
        Animal.Cat C5 = new Animal.Cat("小黑", 5, "Boy", "雀猫");

        // 初始化顾客
        Customer customer1 = new Customer("00001", "俊凡", 5, LocalDate.of(2025, 9, 30));
        Customer customer2 = new Customer("00002", "荣琛", 1, LocalDate.of(2024, 5, 30));

        // 初始动物添加到宠物店
        myPetShop.getAnimals().add(D0);
        D0.setPrice(200);
        D0.setCost(100);
        myPetShop.getAnimals().add(C0);
        C0.setCost(200);
        C0.setPrice(400);
        myPetShop.getAnimals().add(C1);
        C1.setCost(200);
        C1.setPrice(300);

        // 添加老顾客
        myPetShop.getAllCustomers().add(customer1);
        myPetShop.getAllCustomers().add(customer2);

        // 可购买的动物列表
        animalsCanBuy.add(D1);
        animalsCanBuy.add(D2);
        animalsCanBuy.add(D3);
        animalsCanBuy.add(D4);
        animalsCanBuy.add(C2);
        animalsCanBuy.add(C3);
        animalsCanBuy.add(C4);
        animalsCanBuy.add(C5);

        LocalDate date = LocalDate.now();

        // 模拟5天营业
        for (int i = 0; i < 5; i++) {
            System.out.println("\n===== 第" + (i + 1) + "天：" + date + " =====");
            if (!myPetShop.getIsRunning()) {
                System.out.println("============== " + myPetShop.getName() + " 今日歇业 ================");
                date = date.plusDays(1); // 天数仍需加一
                continue;
            }

            // 买入动物逻辑
            System.out.print("请输入是否买入动物（是：1 否：0）：");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 消费掉换行符
            System.out.println();

            if (choice == 1) {
                System.out.println("可购买的动物列表：");
                printAnimalsCanBuy(animalsCanBuy);
                System.out.print("请输入想要购买的动物的名字（输入-1停止购买）:");
                String animalNameInput = input();

                while (!animalNameInput.equals("-1")) {
                    Animal tempAnimal = findAnimalByName(animalsCanBuy, animalNameInput);
                    if (tempAnimal == null) {
                        System.out.println("列表中没有该动物");
                    } else {
                        System.out.print("请设置该动物的售价：");
                        int price = scanner.nextInt();
                        scanner.nextLine(); // 消费换行符
                        try {
                            myPetShop.buyAnimal(animalsCanBuy,tempAnimal, tempAnimal.getPrice() , price);
                        } catch (InsufficientBalanceException e) {
                            System.out.println("买入失败: " + e.getMessage());
                        }
                    }
                    System.out.print("请输入想要购买的动物的名字（输入-1停止购买）:");
                    animalNameInput = input();
                }
            }


            System.out.println("============================= 开始招待顾客 ===============================");
            int flag = 1;
            while (flag != -1) {
                String idInput = "";
                while (true) {
                    System.out.print("请输入顾客ID（5位数字，输入-1结束今日招待）：");
                    idInput = input();
                    if (idInput.equals("-1")) {
                        flag = -1;
                        break;
                    }
                    if (idInput.isEmpty()) {
                        System.out.println("错误：顾客ID不能为空，请重新输入！");
                        continue;
                    }
                    if (!idInput.matches(ID_REGEX)) {
                        System.out.println("错误：顾客ID必须是5位数字，请重新输入！");
                        continue;
                    }
                    break;
                }
                if (flag == -1) break;

                // 检验顾客姓名不能为空
                String customerNameInput = "";
                while (true) {
                    System.out.print("请输入顾客姓名：");
                    customerNameInput = input();
                    if (customerNameInput.isEmpty()) {
                        System.out.println("错误：顾客姓名不能为空，请重新输入！");
                    } else {
                        break; // 姓名非空，跳出循环
                    }
                }
                // 初始化顾客
                Customer tempCustomer = new Customer(idInput, customerNameInput);

                // 打印店内动物列表
                System.out.println("店内可售动物：");
                printAnimalList(myPetShop.getAnimals());
                System.out.print("请输入要购买的动物名字：");
                String animalNameInput = input();
                Animal tempAnimal = findAnimalByName(myPetShop.getAnimals(), animalNameInput);

                try {
                    myPetShop.serveCustomer(tempCustomer, tempAnimal, date);
                } catch (AnimalNotFoundException e) {
                    System.out.println("出售失败: " + e.getMessage());
                }
            }

            // 歇业处理
            myPetShop.closeShop(date);
            date = date.plusDays(1); // 天数加一
        }

        //打印所有顾客，留作纪念
        for(Customer customer : myPetShop.getAllCustomers()) {
            System.out.println(customer);
        }
        scanner.close();
    }

    // 打印可购买的动物列表
    public static void printAnimalsCanBuy(List<Animal> animalsCanBuy) {
        for (Animal animal : animalsCanBuy) {
            System.out.println("name: " + animal.getName() + " price: " + animal.getPrice());
        }
    }

    // 打印宠物店内已有的动物及售价
    public static void printAnimalList(List<Animal> animalList) {
        for (Animal animal : animalList) {
            System.out.println("name: " + animal.getName() + " price: " + animal.getPrice());
        }
    }

    // 查找指定名字的动物
    public static Animal findAnimalByName(List<Animal> animals, String targetName) {
        if (animals == null || targetName == null) {
            return null;
        }
        for (Animal animal : animals) {
            if (targetName.equals(animal.getName())) {
                return animal;
            }
        }
        return null;
    }

    // 处理输入用正则表达式去掉空格
    public static String input() {
        String input = scanner.nextLine();
        return input.replaceAll("\\s", ""); // 去除所有空白字符
    }
}