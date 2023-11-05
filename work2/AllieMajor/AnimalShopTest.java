package HomeWork;

import 面向对象进阶.继承Animal;

import java.time.LocalDate;
import java.util.ArrayList;

public class AnimalShopTest {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(100000,false);
        Cat cat = new Cat("Coke",1,'史');
        ChineseRuralDog dog = new ChineseRuralDog("叶子杰",18,'史');
        shop.getList1().add(cat);
        shop.getList1().add(dog);
        Cat cat1 = new Cat("Cod",1,'公');
        Cat cat2 = new Cat("laoda",8,'公');
        shop.buyNewAnimal(cat1);
        shop.buyNewAnimal(cat2);
        Customer c1 = new Customer("顾客1");
        Customer c2 = new Customer("顾客2");
        shop.entertainCustomer(c1);
        shop.entertainCustomer(c2);
        shop.shutdown();





    }
}
