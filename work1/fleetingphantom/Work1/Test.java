package Work1;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        //顾客
        Customer c1 = new Customer("张三", 2023, 10, 12);
        Customer c2 = new Customer("李四");
        Customer c3 = new Customer("张三");
        Customer c4 = new Customer("王五");
        Customer c5 = new Customer("赵六");
        //动物市场
        ArrayList<Animal> marketAnimals = new ArrayList<>();
        ChineseFieldDog dog1 = new ChineseFieldDog("狗剩", 3, "雄性", true);
        ChineseFieldDog dog2 = new ChineseFieldDog("Dog", 5, "雌性", true);
        Cat cat1 = new Cat("柴郡", 4, "雌性");
        Cat cat2 = new Cat("Cat", 2, "雄性");
        Hamster hamster = new Hamster("Mouse", 2, "雄性", 123, 0.6);
        marketAnimals.add(dog1);
        marketAnimals.add(dog2);
        marketAnimals.add(cat1);
        marketAnimals.add(cat2);
        marketAnimals.add(hamster);
        MyAnimalShop shop = new MyAnimalShop(1000);
        try {
            shop.buyAnimals(marketAnimals, 5);
            shop.buyAnimals(marketAnimals, 1);
            shop.buyAnimals(marketAnimals, 3);
            shop.buyAnimals(marketAnimals, 2);
            shop.buyAnimals(marketAnimals, 1);
        } catch (RuntimeException e) {
            throw e;
        }
        try {
            shop.serveCustomer(c1, 5);
            shop.serveCustomer(c2, 2);
            shop.serveCustomer(c3, 3);
            shop.serveCustomer(c4, 2);
            shop.serveCustomer(c5, 1);
        } catch (RuntimeException e) {
            System.out.println("---------------------------------");
            throw e;
        }

        shop.closeShop();
    }
}
