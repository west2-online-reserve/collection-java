public interface AnimalShop {

    // 买入新动物
    void addAnimal(Animal animal) throws InsufficientBalanceException;

    // 招待客户
    void addCustomer(Customer customer, Animal animal);

    // 歇业
    void close();
}
