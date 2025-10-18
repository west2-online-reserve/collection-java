package example;

import java.time.LocalDate;

import static example.Gender.MALE;
import static example.Gender.FEMALE;

public class Test {
    public static void main(String[] args) {
        //LocalDate l = new LocalDate(2025, 6, 7);
        //System.out.println(l.toString());

        MyAnimalShop myAnimalShop = new MyAnimalShop(1500,true);
        Cat cat1 = new Cat("小凯", 10, MALE);
        Cat cat2 = new Cat("二狗", 9, FEMALE);
        Cat cat3 = new Cat("李麻花", 7, MALE);
        Bird bird1 = new Bird("图图", 12, FEMALE);
        Bird bird2 = new Bird("鸟二", 9, FEMALE);
        ChineseRuralDog chineseRuralDog1 = new ChineseRuralDog("二蛋", 10, MALE, true);
        ChineseRuralDog chineseRuralDog2 = new ChineseRuralDog("大黄", 3, FEMALE, true);
        ChineseRuralDog chineseRuralDog3 = new ChineseRuralDog("二狗", 13, FEMALE, true);

        try{
            myAnimalShop.buyAnimal(cat1);
            myAnimalShop.buyAnimal(cat2);
            myAnimalShop.buyAnimal(cat3);
            myAnimalShop.buyAnimal(bird1);
            myAnimalShop.buyAnimal(chineseRuralDog1);
            myAnimalShop.buyAnimal(chineseRuralDog2);

        }catch(InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
        Customer customer1 = new Customer("小李");
        Customer customer2 = new Customer("小俊");
        Customer customer3 = new Customer("王五");
        Customer customer4 = new Customer("小凯");
        Customer customer5 = new Customer("小宇");

        try {
            myAnimalShop.solicitCustomer(customer1, cat1, 300, LocalDate.of(2025, 6,18));
            myAnimalShop.solicitCustomer(customer2, bird1, 350, LocalDate.of(2025, 6,18));
            myAnimalShop.solicitCustomer(customer3, chineseRuralDog1, 430, LocalDate.of(2025, 6,18));
            myAnimalShop.solicitCustomer(customer4, cat2, 420, LocalDate.of(2025, 6,18));
            myAnimalShop.solicitCustomer(customer4, chineseRuralDog2, 400, LocalDate.of(2025, 6,18));


        }catch(AnimalNotFountException e) {
            System.out.println(e.getMessage());
        }

        myAnimalShop.printCustomerHasVisit();

        myAnimalShop.close();

        //第二天
        myAnimalShop.open();
        try{
            myAnimalShop.buyAnimal(bird2);
            myAnimalShop.buyAnimal(chineseRuralDog3);
        }catch(InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        try {
            myAnimalShop.solicitCustomer(customer1, cat3, 280, LocalDate.of(2025, 6,19));
            myAnimalShop.solicitCustomer(customer2, bird2, 411, LocalDate.of(2025, 6,19));
            myAnimalShop.solicitCustomer(customer5, chineseRuralDog3, 470, LocalDate.of(2025, 6,19));


        }catch(AnimalNotFountException e) {
            System.out.println(e.getMessage());
        }
        myAnimalShop.printCustomerHasVisit();

        myAnimalShop.close();

    }

}
