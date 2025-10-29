package shop.animalshop;

import com.java.pta.shop.animal.Animal;
import com.java.pta.shop.customer.Customer;

public interface AnimalShop {

    /* 购买动物 */
    void buyAnimal(Animal animal);

    /* 服务顾客  卖动物 */
    void serviceCustomer(Customer customer, String animalName);

    /* 歇业 */
    void close();


}
