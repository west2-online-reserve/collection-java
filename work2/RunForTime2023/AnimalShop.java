public interface AnimalShop {
    void buyAnimals(Animal animal);

    void receiveCustomers(Customer customer, Animal animal);

    void closeShop();
}