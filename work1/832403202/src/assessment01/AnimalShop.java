package assessment01;

public interface AnimalShop {
    void buyAnimal(Animal animal) throws InsufficientBalanceException;
    void attendCustomer(Customer customer) throws AnimalNotFoundException;
    void closeShop();
}
