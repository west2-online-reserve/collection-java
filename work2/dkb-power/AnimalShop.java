public interface AnimalShop {
    void buyAnimal(Animal animal) throws InsufficientBalanceException;

    void Customer(Customer customer) throws AnimalNotFoundException;

    void closedoor();
}

class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
