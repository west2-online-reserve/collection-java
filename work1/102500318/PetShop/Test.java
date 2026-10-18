package oop.PetShop;

import java.util.ArrayList;

/**
 * PetShop 的一组例子。
 *
 */
public class Test {

    public static void main(String[] args) {
        example1_buyOneDog();
        example2_buyAndSell();
        example3_sellOutAll();
        example4_sellTheLastOne();
        example5_emptyShop();
        example6_sameCustomerTwice();
        example7_businessAfterClosed();
        example8_profitOfOneDay();
    }

    /** 例 1：1000 元的店，买一只 100 元的狗 */
    private static void example1_buyOneDog() {
        title("例 1：买一只狗");

        MyAnimalShop shop = new MyAnimalShop(1000, new ArrayList<>());
        Dog dog = new Dog("旺财", 3, "公", true);

        shop.buyAnimal(dog);                                // 买进一只狗
        System.out.println("买进成功：" + dog);
    }

    /** 例 2：先进狗、再进猫，顾客小明买走一只 */
    private static void example2_buyAndSell() {
        title("例 2：卖出一只动物");

        MyAnimalShop shop = new MyAnimalShop(1000, new ArrayList<>());
        shop.buyAnimal(new Dog("旺财", 3, "公", true));      // 进货
        shop.buyAnimal(new Cat("咪咪", 2, "母"));            // 进货
        System.out.println("进货顺序：旺财(狗) → 咪咪(猫)");

        shop.serveCustomer(new Customer("小明"));            // 卖出一只，shop 会自己打印卖掉的那只
    }

    /** 例 3：把 3 只动物全部卖光，第 4 位顾客没货可买 */
    private static void example3_sellOutAll() {
        title("例 3：卖光之后再来顾客");

        MyAnimalShop shop = new MyAnimalShop(1000, new ArrayList<>());
        shop.buyAnimal(new Dog("旺财", 3, "公", true));
        shop.buyAnimal(new Cat("咪咪", 2, "母"));
        shop.buyAnimal(new Cat("花花", 1, "母"));

        for (int i = 1; i <= 3; i++) {
            shop.serveCustomer(new Customer("顾客" + i));    // 卖光 3 只
        }

        try {
            shop.serveCustomer(new Customer("顾客4"));       // 第 4 位顾客：店里已空
        } catch (AnimalNotFoundException e) {
            System.out.println("第 4 位顾客没买到：" + e.getMessage());
        }
    }

    /** 例 4：店里只有最后一只时卖出 */
    private static void example4_sellTheLastOne() {
        title("例 4：只剩最后一只");

        MyAnimalShop shop = new MyAnimalShop(1000, new ArrayList<>());
        shop.buyAnimal(new Cat("咪咪", 2, "母"));             // 只进一只

        shop.serveCustomer(new Customer("小明"));            // 把它卖掉
    }

    /** 例 5：一只动物都没有的店，顾客进来 */
    private static void example5_emptyShop() {
        title("例 5：空店服务顾客");

        MyAnimalShop shop = new MyAnimalShop(1000, new ArrayList<>());

        try {
            shop.serveCustomer(new Customer("小明"));
        } catch (AnimalNotFoundException e) {
            System.out.println("小明没买到：" + e.getMessage());
        }
    }

    /** 例 6：同一位顾客今天来两次，歇业时看顾客名单 */
    private static void example6_sameCustomerTwice() {
        title("例 6：同一位顾客来两次");

        MyAnimalShop shop = new MyAnimalShop(1000, new ArrayList<>());
        shop.buyAnimal(new Dog("旺财", 3, "公", true));
        shop.buyAnimal(new Cat("咪咪", 2, "母"));
        shop.buyAnimal(new Cat("花花", 1, "母"));

        shop.serveCustomer(new Customer("小明"));            // 第一次到访
        shop.serveCustomer(new Customer("小明"));            // 第二次到访（同名顾客）

        System.out.println("歇业，打印今天的到访名单（姓名 次数 日期）和净利润：");
        shop.closed();
    }

    /** 例 7：歇业之后还想继续做生意 */
    private static void example7_businessAfterClosed() {
        title("例 7：歇业后再买狗");

        MyAnimalShop shop = new MyAnimalShop(1000, new ArrayList<>());
        shop.closed();                                       // 先歇业

        try {
            shop.buyAnimal(new Dog("旺财", 3, "公", true));
            System.out.println("歇业后再买狗：没有任何反应（被静默忽略）");
        } catch (RuntimeException e) {
            System.out.println("歇业后再买狗：被拒绝 → " + e.getClass().getSimpleName());
        }
    }

    /** 例 8：一天营业结束后的净利润 */
    private static void example8_profitOfOneDay() {
        title("例 8：一天营业，最后结算净利润");

        MyAnimalShop shop = new MyAnimalShop(1000, new ArrayList<>());
        shop.buyAnimal(new Dog("旺财", 3, "公", true));       // 成本 100
        shop.buyAnimal(new Cat("咪咪", 2, "母"));             // 成本 200
        shop.serveCustomer(new Customer("小明"));             // 卖出一只（咪咪），收入 200

        System.out.println("歇业结算（最后一行就是净利润 = 收入 200 - 成本 300）：");
        shop.closed();
    }

    /** 打印例子标题 */
    private static void title(String text) {
        System.out.println();
        System.out.println("==================================================");
        System.out.println(text);
        System.out.println("==================================================");
    }
}
