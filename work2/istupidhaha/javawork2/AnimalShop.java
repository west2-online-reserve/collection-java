package com.west2.javawork2;

//你的宠物店需要有一些基础功能:
//买入新动物(需要的参数自己决定)
//招待客户(Customer)
//歇业()
public interface AnimalShop {
    void purchase(Animal animal);
    void receiveCustomer(Customer customer);
    void close();

    void open();
}
