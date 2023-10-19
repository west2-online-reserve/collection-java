import java.time.LocalDate;

public interface AnimalShop {
    void openShop(LocalDate openDate);
    void buyAnimals(Animal animal);

    void receiveCustomers(Customer customer, Animal animal);

    void closeShop();

}