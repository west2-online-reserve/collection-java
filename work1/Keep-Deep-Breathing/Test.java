import Entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import Exception.InsufficientBalanceException;
import Exception.AnimalNotFountException;

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
        try {
            shop.buyNewAnimal(cat);
        } catch (Exception e) {
            System.out.println("购买猫失败：余额不足");
            e.printStackTrace();
        }

        shop.serveCustomer(c2, rabbit);
        try{
            shop.serveCustomer(c3, cat);
        } catch (Exception e) {
            System.out.println("服务失败：动物不存在");
            e.printStackTrace();
        }


        shop.buyNewAnimal(dog2);

//        -150-100+150-100 = -200
        shop.close();
    }
}