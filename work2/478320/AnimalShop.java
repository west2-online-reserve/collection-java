package com.huayu.work02;

/**
 * AnimalShop接口包含系统中的关键方法
 *
 * 该接口用于约束继承它的类，实现内部的关键购买动物，招待顾客，和歇业方法
 * @author yusiheng
 * @date 2023/10/04
 */
import java.time.LocalDate;

public interface AnimalShop {
    void purchaseNewAnimal(Animal animal, LocalDate importTime);

    void hospitalizingCustomers(Customer customer, int whichOneToPurchase, Animal animal);

    void closureOfBusiness(Customer... customer);

}
