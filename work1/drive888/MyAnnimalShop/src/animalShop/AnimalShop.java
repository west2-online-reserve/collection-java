package animalShop;

import animal.Animal;
import customer.Customer;

public interface AnimalShop {
    /**
     * 买入一只动物
     * @param animal 买入动物
     */
     void buyNewAnimal(Animal animal);



    /**
     * 招待客户
     *
     * @param customer
     * @return 欢迎语
     */
    String welcome(Customer customer);

    /**
     *
     * @param customer
     * @param animalName CRdog:中华田园犬 Cat:猫
     * @return
     */
    String sale (Customer customer, String animalName );


    /**
     * 开业
     */
    void open();


    /**
     * 歇业
     */
    void stop();

}
