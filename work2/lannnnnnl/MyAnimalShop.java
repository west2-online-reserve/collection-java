package westwork2;

import java.util.ArrayList;
import java.util.List;

//宠物店实现
public class MyAnimalShop implements AnimalShop {
 private double balance;
 private List<Animal> animalList = new ArrayList<>();
 private List<Customer> customerList = new ArrayList<>();
 private boolean isOpen;

 MyAnimalShop(double balance) {
     this.balance = balance;
     this.isOpen = true;
 }

 @Override
 public void buyNewAnimal(Animal animal) throws InsufficientBalanceException {
     if (this.balance < animal.price) {
         throw new InsufficientBalanceException("余额不足，无法买入新动物。");
     }
     this.balance -= animal.price;
     this.animalList.add(animal);
 }

 @Override
 public void serveCustomer(Customer customer, String animalType) throws AnimalNotFoundException {
     if (this.animalList.isEmpty()) {
         throw new AnimalNotFoundException("店内没有动物可卖。");
     }

     Animal animalToSell = null;
     for (Animal animal : this.animalList) {
         if (animal.getClass().getSimpleName().equals(animalType)) {
             animalToSell = animal;
             break;
         }
     }

     if (animalToSell == null) {
         throw new AnimalNotFoundException("没有找到指定类型的动物。");
     }

     this.balance += animalToSell.price;
     this.animalList.remove(animalToSell);
     this.customerList.add(customer);
     System.out.println(animalToSell.toString());
 }

 @Override
 public void closeShop() {
     this.isOpen = false;
     for (Customer customer : this.customerList) {
         System.out.println(customer.toString());
     }
     // 假设这里输出一天的利润
     System.out.println("今日利润: " + this.balance);
 }
}
