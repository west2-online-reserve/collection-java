package com.WestTwo.work2;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop myAnimalShop = new MyAnimalShop();
        myAnimalShop.setMoney1(1000);
        myAnimalShop.setMoney(1000);
        Cat cat1 = new Cat("哈哈", 1, false);
        ChineseRuralDog dog1 = new ChineseRuralDog("哇哇", 2, true, true);
        Hamster hamster1 = new Hamster("咔咔", 1, false, 300);
        myAnimalShop.Buy(cat1);
        myAnimalShop.Buy(hamster1);
        Customer customer1 = new Customer();
        customer1.setName("张三");
        customer1.setDate(LocalDate.of(2022, 3, 18));
        LocalDate date = LocalDate.of(2022, 3, 18);
        myAnimalShop.Buy(dog1);
        System.out.println(myAnimalShop.getAnimalList());
        myAnimalShop.Serve(customer1, hamster1);
        myAnimalShop.Off(date);
    }
}
