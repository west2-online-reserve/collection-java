package westwork2;

//宠物店接口
interface AnimalShop {
 void buyNewAnimal(Animal animal) throws InsufficientBalanceException;
 void serveCustomer(Customer customer, String animalType) throws AnimalNotFoundException;
 void closeShop();
}
