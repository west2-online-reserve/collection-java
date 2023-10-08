import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

public class MyAnimalShop implements AnimalShop {
    protected double balance;
    protected boolean isOpen;
    protected double profit;
    protected Collection<Customer> customers = new ArrayList<>();
    protected Collection<Animal> animals = new ArrayList<>();
    public MyAnimalShop(double balance, boolean isOpen) {
        this.increaseBalance(balance);
        this.setOpen(isOpen);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    protected double getBalance() {
        return this.balance;
    }

    protected void increaseBalance(double increment) throws IllegalArgumentException {
        if (increment < 0) {
            throw new IllegalArgumentException("Increment should not be less than 0!");
        }
        this.balance += increment;
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

    public double getProfit() {
        return this.profit;
    }

    protected void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public void purchaseAnimal(Animal animal) throws InsufficientBalanceException {
        this.decreaseBalance(animal.getPrice());
        this.setProfit(this.getProfit() - animal.getPrice());
        this.animals.add(animal);
    }

    @Override
    public void receiveCustomer(Customer customer, Animal animal) throws AnimalNotFoundException {
        if (!this.isOpen) {
            System.out.println("Shop is not open!");
            return;
        }
        if (this.animals.contains(animal)) {
            this.animals.remove(animal);

            this.increaseBalance(animal.getPrice());
            this.setProfit(this.getProfit() + animal.getPrice());

            System.out.println(animal.toString());

            customer.setLatestVisitDate(LocalDate.now());
            customer.setVisitTimes(customer.getVisitTimes() + 1);

            if (!this.customers.contains(customer)) {
                this.customers.add(customer);
            }
        } else {
            throw new AnimalNotFoundException(animal);
        }
    }

    @Override
    public void close() {
        if (this.isOpen) {
            this.isOpen = false;
            for (Customer customer : this.customers) {
                if (customer.getLatestVisitDate().isEqual(LocalDate.now())) {
                    System.out.println(customer.toString());
                }
            }
            System.out.println("Today's profit: " + this.getProfit());
        }
    }

    @Override
    public void reopen() {
        if (!isOpen) {
            this.setProfit(0.0);
            this.isOpen = true;
        }
    }
}
