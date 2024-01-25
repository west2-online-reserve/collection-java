import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {

    protected double balance;
    protected List<Animal> animalList;
    protected List<Customer> customerList;
    protected boolean isOpen;

    public MyAnimalShop(double balance,boolean isOpen) {
        this.balance = balance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = isOpen;
    }

    @Override
    public void buyAnimal(Animal animal) {
        if (animal.getPrice() > this.balance) {
            throw new InsufficientBalanceException("余额不足！");
        }else{
            animalList.add(animal);
            this.balance=this.balance - animal.getPrice();
            System.out.println("进货成功。");
        }
    }
    @Override
    public void sell(Animal animal){
        if(!animalList.contains(animal)) {
            throw new AnimalNotFoundException("店里没有该动物");
        }else{
            animalList.remove(animal);
            this.balance=this.balance + animal.getPrice();
            System.out.println("卖出了1只" + animal.toString());
        }
    }

    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFoundException {
        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("店内没有动物");
        }
        Animal animal = animalList.get(0); // 从列表中取第一只动物出售
        System.out.println("欢迎顾客：" + customer.getCustomerName());
        System.out.println("出售动物：" + animal);
        balance += animal.getPrice(); // 将钱入账
        animalList.remove(0); // 将动物移除列表
    }

    @Override
    public void close() {
        isOpen = false;
        LocalDate today = LocalDate.now();

        // 输出当天光顾的客户信息
        System.out.println("今天光顾了以下客户：");
        for (Customer customer : customerList) {
            if (customer.getLatestVisitDate().equals(today)) {
                System.out.println(customer);
            }
        }

        // 计算并输出一天的利润
        System.out.println("收工下班！");
    }

    // Getter和Setter方法
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
}
