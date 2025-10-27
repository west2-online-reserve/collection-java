import Entity.Animal;
import Entity.Customer;

public interface AnimalShop {
    void buyNewAnimal(Animal animal);

    void serveCustomer(Customer customer, Animal animal);

    void close();
}
