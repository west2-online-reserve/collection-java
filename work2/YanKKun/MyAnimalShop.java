package Animal;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author 12080
 * 继承AnimalShop接口的动物商店
 * 主要实现用于实现AnimalShop接口内的方法
 *
 **/
public class MyAnimalShop implements AnimalShop{

    private double balance;
    //余额
    private double profits;
    //利润
    private final ArrayList<Animal> animalList = new ArrayList<>();
    //动物列表

    private final ArrayList<Customer> customerList = new ArrayList<>();
    //顾客列表

    private boolean isOpen;
    //是否开业

    public MyAnimalShop(double balance, boolean isOpen) {
        this.balance = balance;
        this.isOpen = isOpen;
    }

    @Override
    public void buyAnimals(Animal animal,int number) {
        if (balance< animal.getPrice()*number){
            throw new InsufficientBalanceException("余额不足无法购买");
            //当余额不足时抛出异常
        }else{

            balance -= animal.getPrice()*number;
            //买入动物
            System.out.println("买入"+number+"只"+animal+"成功,还剩下"+balance+"元");

            for (int i = 1; i <= number; i++) {
                animalList.add(animal);
            }
            //将买入的动物加入到动物列表
        }
    }

    @Override
    public void serverCustomers(Customer customer,Animal animal) {
        if(!isOpen){
            System.out.println("商店尚未营业！");
        }else {
            customerList.add(customer);
            //将新顾客加入顾客列表中

            if (animalList.isEmpty()) {
                throw new AnimalNotFountException("找不到对应动物");
                //无动物时抛出异常
            }
            for (int i = 0; i < animalList.size(); i++) {
                Animal a = animalList.get(i);
                if (a.equals(animal)) {
                    profits += 10;
                    //每只利润10块
                    balance += animal.getPrice()+10;
                    //商店余额增加
                    animalList.remove(animal);
                    //将动物移出动物列表
                    System.out.println("成功出售"+animal);
                    //出售动物
                    customer.setArrivalTimes(customer.getArrivalTimes()+1);
                    //顾客到店次数加一
                    customer.setLastestArrivalTime(LocalDate.now());
                    //将顾客到店时间更新为当前时间
                    return;
                }
            }
        }
    }

    @Override
    public boolean close() {
        if(isOpen){
            System.out.println("宠物店歇业");
            LocalDate localDate = LocalDate.now();
            System.out.println("今日利润为"+profits+"元");
            //歇业并输入今日利润
            System.out.println("今日光临的顾客如下");
            for(Customer customer : customerList){
                System.out.println(customer);
            }
            //输出今日光临的顾客
            this.isOpen = false;
        }
        return false;
    }

    @Override
    public boolean open() {
        if(!isOpen){
            if(customerList.isEmpty()){
                //若顾客列表为空直接开业
                System.out.println("宠物店开业");
                this.setProfits(0.0);
                this.isOpen = true;
            }else {
                //若顾客列表不为空删除前天的顾客列表
                for (int i = customerList.size()-1; i >= 0; i--) {
                    customerList.remove(customerList.get(i));
                }
                System.out.println("宠物店开业");
                this.setProfits(0.0);
                this.isOpen = true;
                //开业并重置利润
            }
        }
        return true;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if(balance<0){
            System.out.println("余额不能为负值");
        }else {
        this.balance = balance;
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setFlag(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public double getProfits() {
        return profits;
    }

    public void setProfits(double profits) {
        this.profits = profits;
    }
}
