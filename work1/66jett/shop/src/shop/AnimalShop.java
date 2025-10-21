package shop;

import java.util.List;

/**
 * 宠物店接口
 */
public interface AnimalShop {

    /**
     * 买入新动物
     *
     * @param animal 动物
     */
    void buyNewAnimal(Animal animal);

    /**
     * 招待客户
     *
     * @param customer 顾客
     * @param animalName 要购买的动物名
     */
    void serveCustomer(Customer customer, String animalName);

    /**
     * 歇业
     */
    void closeShop();
}