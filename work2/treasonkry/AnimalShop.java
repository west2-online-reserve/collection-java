public interface AnimalShop {
    void purchase(Animal animal) throws InsufficientBalanceException;
    void treatCustomer(Customer customer) throws AnimalNotFountException;
    void close();
}
