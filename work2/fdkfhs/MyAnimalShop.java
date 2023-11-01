import java.time.LocalDate;
import java.util.*;

public class MyAnimalShop implements AnimalShop {
    // 余额
    private double balance;
    // 利润
    private double profit;
    private final Collection<Customer> customerList = new ArrayList<>();
    private final Collection<Animal> animalList = new ArrayList<>();
    //true表示正在营业，false表示歇业
    private boolean open;


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
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
        if (getOpen()) {
            if (animalList.contains(animal)) {
                if (!customerList.contains(customer)) {
                    customerList.add(customer);
                }
                animalList.remove(animal);
                // 每买出一只动物获利50元
                setBalance(getBalance() + animal.getPrice() + 50);
                setProfit(getProfit() + animal.getPrice() + 50);
                System.out.println("恭喜" + customer.getName() + "动物购买成功!该动物的信息为" + animal);
                customer.setNumber(customer.getNumber() + 1);
                customer.setLocalDate(LocalDate.now());
            } else {
                throw new AnimalNotFountException(animal.name);
            }
        } else {
            System.out.println("抱歉，本店正在歇业！");
        }

    }

    @Override
    public void Closed() {
        this.setOpen(false);
        for (Customer customer : this.customerList) {
            if (customer.getLocalDate().isEqual(LocalDate.now())) {
                System.out.println(customer);
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