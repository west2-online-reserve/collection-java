package Animal;

/**
 *
 * @author 12080
 * 动物商店接口
 *
 **/
public interface AnimalShop {
    //买入动物
    void buyAnimals(Animal animal,int number);
    //招待顾客
    void serverCustomers(Customer customer,Animal animal);
    //歇业
    boolean close();
    //开业
    boolean open();
}
