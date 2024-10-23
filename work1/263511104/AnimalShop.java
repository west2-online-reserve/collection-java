package work1;

import work1.Animal;
import work1.AnimalNotFoundException;

public interface AnimalShop {
    void buyAnimal(Animal animal) throws InsufficientBalanceException;
    void serveCustomer(Customer customer) throws AnimalNotFoundException;
    void closeShop();
}
