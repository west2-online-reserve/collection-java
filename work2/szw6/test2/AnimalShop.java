package szw.test2;

//买入新动物(需要的参数自己决定)
//        招待客户(Customer)
//        歇业()
public interface AnimalShop {


    void buyAnimal(Animal newAnimal);

    void serveCustomer(Customer customer);
    void close();
}
