package example;

import java.time.LocalDate;

import static example.Gender.MALE;
import static example.Gender.FEMALE;

public class Test {
    public static void main(String[] args) {
        //LocalDate l = new LocalDate(2025, 6, 7);
        //System.out.println(l.toString());

        MyAnimalShop myAnimalShop = new MyAnimalShop(1500,true);
        Cat cat = new Cat("小凯", 10, MALE);
//        System.out.println(cat.toString());
        try{
            myAnimalShop.buyAnimal(cat);
            myAnimalShop.buyAnimal(new Cat("二狗", 9, FEMALE));
            myAnimalShop.buyAnimal(new Cat("李麻花", 7, MALE));
            myAnimalShop.buyAnimal(new Bird("图图", 12, FEMALE));
            myAnimalShop.buyAnimal(new ChineseRuralDog("二蛋", 10, MALE, true));
            myAnimalShop.buyAnimal(new ChineseRuralDog("大黄", 3, FEMALE, true));
        }catch(InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
        Customer customer1 = new Customer("小李");
        Customer customer2 = new Customer("小俊");
        Customer customer3 = new Customer("王五");
        Customer customer4 = new Customer("小凯");
        Customer customer5 = new Customer("小宇");

        try {
            myAnimalShop.SolicitCustomer(customer1, "猫", 300, LocalDate.of(2025, 6,18));
            myAnimalShop.SolicitCustomer(customer2, "鸟", 350, LocalDate.of(2025, 6,18));
            myAnimalShop.SolicitCustomer(customer3, "中华田园犬", 430, LocalDate.of(2025, 6,18));
            myAnimalShop.SolicitCustomer(customer4, "猫", 420, LocalDate.of(2025, 6,18));
            myAnimalShop.SolicitCustomer(customer4, "中华田园犬", 400, LocalDate.of(2025, 6,18));


        }catch(AnimalNotFountException e) {
            System.out.println(e.getMessage());
        }

        myAnimalShop.printCustomerHasVisit();

        myAnimalShop.close();

        //第二天
        myAnimalShop.open();
        try{
            myAnimalShop.buyAnimal(new Bird("鸟二", 9, FEMALE));
            myAnimalShop.buyAnimal(new ChineseRuralDog("二狗", 13, FEMALE, true));
        }catch(InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        try {
            myAnimalShop.SolicitCustomer(customer1, "猫", 280, LocalDate.of(2025, 6,19));
            myAnimalShop.SolicitCustomer(customer2, "鸟", 411, LocalDate.of(2025, 6,19));
            myAnimalShop.SolicitCustomer(customer5, "中华田园犬", 470, LocalDate.of(2025, 6,19));


        }catch(AnimalNotFountException e) {
            System.out.println(e.getMessage());
        }
        myAnimalShop.printCustomerHasVisit();

        myAnimalShop.close();

    }

}
