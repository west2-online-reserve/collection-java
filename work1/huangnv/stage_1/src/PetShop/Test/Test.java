package PetShop.Test;

import PetShop.model.*;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
//        testClosedShopBuy();
//        testInsufficientBalance();
//        testAnimalNotInShop();
        testNormalFlow();
    }

    private static void testClosedShopBuy() {
        System.out.println("Case 1: buy when shop is closed");
        MyAnimalShop shop = new MyAnimalShop(500);
        try {
            shop.buyAnimal(new Cat("Kitty", 1, true));
        } catch (ShopClosedException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }

    private static void testInsufficientBalance() {
        System.out.println("Case 2: insufficient balance");
        MyAnimalShop shop = new MyAnimalShop(50);
        shop.open();
        try {
            shop.buyAnimal(new Cat("Mimi", 1, false));
        } catch (InsufficientBalanceException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }

    private static void testAnimalNotInShop() {
        System.out.println("Case 3: sell animal not in shop");
        MyAnimalShop shop = new MyAnimalShop(1000);
        shop.open();
        Animal cat = new Cat("Snow", 2, true);
        shop.buyAnimal(cat);
        Customer customer = new Customer("Alice", LocalDate.now());
        Animal other = new Crow("Crow", 1, false);
        try {
            shop.treatCustomer(customer, other);
        } catch (AnimalNotFountException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }

    private static void testNormalFlow() {
        System.out.println("Case 4: normal flow");
        MyAnimalShop shop = new MyAnimalShop(1000);
        shop.open();
        Animal dog = new ChineseRuralDog("Wang", 2, true, true);
        shop.buyAnimal(dog);
        Customer customer = new Customer("Bob", LocalDate.now());
        shop.treatCustomer(customer, dog);
        shop.close();
    }
}
