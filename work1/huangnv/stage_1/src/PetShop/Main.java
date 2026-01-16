package PetShop;
import  PetShop.model.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        caseClosedShopBuy();
        caseInsufficientBalance();
        caseAnimalNotInShop();
        caseNormalFlow();
    }

    private static void caseClosedShopBuy() {
        System.out.println("Case 1: buy when shop is closed");
        MyAnimalShop shop = new MyAnimalShop(500);
        shop.close();
        try {
            shop.buyAnimal(new Cat("Kitty", 1, true));
        } catch (ShopClosedException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }

    private static void caseInsufficientBalance() {
        System.out.println("Case 2: insufficient balance");
        MyAnimalShop shop = new MyAnimalShop(50);
        shop.open();
        try {
            shop.buyAnimal(new Cat("Mimi", 1, false));
        } catch (InsufficientBalanceException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }

    private static void caseAnimalNotInShop() {
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

    private static void caseNormalFlow() {
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