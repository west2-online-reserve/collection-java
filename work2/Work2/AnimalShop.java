package Work2;

public interface AnimalShop {



    ;

    void buy(Animal animal) throws InsufficientBalanceException;

    void recepte(Customer customer) throws AnimalNotFoundException;
    void close();
}
