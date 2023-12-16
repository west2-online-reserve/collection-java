package java_work2;

/**
 * AnimalShop接口
 *
 * @author formrc
 * @date 2023/10/25
 */
interface AnimalShop {
    void buyAnimal(Animal animal) throws InsufficientBalanceException;
    void serveCustomer(Customer customer) throws AnimalNotFoundException;
    void closeBusiness();
}