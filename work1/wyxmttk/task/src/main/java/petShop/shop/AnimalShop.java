package petShop.shop;

import petShop.customer.Customer;
import petShop.animal.Animal;

public interface AnimalShop {
    void buyAnimal(Animal animal);
    void entertainCustomer(Customer customer, long id);
    void close();
    void open();
}
