public interface AnimalShop {
    void buyAnimal(Animal animal) throws InsufficientBalanceException;

    void serveCustomer(Customer customer, int index) throws AnimalNotFoundException;

    void closeShop();
}
