package Interface;

import Instance.Animal;
import Instance.Customers.Customer;

public interface AnimalShop {
    public void buyAnimal(Animal animal,int number);
    public void welcomeCustomer(Customer customer,Animal animal);
    public void closeShop();
}
