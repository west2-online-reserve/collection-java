import java.time.LocalDate;

public interface AnimalShop {
    void buyAnimal(Animal animal) throws InsufficientBalanceException;

    void serveCustomer(Customer customer) throws AnimalNotFoundException;

    void closeShop(LocalDate date);
}
