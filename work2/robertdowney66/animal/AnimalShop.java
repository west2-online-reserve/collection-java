package com.west2.check02;

import java.time.LocalDate;

/**
 * @author yuyu
 */
public interface AnimalShop {
    /***
     * 方法实现购入动物
     * 由于后续需要进行日利润的计算，所以在此处增设购入日期变量
     * 从而实现日利润计算
     * @param animal
     * @param purchaseDate
     */
    public void purchaseOfNewAnimal(AbstractAnimal animal, LocalDate purchaseDate);

    /***
     * 方法实现招待顾客
     * 加入变量disiredAnimals确定顾客所心仪动物
     * @param customer
     * @param disiredAnimal
     */
    public void treatCustomers(Customer customer, AbstractAnimal disiredAnimal);

    /***
     * 方法实现关闭摊位和返回当日顾客和利润操作
     */
    public void closureOfBusiness();
}
