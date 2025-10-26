package PetShop;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(1000.0);

        try {
            // 购买动物
            shop.buyAnimal(new ChineseRuralDog("小黄", 2, "公", true));
            shop.buyAnimal(new Cat("小白", 1, "母"));
            shop.buyAnimal(new Rabbit("小灰", 6, "母", "灰色"));

            // 招待顾客
            Customer c1 = new Customer("张三", LocalDate.now());
            Customer c2 = new Customer("李四", LocalDate.now());
            shop.serveCustomer(c1);
            shop.serveCustomer(c2);
            shop.serveCustomer(c1); // 再次访问

            // 关闭店铺
            shop.close();

        } catch (InsufficientBalanceException | AnimalNotFoundException e) {
            System.err.println("异常：" + e.getMessage());
        }
    }
}
