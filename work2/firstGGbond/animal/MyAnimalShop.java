package animal;
import java.time.LocalDate;
import java.util.ArrayList;


/**
 * @author MR.瑜
 */
public class MyAnimalShop implements AnimalShop{
    private double balance;
    protected double profit;
    private ArrayList<Animal> animals = new ArrayList<>();;
    private ArrayList<Customer> customers= new ArrayList<>();;
    private boolean isClosed;
    public MyAnimalShop(double balance, boolean isClosed) {
        this.balance = balance;
        this.isClosed = isClosed;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getProfit() {
        return this.profit;
    }

    protected void setProfit(double profit) {
        this.profit = profit;
    }
    protected void decreaseBalance(double decrement) throws IllegalArgumentException, InsufficientBalanceException {
        if (decrement < 0) {
            throw new IllegalArgumentException("Decrement should not be less than 0!");
        }
        if (this.getBalance() < decrement) {
            throw new InsufficientBalanceException(this.getBalance(), decrement);
        }
        this.balance -= decrement;
    }
    @Override
    public void purchaseAnimal(Animal animal) throws InsufficientBalanceException {
        this.decreaseBalance(animal.getPrice());
        this.setProfit(this.getProfit() - animal.getPrice());
        this.animals.add(animal);
    }
    @Override
    public void receiveCustomer(Customer customer, Animal animal) throws AnimalNotFoundException{
        if(isClosed){
            System.out.println("The shop is closed!");
            return;
        }
        if(this.animals.contains(animal)) {
            this.animals.remove(animal);
            this.setBalance(this.getBalance() + animal.getPrice());
            this.setProfit(this.getProfit() + animal.getPrice()+10);
            System.out.println(animal.toString());
            customer.setLatestArrivedTime(LocalDate.now());
            customer.setTimes(customer.getTimes() + 1);
            if (!this.customers.contains(customer)) {
                this.customers.add(customer);
            }
        }
        else {
            throw new AnimalNotFoundException(animal);
        }
    }
    @Override
    public void close(){
        if (!this.isClosed) {
            this.isClosed = true;
            for (Customer customer : this.customers) {
                if (customer.getLatestArrivedTime().isEqual(LocalDate.now())) {
                    System.out.println(customer.toString());
                }
            }
        }
        System.out.println("The profit is "+this.getProfit());
    }
    @Override
    public void reopen() {
        if (isClosed) {
            this.setProfit(0.0);
            this.isClosed = false;
        }
    }
}
