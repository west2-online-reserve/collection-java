import java.time.LocalDate;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(100000,false,80);

        Cat cat1 = new Cat("Jerry",3,"女",100);
        Cat cat2 = new Cat("Sara",3,"女",100);
        Cat cat3 = new Cat("Lili",3,"女",100);
        ChineseRuralDog dog1 = new ChineseRuralDog("field",2,"男",100,true);

        shop.getAnimalArrayList().add(cat2);
        shop.getAnimalArrayList().add(cat3);


        try {
            shop.buy(cat1,900);
        } catch (InsufficientBalanceException e) {
             e.printStackTrace();
        }

        try {
            shop.buy(cat1,1);
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
        }
        try {
            shop.buy(dog1,1);
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
        }

        shop.setIsClosed(true);

        Customer customer1 = new Customer("Jack",0,LocalDate.now());

        try {
            shop.reception(customer1);
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }

        shop.setIsClosed(false);
        Customer customer2 = new Customer("Rare",1,LocalDate.now());
        shop.getCustomerArrayList().add(customer2 );
        try {
            shop.reception(customer2);
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }


        try {
            shop.sell(dog1,1);
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }
        try {
            shop.sell(cat1,1);
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }
        try {
            shop.sell(cat2,1);
        } catch (AnimalNotFountException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < shop.getCustomerArrayList().size(); i++) {
            System.out.println(shop.getCustomerArrayList().get(i));
        }
        for (int i = 0; i < shop.getAnimalArrayList().size(); i++) {
            System.out.println(shop.getAnimalArrayList().get(i));
        }
    }
}
