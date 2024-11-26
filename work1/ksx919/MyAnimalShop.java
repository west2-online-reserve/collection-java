import java.util.ArrayList;
import java.util.List;

class MyAnimalShop implements AnimalShop {
    private double balance;
    private double profit;
    private List<Animal> animals = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private boolean isOpen;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.isOpen = true;
        this.profit = 0;
    }

    public void buyAnimal(Animal animal) {
        if (balance >= animal.price) {
            animals.add(animal);
            balance -= animal.price;
        } else {
            throw new InsufficientBalanceException("Insufficient Balance to buy the animal");
        }
    }

    public void serveCustomer(Customer customer,Animal animal) {
        customers.add(customer);
        animals.remove(animal);
        customer.count++;
        this.balance+=animal.price;
        this.profit+=animal.price;
    }

    public void closeShop() {
        isOpen = false;
        System.out.println("宠物店关门啦。");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        System.out.printf("今日利润：%.2f",profit);
    }
}