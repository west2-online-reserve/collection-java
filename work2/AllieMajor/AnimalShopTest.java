package HomeWork;

import 面向对象进阶.继承Animal;

import java.time.LocalDate;
import java.util.ArrayList;

public class AnimalShopTest {
    public static void main(String[] args) {
        MyAnimalShop shop1 = new MyAnimalShop(100000,false);
        MyAnimalShop shop2 = new MyAnimalShop(100,false);
        MyAnimalShop shop3 = new MyAnimalShop(100000,false);
        MyAnimalShop shop4 = new MyAnimalShop(100000,true);

        Cat cat = new Cat("Coke",1,'史');
        ChineseRuralDog dog = new ChineseRuralDog("叶子杰",18,'史');
        Cat cat1 = new Cat("Cod",1,'公');
        Cat cat2 = new Cat("laoda",8,'公');
        Customer c1 = new Customer("顾客1");
        Customer c2 = new Customer("顾客2");

        //测试未开业
        shop4.getList1().add(cat);
        shop4.getList1().add(dog);
        shop4.entertainCustomer(c1);

        //第一个店
        shop1.getList1().add(cat);
        shop1.getList1().add(dog);
        shop1.buyNewAnimal(cat1);
        shop1.buyNewAnimal(cat2);
        shop1.entertainCustomer(c1);
        shop1.entertainCustomer(c2);
        shop1.entertainCustomer(c2);
        shop1.shutdown();

        //第二个店
        shop2.getList1().add(cat);
        shop2.getList1().add(dog);
        //余额不足异常
        shop2.buyNewAnimal(cat1);
        shop2.entertainCustomer(c1);
        shop2.shutdown();

        //第三个店
        shop3.getList1().add(cat);
        shop3.getList1().add(dog);
        shop3.buyNewAnimal(cat1);
        //无动物异常
        shop3.entertainCustomer(c1);//此时输入不存在的动物号码
        shop3.shutdown();
    }
}
