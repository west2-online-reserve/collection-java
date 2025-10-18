package example;
import java.time.LocalDate;

public interface AnimalShop {
    void buyAnimal(Animal animal);
    void solicitCustomer(Customer customer, Animal animal, int price, LocalDate localDate);
    void close();
}
