package myshop;

public interface AnimalShop {
    //宠物店要有三种基础功能:

    //买入动物
    void buyAnimal(MyAnimalShop myAnimalShop);

    //招待客户
    void serveCustomer(MyAnimalShop myAnimalShop);

    //歇业
    void close(MyAnimalShop myAnimalShop);
}
