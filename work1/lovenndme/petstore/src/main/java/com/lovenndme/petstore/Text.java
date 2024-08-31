package com.lovenndme.petstore;

import com.lovenndme.petstore.animal.Animal;
import com.lovenndme.petstore.animal.Bird;
import com.lovenndme.petstore.animal.Cat;
import com.lovenndme.petstore.animal.Dog;
import com.lovenndme.petstore.animalShop.MyAnimalShop;
import com.lovenndme.petstore.customer.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Text {
    public static final int BUY_NEW_ANIMAL = 1;
    public static final int SERVE_CUSTOMER = 2;
    public static final int CLOSE_SHOP = 3;
    public static final int OPEN_SHOP = 4;
    public static final int EXIT = 5;

    static List<Animal> animalList = new ArrayList<>();
    static List<Customer> customerList = new ArrayList<>();
    static Customer customer1 = new Customer("黄毅弘", 0, LocalDate.now());
    static Customer customer2 = new Customer("黄铭翔", 0, LocalDate.now());
    static Customer customer3 = new Customer("些许懂", 0, LocalDate.now());

    static {
        Dog dog = new Dog("中华田园犬", 3, 1, true);
        Cat cat = new Cat("狸花猫", 2, 0);
        Bird bird = new Bird("金丝雀", 4, 1);

        animalList.add(dog);
        animalList.add(cat);
        animalList.add(bird);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyAnimalShop shop = new MyAnimalShop(500, animalList, customerList);

        System.out.println("------------欢迎来到宠物店-----------");

        while (true) {
            System.out.println("您可以选择以下服务:\n" + "1.买入新动物\n" + "2.招待顾客\n" + "3.歇业\n" + "4.开业\n" + "5.退出");
            int yourChoice = sc.nextInt();

            if (yourChoice == EXIT) {
                System.out.println("谢谢光临，程序结束。");
                break;
            }

            switch (yourChoice) {
                case BUY_NEW_ANIMAL -> {
                    System.out.println("------------选择你要购买的宠物-----------");
                    System.out.println("1.中华田园犬\n" + "2.狸花猫\n" + "3.金丝雀");
                    int yourAnimal = sc.nextInt();
                    switch (yourAnimal) {
                        case 1 -> {
                            Dog dog = new Dog("中华田园犬", 3, 1, true);
                            shop.buyNewAnimal(dog);
                        }
                        case 2 -> {
                            Cat cat = new Cat("狸花猫", 2, 0);
                            shop.buyNewAnimal(cat);
                        }
                        case 3 -> {
                            Bird bird = new Bird("金丝雀", 4, 1);
                            shop.buyNewAnimal(bird);
                        }
                        default -> System.out.println("抱歉，本店未卖该类动物");
                    }
                }
                case SERVE_CUSTOMER -> {
                    shop.serveCustomer(customer1);
                    shop.serveCustomer(customer2);
                    shop.serveCustomer(customer3);
                }
                case CLOSE_SHOP -> shop.closeShop();
                case OPEN_SHOP -> {
                    shop.setOpen(true);
                    System.out.println("本店已开业");
                }
                default -> System.out.println("没有这个选项");
            }
        }

        sc.close();
    }
}

