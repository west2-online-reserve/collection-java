package westwork2;

//宠物店接口
public interface AnimalShop {
 void buyNewAnimal(Animal animal) throws InsufficientBalanceException;
 void serveCustomer(Customer customer, String animalType) throws AnimalNotFoundException;
 void closeShop();
}
