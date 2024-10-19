import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animals;
    private List<Customer> customers;
    private boolean isOpen;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.animals = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if (balance < animal.getPrice()) {
            throw new InsufficientBalanceException("余额不足，无法购买新动物。");
        }
        animals.add(animal);
        balance -= animal.getPrice();
        System.out.println("购买了新动物: " + animal);
    }

    @Override
    public void treatCustomer(Customer customer) throws AnimalNotFoundException {
        if (animals.isEmpty()) {
            throw new AnimalNotFoundException("店内没有动物，无法招待客户。");
        }
        Animal animal = animals.remove(0);
        customers.add(new Customer(customer.getEnterTimes() + 1, customer.getName(), LocalDate.now()));
        balance += animal.getPrice();
        customer.setEnterTimes(customer.getEnterTimes() + 1);

        System.out.println("客户 " + customer + " 购买了动物: " + animal);
    }

    @Override
    public void shopClose() {
        System.out.println("今天的顾客列表: " + customers);
        //公益项目不转差价
        System.out.println("今日利润: " + balance);
        isOpen = false;
    }
}
