import java.util.ArrayList;
import java.util.List;
public class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animalList;
    private List<Customer> customerList;
    private boolean isOpen;
    public MyAnimalShop(double balance) {
        this.animalList=new ArrayList<>();
        this.customerList=new ArrayList<>();
        this.isOpen=true;
        this.balance=balance;
    }
    @Override
    public void buyAnimal(Animal animal) {
        if(balance<animal.getPrice()){
            throw new InsufficientBalanceException("Insufficient balance to buy this animal");
        }
        animalList.add(animal);
        balance -= animal.getPrice();
    }
    public void treatCustomer(Customer customer) {
        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("No animals available in the shop.");
        }
        Animal soldAnimal = animalList.removeFirst();
        System.out.println("Sold animal: " + soldAnimal);
        balance += soldAnimal.getPrice();
        customerList.add(customer);
    }
    public void close() {
        if (isOpen) {
            System.out.println("Customers visited today:");
            for (Customer customer : customerList) {
                System.out.println(customer.toString());//输出顾客所有信息
            }
            System.out.println("Total profit for the day: " + balance);
            isOpen = false;
        } else {
            System.out.println("The shop is already closed.");
        }
    }
}
