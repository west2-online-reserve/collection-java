interface AnimalShop extends Exception{
    void buyAnimal(Animal animal) throws InsufficientBalanceException;

    void Customer(Customer customer) throws AnimalNotFoundException;

    void closedoor();
}
