import java.time.LocalDate;
import java.util.*;

public class MyAnimalShop implements AnimalShop {
    // 余额
    private double balance;
    // 利润
    private double profit;
    Collection<Customer> customerList = new ArrayList<>();
    Collection<Animal> animalList = new ArrayList<>();
    private boolean closed;


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean getClose() {
        return closed;
    }

    public void setClose(boolean closed) {
        this.closed = closed;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    @Override
    public void newAnimal(Animal animal) throws InsufficientBalanceException {
        if (animal.getPrice() <= getBalance()) {
            animalList.add(animal);
            System.out.println("购买" + animal.name + "成功！");
            setProfit(this.getProfit() - animal.getPrice());
            setBalance(getBalance() - animal.getPrice());
        } else {
            throw new InsufficientBalanceException(animal.name, animal.price, balance);
        }

    }

    @Override
    public void reciveCustomer(Customer customer, Animal animal) throws AnimalNotFountException {
        if (animalList.contains(animal)) {
            if (!customerList.contains(customer)) {
                customerList.add(customer);
            }
            animalList.remove(animal);
            // 每买出一只动物获利50元
            setBalance(getBalance() + animal.getPrice() + 50);
            setProfit(getProfit() + animal.getPrice() + 50);
            System.out.println("恭喜"+customer.getName()+"动物购买成功!该动物的信息为"+animal.toString());
            customer.setNumber(customer.getNumber() + 1);
            customer.setLocalDate(LocalDate.now());
        } else {
            throw new AnimalNotFountException(animal.name);
        }

    }

    @Override
    public void Closed() {
        this.setClose(false);
        for (Customer customer : this.customerList) {
            if (customer.getLocalDate().isEqual(LocalDate.now())) {
                System.out.println(customer.toString());
            }
        }
        if (getProfit() > 0) {
            System.out.println("今天盈利" + getProfit() + "元");
        } else if (getProfit() == 0) {
            System.out.println("今天没有盈利也没有亏损");
        } else {
            System.out.println("今天亏损" + -1 * getProfit() + "元");
        }
    }
}