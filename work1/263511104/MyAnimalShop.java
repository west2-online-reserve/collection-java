package work1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class MyAnimalShop implements AnimalShop {
    private double balance;
    private double totalIncome = 0; // 总收入
    private List<Animal> animalList;
    private List<Customer> customerList;
    private boolean isOpen;

    public MyAnimalShop(double initialbalance) {
        this.balance = initialbalance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if (animal.getPrice() > balance) {
            throw new InsufficientBalanceException("余额不足，无法购买动物");
        }

        balance -= animal.getPrice();
        animalList.add(animal);

    }
    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFoundException, InsufficientBalanceException {
        Scanner scanner = new Scanner(System.in);
        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("动物数量不足无法出售");
        }

        System.out.println("可购买的动物：");
        for (int i = 0; i < animalList.size(); i++) {
            Animal animal = animalList.get(i);
            System.out.println((i + 1) + ". " + animal.toString());
        }

        System.out.print("请选择一个动物 (enter number): ");
        int choice = scanner.nextInt();

        // 检查用户输入是否有效
        if (choice < 1 || choice > animalList.size()) {
            System.out.println("选择无效，请重新选择。");
            scanner.nextLine(); // 清除错误的输入
            return;
        }

        Animal chosenAnimal = animalList.get(choice - 1);

        // 检查余额是否足够购买选中的动物
        if (chosenAnimal.getPrice() > balance) {
            throw new InsufficientBalanceException("余额不足无法购买");
        }

        // 余额足够，更新余额和收入
        balance -= chosenAnimal.getPrice(); // 减去动物的价格，因为这是支出
        totalIncome += chosenAnimal.getPrice(); // 增加总收入
        animalList.remove(chosenAnimal);

        System.out.println("卖出: " + chosenAnimal + "，剩余余额: " + balance);
        customer.visit();
        customerList.add(customer);
    }

    @Override
    public void closeShop() {
        if (!isOpen) {
            System.out.println("已经关店");
            return;
        }
        System.out.println("今日顾客: " + customerList.size());
        System.out.println("今日收入: " + totalIncome);
        isOpen = false;
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
        this.isOpen = open;
    }
}
