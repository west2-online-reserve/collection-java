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
  public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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
 public void serveCustomer(Customer customer, Animal animal) throws AnimalNotFoundException {
      if(!isOpen){
            System.out.println("The shop is closed!");
            return;
        }
        if(this.animalList.contains(animal)) {
            this.animalList.remove(animal);
            this.setBalance(this.getBalance() + animal.getPrice());
            System.out.println(animal.toString());
            customer.setLatestArrivedTime(LocalDate.now());
            customer.setTimes(customer.getTimes() + 1);
            if (!this.customerList.contains(customer)) {
                this.customerList.add(customer);
            }
        }
        else {
            throw new AnimalNotFoundException(animal);
        }
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
