import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animalList;
    private List<Customer> customerList;
    private boolean isOpen;
    private double initialBalance;


    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.initialBalance = balance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) {
        if (!isOpen) {
            System.out.println("宠物店已歇业，无法购买新动物。");
            return;
        }
        if (balance < animal.price) {
            throw new InsufficientBalanceException("余额不足，无法购买新动物。当前余额: " + balance + ", 动物价格: " + animal.price);
        }
        balance -= animal.price;
        animalList.add(animal);
        System.out.println("成功购入一只动物: " + animal.toString() + "。剩余余额: " + balance);
    }

    @Override
    public void treatCustomer(Customer customer) {
        if (!isOpen) {
            System.out.println("宠物店已歇业，无法招待顾客。");
            return;
        }
        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("店内没有动物了，无法出售。");
        }

        Customer existingCustomer = customerList.stream()
                .filter(c -> c.getCustomerName().equals(customer.getCustomerName()))
                .findFirst()
                .orElse(null);

        if (existingCustomer != null) {
            existingCustomer.incrementVisitCount();
        } else {
            customerList.add(customer);
        }

        Animal soldAnimal = animalList.remove(0);
        balance += soldAnimal.price;
        System.out.println("顾客 " + customer.getCustomerName() + " 光临本店！");
        System.out.println("成功出售一只动物: " + soldAnimal.toString());
        System.out.println("当前余额: " + balance);
    }

    @Override
    public void closeShop() {
        if (!isOpen) {
            System.out.println("宠物店已经歇业了。");
            return;
        }
        this.isOpen = false;
        System.out.println("\n--- 宠物店开始歇业 ---");
        LocalDate today = LocalDate.now();
        List<Customer> todayCustomers = customerList.stream()
                .filter(c -> c.getLatestVisitTime().equals(today))
                .collect(Collectors.toList());

        if (todayCustomers.isEmpty()) {
            System.out.println("今天没有顾客光顾。");
        } else {
            System.out.println("今天光顾的顾客列表:");
            todayCustomers.forEach(System.out::println);
        }

        double profit = balance - initialBalance;
        System.out.println("今日总利润为: " + profit);
        System.out.println("--- 宠物店已歇业 ---");
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }
}