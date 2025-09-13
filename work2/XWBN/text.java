/**
 *@Date：2023/10/18
 *@Author：XWBN
 */
package XWBN2;

public class text {
    public static void main(String[] args) {
        MyAnimalShop shop1 = new MyAnimalShop("shop1", 10000);
        shop1.start();
        MyAnimalShop shop2 = new MyAnimalShop("shop2", 20);
        shop2.start();
        CNDog dog1 = new CNDog("dog1", 3, "male", 50);
        CNDog dog2 = new CNDog("dog2", 5, "female", 70);
        Cat cat1 = new Cat("cat1", 4, "male", 120);
        Cat cat2 = new Cat("cat2", 6, "female", 95);
        Beer beer1 = new Beer("beer1", 4, "male", 250);
        Customer customer1 = new Customer("customer1", 0);
        Customer customer2 = new Customer("customer2", 1);
        Customer customer3 = new Customer("customer3", 0);
        shop1.buyNewAnimal(dog1);
        shop1.buyNewAnimal(cat1);
        shop2.buyNewAnimal(dog2);
        shop1.entertainCustomer(customer2, beer1);
        shop1.entertainCustomer(customer1, dog1);
        shop1.entertainCustomer(customer1, cat1);
        shop2.entertainCustomer(customer2, dog2);
        shop1.close();
        shop2.close();
        shop1.entertainCustomer(customer3, cat1);
    }
}




