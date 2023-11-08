package westwork2;

import java.time.LocalDate;
import java.util.ArrayList;
public class MyAnimalShop implements AnimalShop{
    private double balance;
    private double profit;
    private ArrayList<Animal> animals = new ArrayList<>();;
    private ArrayList<Customer> customers= new ArrayList<>();;
    private boolean isClosed;
    public MyAnimalShop(double balance, boolean isClosed) {
        this.balance = balance;
        this.isClosed = isClosed;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getProfit() {
        return this.profit;
    }

    protected void setProfit(double profit) {
        this.profit = profit;
    }
    protected void decreaseBalance(double decrement) throws IllegalArgumentException, InsufficientBalanceException {
        if (decrement < 0) {
            throw new IllegalArgumentException("Decrement should not be less than 0!");
        }
        if (this.getBalance() < decrement) { //检查用户的当前余额
            throw new InsufficientBalanceException(this.getBalance(), decrement);
        }
        this.balance -= decrement;//在余额中扣除这笔钱
    }//确保余额有效
    @Override
    public void purchaseAnimal(Animal animal) throws InsufficientBalanceException {
        this.decreaseBalance(animal.getPrice());
        this.setProfit(this.getProfit() - animal.getPrice());//设置新的利润
        this.animals.add(animal);//将购买的动物添加到宠物店持有的动物列表中
    }
    @Override
    public void receiveCustomer(Customer customer, Animal animal) throws AnimalNotFoundException{
        if(isClosed){
            System.out.println("The shop is closed!");//判断是否关闭
            return;
        }
        if(this.animals.contains(animal)) {
            this.animals.remove(animal);
            this.setBalance(this.getBalance() + animal.getPrice());//余额
            this.setProfit(this.getProfit() + animal.getPrice()+20);//利润
            System.out.println(animal.toString());
            customer.setLatestArrivedTime(LocalDate.now());//当前日期
            customer.setTimes(customer.getTimes() + 1);//次数
            if (!this.customers.contains(customer)) {//未包含该顾客
                this.customers.add(customer);
            }
        }
        else {
            throw new AnimalNotFoundException(animal);
        }
    }
    @Override
    public void close(){
        if (!this.isClosed) {
            this.isClosed = true;
            for (Customer customer : this.customers) {
                if (customer.getLatestArrivedTime().isEqual(LocalDate.now())) {//检查顾客到访时间
                    System.out.println(customer.toString());//如果正确则打印顾客信息
                }
            }
        }
        System.out.println("The profit is "+this.getProfit());//不管关不关店都会打印利润
    }
    @Override
    public void reopen() {
        if (isClosed) {
            this.setProfit(0.0);
            this.isClosed = false;
        }
    }
}