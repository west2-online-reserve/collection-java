import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    protected double balance;
    protected List<Animal> animalList;
    protected List<Customer> customerList;
    private boolean isOpen;

    public MyAnimalShop() {
        this.balance = 0.0;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if (balance < animal.getCost()) {
            throw new InsufficientBalanceException("Insufficient balance to buy the animal.");
        }

        balance -= animal.getCost();
        animalList.add(animal);
    }

    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFoundException {
        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("No animals available for sale.");
        }

        Animal animal = animalList.remove(0);
        System.out.println("Selling animal: " + animal.toString());

        balance += animal.getCost();
        customerList.add(customer);
    }

    public void entertainCustomer(Customer customer) {
        System.out.println("Entertaining customer: " + customer.toString());
    }

    @Override
    public void stop() {

    }

    public void closeShop(LocalDate date) {
        isOpen = false;

        System.out.println("Customers visited today:");
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }

        double totalProfit = 0.0;
        for (Animal animal : animalList) {
            totalProfit += animal.getCost();
        }

        System.out.println("Profit for the day: " + totalProfit);

        balance += totalProfit;
        animalList.clear();
        customerList.clear();
    }
}
