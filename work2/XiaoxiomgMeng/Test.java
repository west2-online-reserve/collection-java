import java.util.ArrayList;
import java.util.Scanner;
/**
 * @author XiaoxiongMeng
 */

public class Test {


    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop();
        Customer customer1 = new Customer("张三");
        Customer customer2 = new Customer("李四");
        Customer customer3 = new Customer("王五");
        CuteCats cat1 = new CuteCats("Dio an", 16, 0);
        CuteCats cat2 = new CuteCats("Lynette", 18, 0);
        ChineseFieldDog d1 = new ChineseFieldDog("WuLang", 19, 1);
        ChineseFieldDog d2 = new ChineseFieldDog("WangChai", 4, 1);
        Panda panda1 = new Panda("MengLan", 8, 1);
        shop.buy(panda1);
        shop.buy(cat1);
        shop.buy(cat2);
        shop.buy(d1);
        shop.buy(d2);
        shop.welcome(customer1);
        shop.welcome(customer2);
        shop.welcome(customer3);
        shop.close();
    }
}