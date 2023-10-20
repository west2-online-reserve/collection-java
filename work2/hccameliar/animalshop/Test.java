package animalshop;

import java.time.LocalDate;

/**
 * @author 102301412
 * {@code @data} 2023/10/20
 */

public class Test {
    public static void main(String[] args) {
        MyAnimalShop hhh = new MyAnimalShop(1000.0);
        ChineseRuralDog dog1 = new ChineseRuralDog("二哈",5,'雄',true);
        Cat cat1 = new Cat("小花",3,'雌');
        Customer David = new Customer("David",LocalDate.of(2023,10,20));
        Customer Jane = new Customer("Jane",LocalDate.of(2023,10,21));

        try {
            hhh.purchase(dog1);
            hhh.purchase(cat1);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        try {
            hhh.treatCustomer(David ,"二哈");
            //购买不存在的动物，抛出异常
            hhh.treatCustomer(Jane,"旺财");
        } catch (AnimalNotFountException e) {
            System.out.println(e.getMessage());
        }

        hhh.close();
    }
}
