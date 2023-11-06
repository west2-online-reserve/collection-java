
public interface AnimalShop {
    void buyAnimal(Animal animal);

    void serveCustomer(Customer customer) throws AnimalNotFoundException;

    void entertainCustomer(Customer customer);
    void stop();
}
