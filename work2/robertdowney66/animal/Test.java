package com.west2.check02;

import java.time.LocalDate;
import java.util.ArrayList;

/***
 * 中间输出的price都是该动物的售价，而不是动物的进价
 * @author yuyu
 */
public class Test {
    public static void main(String[] args) {

        ArrayList<AbstractAnimal> animals = new ArrayList<>();
        ChineseRuralDogs dog1 = new ChineseRuralDogs("花花", 1, "公", true);
        LeopardGeckos leopardGeckos1 = new LeopardGeckos("乐乐", 1, "公");
        Cats cat1 = new Cats("浩浩", 2, "母");
        animals.add(dog1);
        animals.add(leopardGeckos1);
        animals.add(cat1);
        MyAnimalShop myAnimalShop = new MyAnimalShop(3200, animals, new ArrayList<>(), true);


        // 购买操作test，为保证test连贯性，已用try-catch捕捉异常部分
        Cats cat2 = new Cats("公主", 2, "母");
        LeopardGeckos leopardGeckos2 = new LeopardGeckos("王子", 1, "母");
        myAnimalShop.purchaseOfNewAnimal(cat2, LocalDate.of(2023, 10, 4));
        myAnimalShop.purchaseOfNewAnimal(leopardGeckos2, LocalDate.of(2023, 10, 4));
        try {
            myAnimalShop.purchaseOfNewAnimal(leopardGeckos2, LocalDate.of(2023, 10, 4));
        } catch (InsufficieBalanceException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }


        /*
        招待顾客部分test，为保证test连贯性，已用try-catch捕捉异常部分
        由于测试当天为十月十四号，所以以下数据基于十四号产生，其中四号顾客为十五号购买，所以十四号到店顾客列表中没有该顾客
         */
        Customer c1 = new Customer("Tom", 0, LocalDate.of(2023, 10, 14));
        Customer c2 = new Customer("Tony", 1, LocalDate.of(2023, 10, 14));
        Customer c3 = new Customer("Jane", 2, LocalDate.of(2023, 10, 14
        ));
        Customer c4 = new Customer("Marry", 2, LocalDate.of(2023, 10, 15));
        myAnimalShop.treatCustomers(c1, cat2);
        myAnimalShop.treatCustomers(c2, dog1);
        try {
            myAnimalShop.treatCustomers(c3, dog1);
        } catch (AnimalNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println();
        }
        myAnimalShop.treatCustomers(c4, leopardGeckos1);



        // 由于测试当天为十月十四号，所以以下数据基于十四号产生
        myAnimalShop.closureOfBusiness();
        myAnimalShop.purchaseOfNewAnimal(new Cats("haha",1,"母"),LocalDate.of(1999,11,2));
        myAnimalShop.treatCustomers(c1,dog1);

    }
}
