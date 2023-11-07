import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private double dailyCost = 0;
    private double dailyIncome = 0;
    private LinkedList<Animal> animalList = new LinkedList<>();
    private ArrayList<Customer> customerList = new ArrayList<>();
    private boolean isClose = true;

    private LocalDate todayDate;

    public MyAnimalShop(double balance, boolean isClose) {
        this.balance = balance;
        this.isClose = isClose;
    }

    @Override
    public void buy(Animal animal) {
        System.out.println("宠物店进新宠");
        System.out.println("购进" + animal.getName() + "...");
        if (balance < animal.getPrice()) {
            throw new InsufficientBalanceException("The balance is insufficient to cover the animal's price");
        }
        System.out.println("购进成功");
        animalList.add(animal);
        balance -= animal.getPrice();
        dailyCost += animal.getPrice();
    }

    @Override
    public void serve(Customer customer) {
        System.out.println(customer.getName() + "前来购买宠物");
        if (isClose) {
            System.out.println("宠物店已关门，请下次光临。");
            return;
        }
        int customerIndex = customerList.indexOf(customer);
        customer.addArrivedTimes();
        customer.setLastVisitedTime(todayDate);
        if (customerIndex == -1) {
            customerList.add(customer);
        } else {
            customerList.set(customerIndex, customer);
        }
        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("所有宠物都已售完");
        }
        Animal animalSelling = animalList.getLast();
        animalList.removeLast();
        System.out.println("购买成功");
        System.out.println("宠物信息:");
        System.out.println(animalSelling.toString());
        balance += animalSelling.getPrice();
        dailyIncome += animalSelling.getPrice();
    }

    @Override
    public void close() {
        isClose = true;
        System.out.println("宠物店歇业");
        System.out.println("今日来访客人：");
        for (Customer customer : customerList) {
            if (customer.getLastVisitedTime() == todayDate) {
                System.out.println(customer.toString());
            }
        }
        System.out.println("今日收入" + dailyIncome + "元");
        System.out.println("今日支出" + dailyCost + "元");
        System.out.println("净利润：" + (dailyIncome - dailyCost) + "元");
    }

    @Override
    public void open() {
        System.out.println("宠物店开始营业");
        isClose = false;
        dailyIncome = 0;
        dailyCost = 0;
        if (todayDate != null) todayDate = todayDate.plusDays(1);
        else todayDate = LocalDate.now();
    }
}
