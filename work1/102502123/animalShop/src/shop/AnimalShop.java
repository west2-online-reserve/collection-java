package shop;

public interface AnimalShop {
    void buyNewAnimal(Animal animal,int quantity);
    void serveCustomer(Customer customer);
    void closeShop();
}
