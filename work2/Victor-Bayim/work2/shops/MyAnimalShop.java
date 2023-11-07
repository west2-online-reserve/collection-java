import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animalList;
    private List<Customer> customerList;
    private boolean isOpen;
    private double todayIncome;

    public MyAnimalShop(double initialBalance) {
        this.balance = initialBalance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = true;
        this.todayIncome = 0.0;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if (this.balance < animal.getPrice()) {
            throw new InsufficientBalanceException("Insufficient balance to buy the animal.");
        }
        this.balance -= animal.getPrice();
        this.animalList.add(animal);
    }

    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFoundException {
        if (this.animalList.isEmpty()) {
            throw new AnimalNotFoundException("No animals available for sale.");
        }
        // 销售第一只动物
        Animal soldAnimal = this.animalList.remove(0);
        // 更新店铺余额
        this.balance += soldAnimal.getPrice();
        // 更新今日收入
        this.todayIncome += soldAnimal.getPrice();
        // 添加顾客到顾客列表
        this.customerList.add(customer);
        // 更新顾客信息
        customer.updateVisit();
        // 打印出售的动物信息
        System.out.println(soldAnimal);
    }

    @Override
    public void closeShop() {
        if (!this.isOpen) {
            System.out.println("The shop is already closed.");
            return;
        }
        this.isOpen = false;
        System.out.println("Closing shop... Today's income: " + this.todayIncome);
        this.todayIncome = 0;
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
        this.customerList.clear();
    }

    // Getters and Setters
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public double getTodayIncome() {
        return todayIncome;
    }

    @Override
    public String toString() {
        return "MyAnimalShop{" +
                "balance=" + balance +
                ", animalList=" + animalList +
                ", customerList=" + customerList +
                ", isOpen=" + isOpen +
                ", todayIncome=" + todayIncome +
                '}';
    }
}
