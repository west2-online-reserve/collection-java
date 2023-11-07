package work2;

import java.time.LocalDate;
import java.util.ArrayList;

public interface AnimalShop {
    public abstract void buyAnimal(Animal a);

    public abstract Animal entertainCustomer(Customer customer);

    public abstract void close(LocalDate tim,ArrayList<Customer> customerse,ArrayList<Animal> Animals,MyAnimalShop shop);
}
