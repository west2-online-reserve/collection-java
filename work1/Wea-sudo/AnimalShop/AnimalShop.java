package example;
import java.time.LocalDate;

public interface AnimalShop {
    void buyAnimal(Animal animal);
    void SolicitCustomer(Customer customer, String className, int price, LocalDate localDate);
    void close();
}
