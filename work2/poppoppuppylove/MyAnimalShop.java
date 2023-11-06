import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {

    private double balance;
    private List<Animal> animals = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private boolean isClosed;

    public MyAnimalShop(double initialBalance, boolean isClosed) {
        this.balance = initialBalance;
        this.isClosed = false;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        // 使用异常检查余额
        if (balance < animal.getPrice()) {
            throw new InsufficientBalanceException("余额不足");
        }
        animals.add(animal);
        balance -= animal.getPrice();
    }

    @Override
    public void welcomeCustomer(Customer customer) throws AnimalNotFoundException {
        if (isClosed) {
            System.out.println("关门了，明天再见吧");
            return;
        }

        customers.add(customer);
        customer.setVisitCount(customer.getVisitCount() + 1);
        customer.setLastVisitDate(LocalDate.now());

        if (animals.isEmpty()) {
            throw new AnimalNotFoundException("本店已售空");
        }

        int index = (int)(Math.random() * animals.size());
        Animal soldAnimal = animals.remove(index);

        balance += soldAnimal.getPrice();
    }

    @Override
    public void close() {
        System.out.println("今日顾客:");
        for (Customer customer : this.customers) {
            if (customer.getLastVisitDate().isEqual(LocalDate.now())) {
                System.out.println(customer.toString());
            }
        }

        // 计算利润
        double profit = balance - getTotalBoughtAmount();
        System.out.println("今日利润:" + profit);

        // 清空列表
        animals.clear();
        customers.clear();

        isClosed = true;
    }

    private double getTotalBoughtAmount() {
        double total = 0.0;
        for (Animal animal : animals) {
            total += animal.getPrice();
        }
        return total;
    }

}