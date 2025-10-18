package example;
import java.time.LocalDate;

public interface AnimalShop {
    void buyAnimal(Animal animal);
    void solicitCustomer(Customer customer, Class<? extends Animal> animalTpye, double price, LocalDate localDate);
    void close();
}
