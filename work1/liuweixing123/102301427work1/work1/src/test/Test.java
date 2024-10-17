package test;

import base.AbstractAnimal;
import pojo.Cat;
import pojo.Customer;
import pojo.Dog;
import pojo.MyAnimalShop;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        //创建一些动物实例
        Cat cat1 = new Cat("胖猫", 18, "母");
        Cat cat2 = new Cat("科比布莱恩特", 24, "超雄");
        Dog dog1 = new Dog("大黄", 10, "母", true);
        Dog dog2 = new Dog("小黑", 4, "雄", false);
        Dog dog3 = new Dog("魏云", 20, "超雄", false);

        ArrayList<AbstractAnimal> Pets = new ArrayList<>();
        Pets.add(cat1);
        Pets.add(cat2);
        Pets.add(dog1);
        Pets.add(dog2);

        //创建我的宠物店对象
        MyAnimalShop shop = new MyAnimalShop(50, Pets, true);

        //来了一批客户
        Customer customer1 = new Customer("姜奥", 7, LocalDate.of(2024, 9, 12));
        Customer customer2 = new Customer("林俊明", 0, null);

        //招待客户
        shop.welcomeClients(customer1);

        //买入宠物
        shop.buy(dog3);

        //招待客户
        shop.welcomeClients(customer2);
        shop.welcomeClients(customer2);
        shop.welcomeClients(customer2);

        //歇业
        shop.close();
        System.out.println(shop);
    }
}
