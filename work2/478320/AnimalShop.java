package com.huayu.work02;

import java.time.LocalDate;

public interface AnimalShop {
    void purchaseNewAnimal(Animal animal, LocalDate importTime);
//这是宠物店的购买方法，参数一个是购买的宠物，一个是用于判断当天利润的进口时间
    void hospitalizingCustomers(Customer customer, int whichOneToPurchase, Animal animal);
//这是招待顾客的方法，参数是顾客的信息，和便于删除在动物列表中对应的索引的动物设立的购买的动物是第几只，还有动物的信息
    void closureOfBusiness(Customer... customer);
//这是歇业方法，形参是光顾的所有顾客，这个方法会判断符合当天关顾条件的顾客，用于打印光顾顾客的信息并把顾客加入顾客列表
}
