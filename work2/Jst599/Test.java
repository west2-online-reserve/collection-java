package PetShop;

import PetShop.*;

import java.time.LocalDate;

/**
 * @author Jst599
 * date 2023/10/20
 */
public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop1 = new MyAnimalShop(10, false, "shop1");

        MyAnimalShop shop2 = new MyAnimalShop(500, false, "shop2");

        Customer Jackson = new Customer("Jackson", 4);
        Customer Rose = new Customer("Rose", 5);
        Customer James = new Customer("James", 4);
        Customer Harden = new Customer("Harden", 5);
        Customer Curry = new Customer("Curry", 3);

        Cat Angle = new Cat("Angle", 2, "female");
        Cat Force = new Cat("Force", 2, "male");
        Cat Miracle = new Cat("Miracle", 1, "male");

        ChineseDog A = new ChineseDog("A", 2, "female", true);
        ChineseDog B = new ChineseDog("B", 1, "male", false);
        ChineseDog C = new ChineseDog("C", 1, "female",true);

        shop1.buyAnimal(Angle);
        shop1.buyAnimal(B);
        shop1.buyAnimal(Force);
        shop2.buyAnimal(Miracle);
        shop2.buyAnimal(A);
        shop2.buyAnimal(C);

        shop1.serveCustomer(Jackson);
        shop1.serveCustomer(Rose);
        shop2.serveCustomer(James);
        shop1.serveCustomer(Curry);
        shop2.serveCustomer(Harden);

        shop1.OutOfWork();
        shop2.OutOfWork();
    }
}
