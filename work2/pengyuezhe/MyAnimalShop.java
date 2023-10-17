/**
 * @author pengyuezhe
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class MyAnimalShop implements AnimalShop {
    private double money;
    private boolean isClosed;
    /**
     * benefit为储存当天利润的私有变量
     */
    private double benefit;
    /**
     * timeCheck用于检测开店时间和关店时间是否在同一天
     */
    private LocalDate timeCheck;
    private ArrayList<Animal> pets = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();

    public MyAnimalShop(double money, boolean isClosed) {
        this.money = money;
        this.isClosed = isClosed;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setClosed(boolean state) {
        isClosed = state;
    }

    public Boolean getClosed() {
        return isClosed;
    }

    public double getBenefit() {
        return benefit;
    }

    public void setBenefit(double benefit) {
        this.benefit = benefit;
    }

    @Override
    public void purchase(Animal animal) throws InsufficientBalanceException {
        if (isClosed) {
            System.out.println("进货失败，宠物店已打烊");
        } else {
            if (money < animal.price) {
                double needs = animal.price - money;
                throw new InsufficientBalanceException(needs);
            } else {
                pets.add(animal);
                setMoney(money - animal.price);
                System.out.println("【进货宠物】购入一只\n" + animal);
                System.out.println("宠物店余额：" + money + "\n-------------------------------");
                System.out.println("【进货检查】当前宠物店里所有宠物：");
                for (Animal pet : pets) {
                    System.out.println(pet + "\n-------------------------------");
                }
            }
        }
    }

    @Override
    public void service(Customer customer, Animal animal) throws AnimalNotFoundException {
        System.out.println("宠物店当前是否打烊" + getClosed());
        if (customers.contains(customer)) {
            customers.remove(customer);
        }
        customer.setNewestDate();
        customer.setFrequency(customer.getFrequency() + 1);
        customers.add(customer);
        if (isClosed) {
            System.out.println("【购买失败】宠物店已打烊");
        } else {
            if (pets.isEmpty()) {
                throw new AnimalNotFoundException();
            }
            if (pets.contains(animal)) {
                System.out.println("【购买成功】" + customer.getName() + "买了一只宠物：\n" + animal + "\n-------------------------------");
                setBenefit(getBenefit() + animal.price);
                pets.remove(animal);
                setMoney(getMoney() + animal.price);
                if (pets.isEmpty()) {
                    System.out.println("【购买剩余】宠物店已清仓" + "\n-------------------------------");
                } else {
                    System.out.println("【购买剩余】宠物店里所有宠物：");
                    for (Animal pet : pets) {
                        System.out.println(pet + "\n-------------------------------");
                    }
                }
            } else {
                System.out.println("【购买失败】未查找到指定宠物" + "\n-------------------------------");
            }
        }

    }

    @Override
    public void isClosed() {
        System.out.println("【打烊结算】今日的顾客：");
        setClosed(true);
        for (Customer customer : customers) {
            if (Objects.equals(customer.getNewestDate(), LocalDate.now())) {
                System.out.println(customer + "\n-------------------------------");
            }
        }
        System.out.println("【打烊结算】宠物店当天利润为" + benefit + "\n-------------------------------");
        timeCheck = LocalDate.now();
    }

    /**
     * setBenefit(0);用于在新一天开张时重置当天利润
     * 当店铺在同一天内日期未改变时不会重置利润
     */
    public void open() {
        setClosed(false);
        if (Objects.equals(timeCheck, LocalDate.now())) {
            System.out.println("【警告】请不要在同一天内重复开关店");
        } else {
            setBenefit(0);
        }
        System.out.println("【开张检查】宠物店余额：" + money + "\n-------------------------------");
        if (pets.isEmpty()) {
            System.out.println("【开张检查】宠物店需要补货" + "\n-------------------------------");
        } else {
            System.out.println("【开张检查】宠物店里所有宠物：");
            for (Animal pet : pets) {
                System.out.println(pet + "\n-------------------------------");
            }
        }
    }
}
