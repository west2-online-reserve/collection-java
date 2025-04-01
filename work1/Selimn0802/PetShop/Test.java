package PetShop;

import java.time.LocalDate;

//测试类
public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(10000);
        //动物
        Animal d1 = new Dog("Sally","female",2, true);
        Animal d2 = new Dog("Tim", "male", 1, false);
        Animal d3 = new Dog("Lucy", "female", 3, true);
        Animal c1 = new Cat("Tom", "male", 3);
        Animal c2 = new Cat("Lily", "female", 2);
        Animal c3 = new Cat("Max", "male", 4);
        Hamster h1 = new Hamster("Jerry", "male",2 );
        Hamster h2 = new Hamster("Lisa", "female", 3);
        Hamster h3 = new Hamster("Jack", "male", 1);

        //顾客
        Customer cus1 = new Customer("Alice", 1, LocalDate.of(2025, 3, 1));
        Customer cus2 = new Customer("Bob", 2, LocalDate.now());
        Customer cus3 = new Customer("Carl", 0, LocalDate.now());

        //测试买入动物
        try {
            shop.buyNewAnimal(d1);
            shop.buyNewAnimal(d2);
            shop.buyNewAnimal(d3);
            shop.buyNewAnimal(c1);
            shop.buyNewAnimal(c2);
            shop.buyNewAnimal(c3);
            shop.buyNewAnimal(h1);
            shop.buyNewAnimal(h2);
            shop.buyNewAnimal(h3);
        }catch (InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }


        //测试招待顾客
        try {
            shop.serveCustomer(cus3, d1);
            shop.serveCustomer(cus2, c1);
            shop.serveCustomer(cus3, h1);
        }catch (InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }

        //测试卖出动物
        try {
            shop.sellAnimal(d2);
            shop.sellAnimal(c3);
        }
        catch (InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }

        //测试歇业
        shop.close();

        shop.showBalanceAfterBuy();
        shop.showBalanceAfterSell();
    }
}
