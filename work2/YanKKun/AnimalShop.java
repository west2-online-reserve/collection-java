package Animal;

/**
 *
 * @author 12080
 * 动物商店接口
 *
 **/
public interface AnimalShop {
    void buyAnimals(Animal animal,int number);
    //买入动物
    void serverCustomers(Customer customer,Animal animal);
    //招待顾客
    boolean close();
    //歇业
    boolean open();
    //开业
}
