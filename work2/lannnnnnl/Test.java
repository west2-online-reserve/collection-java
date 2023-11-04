package westwork2;

import java.time.LocalDate;

//测试类
public class Test {
 public static void main(String[] args) {
     MyAnimalShop shop = new MyAnimalShop(1000.0); // 初始化宠物店，余额1000元

     try {
         // 买入动物
         shop.buyNewAnimal(new ChineseRuralDog("旺财", 2, 'M', true));
         shop.buyNewAnimal(new Cat("喵喵", 1, 'F'));
         shop.buyNewAnimal(new Rabbit("兔子", 1, 'M'));

         // 招待顾客
         Customer customer1 = new Customer("张三", 1, LocalDate.now());
         shop.serveCustomer(customer1, "ChineseRuralDog");

         // 歇业
         shop.closeShop();

     } catch (AnimalNotFoundException | InsufficientBalanceException e) {
         System.err.println(e.getMessage());
     }
 }
}