package shop;

import com.java.pta.shop.animal.Cat;
import com.java.pta.shop.animal.ChineseNativeDog;
import com.java.pta.shop.animal.Duck;
import com.java.pta.shop.animalshop.MyAnimalShop;
import com.java.pta.shop.customer.Customer;

import java.time.LocalDate;

/**
 * Describe:
 *
 * @author cky
 * @date 2025/10/22
 */
public class ShopTest {
    public static void main(String[] args) {

        //初始化宠物店
        MyAnimalShop animalShop = new MyAnimalShop("QQ宠物店", 250);
        animalShop.getListOfAnimals().clear();
        animalShop.getListOfAnimals().add(new ChineseNativeDog("小黄", 1, "母", 100, true));
        animalShop.getListOfAnimals().add(new ChineseNativeDog("旺财", 2, "公", 100, true));
        animalShop.getListOfAnimals().add(new Cat("团子", 2, "公", 200));
        animalShop.getListOfCustomers().clear();

        //买入动物
        animalShop.buyAnimal(new Cat("小猫", 1, "母", 200));
        animalShop.buyAnimal(new Duck("小黄", 1, "母", 66));

        //顾客小明
        Customer customerA = new Customer("小明", 1, LocalDate.now());
        animalShop.serviceCustomer(customerA, "小黄");

        //顾客小红
        Customer customerB = new Customer("小红", 1, LocalDate.now());
        animalShop.serviceCustomer(customerB, "团子");

        //歇业
        animalShop.close();

    }
}
