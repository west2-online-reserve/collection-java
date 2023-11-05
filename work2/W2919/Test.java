package work2;

import java.time.LocalTime;


public class Test {
    public static void main(String[] args) {
        MyAnimalShop a = new MyAnimalShop("动物园", 100, true);
        MyAnimalShop b = new MyAnimalShop("宠物店", 9000, true);
        MyAnimalShop c = new MyAnimalShop("废墟", 0, false);

        ChineseDog dog1 = new ChineseDog("1", 100, "male", 100, true);
        ChineseDog dog2 = new ChineseDog("2", 12, "female", 100, false);
        ChineseDog variantDog = new ChineseDog("大哥哥", 1, "?", 8000, false);
        Cat cat1 = new Cat("a", 1, "female", 200);
        Cat cat2 = new Cat("b", 299, "female", 200);
        Customer c1 = new Customer("father", 100, LocalTime.now());
        Customer c2 = new Customer("sister", 0, LocalTime.now());

        a.buying(dog1);
        a.buying(cat1);
        b.buying(dog1);
        b.buying(dog2);
        b.buying(cat1);
        b.buying(cat2);
        b.buying(variantDog);
        System.out.println("==================================================");

        a.entertainCustomer(c1, 1);
        a.entertainCustomer(c2, 1);
        b.entertainCustomer(c1, 5);
        b.entertainCustomer(c2, 1);
        c.entertainCustomer(c1, 1);
        System.out.println("==================================================");


        a.close();
        System.out.println("==================================================");
        b.close();


    }
}
