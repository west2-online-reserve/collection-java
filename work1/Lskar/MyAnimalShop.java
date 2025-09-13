import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animalList;
    private List<Customer> customerList;
    private boolean isOpen;
    private LocalDate nowDate;
    public MyAnimalShop(double balance,LocalDate nowDate) {
        this.animalList=new ArrayList<>();
        this.customerList=new ArrayList<>();
        this.isOpen=true;
        this.balance=balance;
        this.nowDate=nowDate;
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
        System.out.println("Available animals:");
        for(int i=0; i<animalList.size(); i++){
            System.out.println((i+1)+"."+animalList.get(i).toString());
        }
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter an animal number: ");
        int choice=scanner.nextInt();
        if(choice<1||choice>animalList.size()){
            throw new InvalidAnimalNumberException("Invalid animal number");
        }
        Animal soldAnimal = animalList.remove(choice-1);
        System.out.println("Sold animal: " + soldAnimal);
        balance += soldAnimal.getPrice();
        customerList.add(customer);
    }
    public void close() {
        if (isOpen) {
            System.out.println("Customers visited today:");
            for (Customer customer : customerList) {
                if(customer.getLastVisitDate().equals(nowDate)) {
                    System.out.println(customer.toString());
                }
            }
            System.out.println("Total profit for the day: " + balance);
            isOpen = false;
        } else {
            System.out.println("The shop is already closed.");
        }
    }
}
