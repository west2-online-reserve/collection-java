/**
 *@Date：2023/10/18
 *@Author：XWBN
 */
package XWBN2;


public interface AnimalShop {
    void buyNewAnimal(Animal animal);

    void entertainCustomer(Customer customer, Animal animal);

    void start();

    void close();
}
