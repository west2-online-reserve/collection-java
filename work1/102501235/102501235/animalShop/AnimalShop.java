package animalShop;

import java.time.LocalDate;

public interface AnimalShop {
    void buyNewAnimal(Animal animal);
    void serveCustomer(Customer customer, String animalName);
    void closeShop(LocalDate today);
}
