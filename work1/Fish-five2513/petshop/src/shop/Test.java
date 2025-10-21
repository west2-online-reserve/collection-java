package shop;
// Test.java
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        try {
            // 创建宠物店实例
            MyAnimalShop shop = new MyAnimalShop(1000.0);

            // 买入动物
            shop.buyAnimal(new ChineseRuralDog("旺财", 2, "公", true));
            shop.buyAnimal(new Cat("咪咪", 1, "母"));
            shop.buyAnimal(new Rabbit("跳跳", 1, "公"));

            // 招待顾客
            Customer customer1 = new Customer("张三");
            Customer customer2 = new Customer("李四");
            shop.serveCustomer(customer1);
            shop.serveCustomer(customer2);

            // 歇业
            shop.closeShop();

        } catch (InsufficientBalanceException | AnimalNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}


