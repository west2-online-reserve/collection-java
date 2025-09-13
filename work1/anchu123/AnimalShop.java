/*宠物商店基本功能的接口*/
public interface AnimalShop {
    //购买宠物
    void buyAnimal(Animal animal);

    //招待顾客
    void serveCustomer(Customer customer, Animal animal);

    //暂停营业
    void closeShop();
}
