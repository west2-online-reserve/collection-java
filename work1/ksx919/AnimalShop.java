public interface AnimalShop{
    void buyAnimal(Animal animal);
    void serveCustomer(Customer customer,Animal animal) throws AnimalNotFoundException;
    void closeShop();
}