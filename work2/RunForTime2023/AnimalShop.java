import java.time.LocalDate;

public interface AnimalShop {
    @Override
    void openShop(LocalDate openDate);

    @Override
    void buyAnimals(Animal animal);

    @Override
    void receiveCustomers(Customer customer, Animal animal);

    @Override
    void closeShop();
}
