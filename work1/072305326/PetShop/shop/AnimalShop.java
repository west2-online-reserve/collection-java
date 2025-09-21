package com.cyx.Amimals;

public interface AnimalShop {
    // 买入新动物
    public void buyAnimal(Animal animal);
    // 招待客户
    public void serverCustomer(Customer customer);
    // 歇业
    public void close();
}
