package PetShop;

import java.util.ArrayList;

/**
 * AnimalShop接口表示宠物店
 *
 * 该接口包含宠物店的基本信息和操作
 * @author Jst599
 * @date 2023/10/17
 */
public interface AnimalShop {
    /**
     * 买入新宠物,招待客户,歇业
     */
    void buyAnimal(Animal animal);


    /**
     * 招待顾客
     */
    void serveCustomer(Customer customer);

    /**
     * 歇业
     */
     void OutOfWork();


}
