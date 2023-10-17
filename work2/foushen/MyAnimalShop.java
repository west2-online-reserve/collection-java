import java.time.LocalDate;
import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private boolean isOpen;
    private double theDaysProfit;
    private ArrayList<Animal> onSaleAnimals;
    private ArrayList<Customer> customers;

    void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }


    public MyAnimalShop(double balance, boolean isOpen) {
        this.balance = balance;
        this.isOpen = isOpen;
        this.customers = new ArrayList<>();
        this.onSaleAnimals = new ArrayList<>();
    }

    @Override
    public void buyNewAnimal(Animal animal) throws AnimalShopIsClosed, InsufficientBalanceException {
        if (!isOpen) {
            throw new AnimalShopIsClosed();
        }
        if (animal.getRestorePrice() > balance) {
            throw new InsufficientBalanceException("余额不足，还差", animal.getRestorePrice() - balance);
        }

        balance -= animal.getRestorePrice();
        theDaysProfit -= animal.getRestorePrice();
        onSaleAnimals.add(animal);

        System.out.println("进货成功");


    }

    @Override
    public void sellAnimal(Customer customer, Animal animal) throws AnimalShopIsClosed, AnimalNotFountException {
        if (!isOpen) {
            throw new AnimalShopIsClosed();
        }
        if (!onSaleAnimals.contains(animal)) {
            throw new AnimalNotFountException("缺少动物", animal);
        }
        balance += animal.getPrice();
        theDaysProfit += animal.getPrice();
        onSaleAnimals.remove(animal);

        if (!customers.contains(customer)) {
            customer.setLatestVisitShopTime(LocalDate.now());
            customer.setVisitShopTimes(customer.getVisitShopTimes() + 1);
            customers.add(customer);
        }
        System.out.println("卖出成功");

    }

    @Override
    public void serveNewCustomer(Customer customer) throws AnimalShopIsClosed {
        if (!isOpen) {
            throw new AnimalShopIsClosed();
        }
        if (!customers.contains(customer)) {
            customer.setVisitShopTimes(customer.getVisitShopTimes() + 1);
            customer.setLatestVisitShopTime(LocalDate.now());
            System.out.println("成功招待一名顾客");
            return;
        }
        System.out.println("该顾客已存在");

    }

    @Override
    public void close() {
        if (!isOpen) {
            System.out.println("商店已经关闭");
            return;
        }

        System.out.println("今天的利润为：" + theDaysProfit);
        //清零当天利润
        theDaysProfit = 0;

        for (Customer customer : customers) {
            if (customer.getLatestVisitShopTime().isEqual(LocalDate.now())) {
                System.out.println(customer);
            }
        }

        isOpen = false;

        System.out.println("商店已经关闭");
    }

    @Override
    public void open() {
        isOpen = true;
        System.out.println("商店已开启");
    }

}
