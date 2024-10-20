package com.catowl.animalshop;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Animal> animalsList = new ArrayList<>();
        ArrayList<Customer> customersList = new ArrayList<>();

        System.out.println("请输入开业时间（分别输入年月日）：");
        int year = sc.nextInt(), month = sc.nextInt(), day = sc.nextInt();
        LocalDate localDate = LocalDate.of(year, month, day);

        System.out.print("请输入初始余额：");
        double balance = sc.nextDouble();

        boolean status = true;
        MyAnimalShop myAnimalShop = new MyAnimalShop(balance, animalsList, customersList, status);
        //记录前一天的余额，方便后续计算盈利
        double balanceOfYesterday = myAnimalShop.getBalance();

        while (true) {
            System.out.println("购买动物请输入“ 1 ”，招待客户请输入“ 2 ”，其他输入则歇业");
            String choice = sc.next();
            switch (choice) {
                //购买动物
                case "1": {
                    purchaseList();
                    System.out.println("余额：" + myAnimalShop.getBalance());
                    int choice2 = sc.nextInt();
                    int age;
                    String name, gender;
                    System.out.println("性别要求（“male”或“female”）：");
                    gender = sc.next();
                    System.out.println("年龄要求：");
                    age = sc.nextInt();
                    System.out.println("请给它取名：");
                    name = sc.next();
                    switch (choice2) {
                        case 1:
                            boolean isVaccinelnjected;
                            System.out.println("是否给它注射疫苗（true or false）：");
                            isVaccinelnjected = sc.nextBoolean();
                            ChineseRuralDog chineseRuralDog = new ChineseRuralDog(name, age, gender, isVaccinelnjected);
                            myAnimalShop.buyAnimal(chineseRuralDog);
                            break;
                        case 2:
                            Cat cat = new Cat(name, age, gender);
                            myAnimalShop.buyAnimal(cat);
                            break;
                        case 3:
                            Rabbit rabbit = new Rabbit(name, age, gender);
                            myAnimalShop.buyAnimal(rabbit);
                            break;
                        default:
                            System.out.println("没有该动物");
                            break;
                    }
                    break;
                }
                //招待顾客
                case "2": {
                    String name;
                    System.out.println("请输入顾客姓名：");
                    name = sc.next();
                    Customer customer = new Customer(name, localDate);
                    saleList(myAnimalShop);
                    System.out.println("请顾客输入编号：");
                    int index = sc.nextInt();
                    myAnimalShop.entertainCustomer(customer, index);
                    System.out.println("余额：" + myAnimalShop.getBalance());
                    break;
                }
                //歇业
                default: {
                    myAnimalShop.shutdown(localDate, myAnimalShop.getBalance() - balanceOfYesterday);
                    localDate = localDate.plusDays(1);//日期加一
                    balanceOfYesterday = myAnimalShop.getBalance();
                    System.out.println("明天是否继续开业？（开业输入“1”）");
                    String isOpen = sc.next();
                    if (!isOpen.equals("1")) {
                        return;
                    }
                }
            }
        }

    }
    //采购清单
    public static void purchaseList() {
        System.out.println("------------------------------------");
        System.out.println("可采购的动物：(请输入对应编号选择购买)");
        System.out.println("  品种         售价         编号");
        System.out.println("中华田园犬      50           1");
        System.out.println("  猫猫         100           2");
        System.out.println("  兔子         25            3");
        System.out.println("------------------------------------");
        System.out.println();
    }
    //售卖清单
    public static void saleList(MyAnimalShop myAnimalShop) {
        System.out.println("顾客可购买的动物：");
        for (int i = 0; i < myAnimalShop.getAnimalsList().size(); i++) {
            System.out.println("编号：" + i + " " + myAnimalShop.getAnimalsList().get(i).toString());
        }
    }
}
