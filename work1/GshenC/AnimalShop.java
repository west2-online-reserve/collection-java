public interface AnimalShop {
    void buyAnimal(Animal animal) throws InsufficientBalanceException;
    Animal attendCustomer(Customer customer) throws AnimalNotFoundException;
    void closeShop();
    void openShop();
}
