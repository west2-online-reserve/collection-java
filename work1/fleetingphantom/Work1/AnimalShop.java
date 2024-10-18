package Work1;

import java.util.ArrayList;

public interface AnimalShop {
    void buyAnimals(ArrayList<Animal> marketAnimals, int num);

    void serveCustomer(Customer customer, int num);

    void closeShop();
}
