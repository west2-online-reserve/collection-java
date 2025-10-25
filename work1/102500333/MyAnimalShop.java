import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double balance; 
    private List<Animal> animalList; 
    private List<Customer> customerList; 
    private boolean isOpen; 
    private double dailyProfit; 

    public MyAnimalShop(double initialBalance) {
        this.balance = initialBalance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = true;
        this.dailyProfit = 0.0;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if (!isOpen) {
            throw new RuntimeException("店铺已歇业，无法买入动物！");
        }
        double animalPrice = animal.getPrice();
        if (balance < animalPrice) {
            throw new InsufficientBalanceException("余额不足，无法买入该动物！当前余额：" + balance + "元，所需金额：" + animalPrice + "元");
        }
        animalList.add(animal);
        balance -= animalPrice;
        System.out.println("成功买入：" + animal.toString() + "，当前店铺余额：" + balance + "元");
    }

    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFoundException {
        if (!isOpen) {
            throw new RuntimeException("店铺已歇业，无法招待客户！");
        }

        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("店内暂无动物可售，无法满足客户需求！");
        }

            boolean isExisting = false;
        for (Customer c : customerList) {
            if (c.getName().equals(customer.getName())) {
                c.setVisitCount(c.getVisitCount() + 1);
                c.setLatestVisitTime(customer.getLatestVisitTime());
                isExisting = true;
                break;
            }
        }
        if (!isExisting) {
            customerList.add(customer);
        }

        Animal soldAnimal = animalList.remove(0);
        double soldPrice = soldAnimal.getPrice();
        balance += soldPrice;
        dailyProfit += soldPrice; 
        System.out.println("成功出售：" + soldAnimal.toString());
        System.out.println("招待客户成功：" + customer.toString() + "，当前店铺余额：" + balance + "元");
    }

    @Override
    public void closeShop(LocalDate date) {
        isOpen = false;
        System.out.println("\n===== " + date + " 店铺歇业统计 =====");
       
        System.out.println("当日光顾客户列表：");
        boolean hasDailyCustomer = false;
        for (Customer c : customerList) {
            if (c.getLatestVisitTime().equals(date)) {
                System.out.println(c.toString());
                hasDailyCustomer = true;
            }
        }
        if (!hasDailyCustomer) {
            System.out.println("当日无客户光顾");
        }
        
        System.out.println("当日利润：" + dailyProfit + "元");
        System.out.println("===========================");
    }

    
    public double getBalance() { return balance; }
    public List<Animal> getAnimalList() { return animalList; }
    public List<Customer> getCustomerList() { return customerList; }
    public boolean isOpen() { return isOpen; }
}
