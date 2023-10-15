package Animal;

import java.time.LocalDate;

public interface AnimalShopinterface {
    void purchase(Animal d, int num);

    void treat(Customer s, Animal pet);

    void isClosed();
    void isOpen();
}
