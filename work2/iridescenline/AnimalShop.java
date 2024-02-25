package Eyrine;

public interface AnimalShop {
    /*
    宠物店接口AnimalShop(interface)

你的宠物店需要有一些基础功能:

买入新动物(需要的参数自己决定)
招待客户(Customer)
歇业()
     */
    public void buyAnimal(Animal animal);
    public void treat(Customer customer);
    public void isClosed();
}
