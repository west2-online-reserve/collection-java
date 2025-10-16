import Entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(300.0, 0.0, new ArrayList<Animal>(), new ArrayList<Customer>(),
                true);

        Rabbit rabbit = new Rabbit("兔子", 1, 0);
        Dog dog = new Dog("修狗", 4, 1, true);
        Dog dog2 = new Dog("修狗2号", 5, 0, true);
        Cat cat = new Cat("猫咪", 2, 1);

        Customer c1 = new Customer("客人1号", 1, LocalDate.of(2023, 10, 5) );
        Customer c2 = new Customer("客人2号", 2, LocalDate.of(2025, 9, 20) );
        Customer c3 = new Customer("客人3号", 3, LocalDate.now());

        shop.buyNewAnimal(rabbit);
        shop.buyNewAnimal(dog);
//        余额不足异常
//        shop.buyNewAnimal(cat);

        shop.serveCustomer(c2, rabbit);
//        不存在这个宠物的异常
//        shop.serveCustomer(c3, cat);

        shop.buyNewAnimal(dog2);

//        -150-100+150-100 = -200
        shop.close();
    }
}