package PetShop;

public interface AnimalShop {
    void buyAnimal(Animal animal) throws InsufficientBalanceException;
    void serveCustomer(Customer customer);
    void close();
}
