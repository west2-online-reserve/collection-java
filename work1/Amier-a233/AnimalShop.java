public interface AnimalShop {
    /*买入新动物(需要的参数自己决定)
    招待客户(Customer)
    歇业()*/
    void buyNewAnimal(Animal animal);
    void serveCustomer(Customer customer)throws AnimalNotFoundException, InvalidAnimalIndexException;
    void closeShop();
}
