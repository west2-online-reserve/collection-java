public interface AnimalShop {
    void shopClose();

    void buyAnimal(Animal animal, int quantity) throws InsufficientBalanceException;

    void treatCustomer(Customer customer, String animalType, int index) throws AnimalNotFoundException;
}

