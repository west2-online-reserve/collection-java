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

    //余额
    private double balance;

    //利润
    private double profits;

    //动物列表
    private final ArrayList<Animal> animalList = new ArrayList<>();

    //顾客列表
    private final ArrayList<Customer> customerList = new ArrayList<>();

    //是否开业
    private boolean isOpen;

    public MyAnimalShop(double balance, boolean isOpen) {
        this.balance = balance;
        this.isOpen = isOpen;
    }

    @Override
    public void buyAnimals(Animal animal,int number) {
        if (balance< animal.getPrice()*number){
            //当余额不足时抛出异常
            throw new InsufficientBalanceException("余额不足无法购买");
        }else{
            //当余额足够时买入动物
            balance -= animal.getPrice()*number;
            System.out.println("买入"+number+"只"+animal+"成功,还剩下"+balance+"元");
            //将买入的动物加入到动物列表
            for (int i = 1; i <= number; i++) {
                animalList.add(animal);
            }

        }
    }

    @Override
    public void serverCustomers(Customer customer,Animal animal) {
        if(!isOpen){
            System.out.println("商店尚未营业！");
        }else {
            //将新顾客加入顾客列表中
            customerList.add(customer);

            if (animalList.isEmpty()) {
                //无动物时抛出异常
                throw new AnimalNotFountException("找不到对应动物");
            }
            for (int i = 0; i < animalList.size(); i++) {
                Animal a = animalList.get(i);
                if (a.equals(animal)) {
                    //每只利润10块
                    profits += 10;
                    //商店余额增加
                    balance += animal.getPrice()+10;
                    //将动物移出动物列表
                    animalList.remove(animal);
                    //出售动物
                    System.out.println("成功出售"+animal);
                    //顾客到店次数加一
                    customer.setArrivalTimes(customer.getArrivalTimes()+1);
                    //将顾客到店时间更新为当前时间
                    customer.setLastestArrivalTime(LocalDate.now());
                    return;
                }
            }
        }
    }

    @Override
    public boolean close() {
        if(isOpen){
            //歇业并输入今日利润
            System.out.println("宠物店歇业");
            LocalDate localDate = LocalDate.now();
            System.out.println("今日利润为"+profits+"元");
            //输出今日光临的顾客
            System.out.println("今日光临的顾客如下");
            for(Customer customer : customerList){
                System.out.println(customer);
            }
            this.isOpen = false;
        }
        return false;
    }

    @Override
    public boolean open() {
        if(!isOpen){
            //若顾客列表为空直接开业
            if(customerList.isEmpty()){
                System.out.println("宠物店开业");
                this.setProfits(0.0);
                this.isOpen = true;
            }else {
                //若顾客列表不为空删除前天的顾客列表
                for (int i = customerList.size()-1; i >= 0; i--) {
                    customerList.remove(customerList.get(i));
                }
                //开业并重置利润
                System.out.println("宠物店开业");
                this.setProfits(0.0);
                this.isOpen = true;
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
