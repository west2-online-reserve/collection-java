import java.util.ArrayList;

public interface AnimalShop {
    void buyNewAnimal(Animal animal);
    void tradeCustomer( ArrayList<Customer> customers, ArrayList<Animal> animals);
    void closeShop();
}
