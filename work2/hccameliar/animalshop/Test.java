package animalshop;

/**
 * @author 102301412
 * {@code @data} 2023/10/20
 */

public class Test {
    public static void main(String[] args) {
        // 创建宠物店、宠物和顾客
        MyAnimalShop hhh = new MyAnimalShop(400.0);
        ChineseRuralDog dog1 = new ChineseRuralDog("二哈", 5, '雄', true);
        Cat cat1 = new Cat("小花", 3, '雌');
        Cat cat2 = new Cat("小宝", 4, '雌');
        Customer David = new Customer("David");
        Customer Jane = new Customer("Jane");
        Customer Lee = new Customer("Lee");

        // 购买宠物
        try {
            hhh.purchase(dog1);
            hhh.purchase(cat1);
            // 余额不足无法购买
            hhh.purchase(cat2);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        // 招待顾客
        try {
            hhh.treatCustomer(David, "二哈");
            hhh.treatCustomer(Lee, "小花");
            // 购买不存在的动物，抛出异常
            hhh.treatCustomer(Jane, "旺财");
            // 动物已卖完，抛出异常
            hhh.treatCustomer(Jane, "小花");
        } catch (AnimalNotFountException e) {
            System.out.println(e.getMessage());
        }

        // 关店
        hhh.close();
    }
}
