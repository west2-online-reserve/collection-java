import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animalList;
    private List<Customer> customerList;
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
            throw new InsufficientBalanceException("没有足够的余额购买动物.");
        }

        balance -= animal.getCost();
        animalList.add(animal);
    }

    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFoundException {
        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("没有动物可供出售。.");
        }

        Animal animal = animalList.remove(0);
        System.out.println("Selling animal: " + animal.toString());

        balance += animal.getCost();
        customeer.setArriveTime(LocalDate.now());
        customerList.add(customer);
    }

    public void entertainCustomer(Customer customer) {
        System.out.println("招待顾客: " + customer.toString());
        customeer.setArriveTime(LocalDate.now());
            }

    @Override
    public void stop() {

    }

    public void closeShop(LocalDate date) {
        isOpen = false;

        System.out.println("今天来的客户:");
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }

        double totalProfit = 0.0;
        for (Animal animal : animalList) {
            totalProfit += animal.getCost();
        }

        System.out.println("今日收益: " + totalProfit);

        balance += totalProfit;
        animalList.clear();
        customerList.clear();
    }
}
