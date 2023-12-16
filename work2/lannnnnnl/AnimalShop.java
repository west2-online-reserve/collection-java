package westwork2;

public interface AnimalShop {
    void purchaseAnimal(Animal animal);
    void receiveCustomer(Customer customer, Animal animal);
    void close();
    void reopen();

}
