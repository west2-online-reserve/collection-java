import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    ArrayList<Animal> animalList = new ArrayList<>();
    ArrayList<Customer> customerList = new ArrayList<>();
    private boolean isClosed;
    private double profit = 0;

    public MyAnimalShop() {
    }

    public MyAnimalShop(double balance, boolean isClosed) {
        this.balance = balance;
        this.isClosed = isClosed;
    }

    @Override
    public void buyNewAnimal(Animal animal) {
        try {
            if (balance < animal.price) {
                throw new InsufficientBalanceException("余额不足！");
            } else {
                balance -= animal.price;
                animalList.add(animal);
                System.out.println("购买成功。当前宠物店有" + animalList.size() + "只宠物,购买的宠物为" + animal.name + "。余额:" + this.balance);
            }
        } catch (InsufficientBalanceException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void serve(Customer customer) {
        try {
            if (isClosed) {
                System.out.println("宠物店已关门。");
                return ;
            } else if (!animalList.isEmpty()) {
                customerList.add(customer);
                int num = (int) (Math.random() * animalList.size());
                Animal animal = animalList.get(num);
                animalList.remove(num);
                profit += animal instanceof Cat ? 200 : 100;
                System.out.println("您购买了本店的" + animal.name + ",谢谢您的惠顾,欢迎下次再来。");
                customer.arrivalTimes++;
                customer.lastArriveTime = LocalDate.now();
            } else {
                throw new AnimalNotFoundException("本店宠物已售罄,请下次再来。");
            }
        } catch (AnimalNotFoundException e) {
            System.out.println(e.toString());

        }
    }

    @Override
    public void close() {

        System.out.println("今日顾客:");
        for (Customer it : customerList) {
            System.out.println(it.toString());
        }
        System.out.print("今日利润为:");
        System.out.println(profit);
        setIsClosed(true);
        balance += profit;
        profit = 0;
    }

    /**
     * 获取
     *
     * @return balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * 设置
     *
     * @param balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * 获取
     *
     * @return animalList
     */
    public ArrayList<Animal> getAnimalList() {
        return animalList;
    }

    /**
     * 设置
     *
     * @param animalList
     */
    public void setAnimalList(ArrayList<Animal> animalList) {
        this.animalList = animalList;
    }

    /**
     * 获取
     *
     * @return customerList
     */
    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * 设置
     *
     * @param customerList
     */
    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    /**
     * 获取
     *
     * @return isClosed
     */
    public boolean isIsClosed() {
        return isClosed;
    }

    /**
     * 设置
     *
     * @param isClosed
     */
    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public String toString() {
        return "MyAnimalShop{balance = " + balance + ", animalList = " + animalList + ", customerList = " + customerList + ", isClosed = " + isClosed + "}";
    }
}
