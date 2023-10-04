package szw.test2;

import java.time.LocalDate;
import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop{

    private double balance;
    private ArrayList<Animal> animalList;
    private ArrayList<Customer> customerList;
    private boolean isOpen;
    private double cost; //成本
    private double profit; //利润
    public MyAnimalShop(double balance, ArrayList<Animal> animalList, ArrayList<Customer> customerList, boolean isOpen,double cost) {
        this.balance = balance;
        this.animalList = animalList;
        this.customerList = customerList;
        this.isOpen = isOpen;
        this.cost=cost;
        this.profit=0;
    }

    public MyAnimalShop() {
        this.balance = 0.0;
        this.animalList = new ArrayList<Animal>();
        this.customerList = new ArrayList<Customer>();
        this.isOpen = true;
        this.profit = 0;
        this.cost=20.0; //成本默认值为20
    }

    public double getProfit() {
        return profit;
    }



    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(ArrayList<Animal> animalList) {
        this.animalList = animalList;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public void buyAnimal(Animal newAnimal) {
        if (!isOpen) {
            System.out.println("宠物店已歇业，无法购买动物。");
            return;
        }

        // 判断余额是否足够购买动物
        if (balance < newAnimal.getPrice()) {
            throw new InsufficientBalanceException("余额不足，无法购买动物。");
        }

        // 创建新的动物对象，并添加到动物列表中

        animalList.add(newAnimal);
        System.out.println("成功购买动物：" + newAnimal.toString());

        // 更新店的余额
        balance -= newAnimal.getPrice();
        profit -=newAnimal.getPrice();
    }

    @Override
    public void serveCustomer(Customer customer) {
        if (!isOpen) {
            System.out.println("宠物店已歇业，无法招待客户。");
            return;
        }
        customerList.add(customer);
        customer.addVisitCount();
        customer.setLastVisitDate();
        if (animalList.isEmpty()) {
            System.out.println("抱歉，宠物店暂时没有动物可供出售。");
            return;
        }

        // 出售动物给客户
        Animal soldAnimal = animalList.remove(0);
        System.out.println("成功出售动物：[" + soldAnimal.toString() + "] ,  购买者：" + customer.getName());

        // 将动物的价格入账
        balance += soldAnimal.getPrice();
        profit+= soldAnimal.getPrice();
    }

    @Override
    public void close() {
        isOpen = false;

        System.out.println("宠物店已歇业。");
        LocalDate today = LocalDate.now();
        System.out.println("今天光顾的客户列表：");
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }

        double phase = profit - cost; //计算盈亏
        if (phase >= 0) {
            System.out.println("今天的利润为：" + phase + "元");
        } else {
            System.out.println("今天的亏损为：" + Math.abs(phase) + "元");
        }
    }
}
