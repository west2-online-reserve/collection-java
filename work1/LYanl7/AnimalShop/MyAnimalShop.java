import java.time.LocalDate;
import java.util.List;

/**
 * 动物商店的实现类
 *
 * @author  LYanl7
 * @since  2025-9-14
 */
public class MyAnimalShop implements AnimalShop {
    private double balance;           // 商店余额
    private List<Animal> animals;     // 存放动物的列表
    private List<Customer> customers; // 存放顾客的列表
    private double profit;            // 今日盈利
    private boolean isOpen;           // 商店是否开业

    public MyAnimalShop(double balance, List<Animal>animals, List<Customer> customers, boolean isOpen) {
        this.balance = balance;
        this.animals = animals;
        this.customers = customers;
        this.isOpen = isOpen;
    }

    @Override
    public void purchaseAnimal(Animal animal) {
        // 1. 检查余额是否足够
        double price = animal.price;
        if (balance < price) {
            throw new InsufficientBalanceException("余额不足，无法购买该动物", balance, price);
        }
        // 2. 扣除余额
        balance -= price;
        profit -= price;
        // 3. 将动物添加到库存
        animals.add(animal);
    }

    @Override
    public void entertainCustomer(Customer customer) {
        if (!isOpen) {
            throw new RuntimeException("商店未开业，无法接待顾客");
        }
        // 1. 在顾客列表中加入新顾客
        customer.setLastVisitDate(LocalDate.now());
        customer.incrementFrequency();
        if (!customers.contains(customer)) {
            customers.add(customer);
        }
        // 2. 出售动物
        if (animals.isEmpty()) {
            throw new AnimalNotFoundException("库存中没有动物，无法出售");
        }
        Animal animal = animals.remove(0);
        System.out.println("出售动物: ");
        System.out.print(animal.toString());
        // 3. 增加余额
        balance += animal.price;
        profit += animal.price;
    }

    @Override
    public void closeShop() {
        this.isOpen = false;
        for (Customer customer : customers) {
            if (customer.getLastVisitDate().equals(LocalDate.now())) {
                System.out.println("招待顾客: ");
                System.out.print(customer.toString());
            }
        }
        System.out.println("今日总盈利: " + profit + "元");
        profit = 0; // 重置今日盈利
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public double getBalance() {
        return balance;
    }
}
