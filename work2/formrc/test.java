package java_work2;

import java.time.LocalDate;

/**
 * test类
 *
 * @author formrc
 * @date 2023/10/30
 */
public class test {
    public static void main(String[] args) {
        MyAnimalShop animalShop = new MyAnimalShop(225.0);

        try {
            Animal dog = new ChineseRuralDog("Bobby", 2, "Male", true);
            animalShop.buyAnimal(dog);

            Animal cat1 = new Cat("Whiskers", 3, "Female");
            animalShop.buyAnimal(cat1);

            Animal parrot=new Parrot("Jude",5, "Male",true);
            animalShop.buyAnimal(parrot);

            //  购买第四个动物时余额不足，抛出异常，并输出无法购买的动物的信息
            Animal cat2 = new Cat("Kitty", 4, "Female");
            animalShop.buyAnimal(cat2);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        try {
            //  若顾客成功购买，则输出顾客信息及该顾客所买动物的信息
            Customer customer1 = new Customer("John", 3, LocalDate.now());
            animalShop.serveCustomer(customer1);

            Customer customer2 = new Customer("Alice", 7, LocalDate.now());
            animalShop.serveCustomer(customer2);

            Customer customer3 = new Customer("Yenefa", 2, LocalDate.now());
            animalShop.serveCustomer(customer3);

            //  第四个人购买时店内没有动物，抛出异常并输出购买失败的顾客的信息
            Customer customer4 = new Customer("Gerout", 5, LocalDate.now());
            animalShop.serveCustomer(customer4);
        } catch (AnimalNotFoundException e) {
            System.out.println(e.getMessage());
        }

        animalShop.closeBusiness();
    }
}
