package Test;

public interface AnimalShop {
    void buyNewPet(Animal animal) throws InsufficientBalanceException;
    void receiveGuests(Customer customer);
    void close();

}
