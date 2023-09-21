import com.sun.org.apache.xml.internal.security.keys.content.SPKIData;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @projectName（项目名称）: west2online_work2
 * @className（类名称）: MyAnimalShop
 * @description（类描述）: 我的宠物类
 * @author（创建人）: mewchao
 * @createDate（创建时间）: 2023-09-18
 */
public class MyAnimalShop implements AnimalShop {
    /**
     * 用于记录每日利润的两个变量
     * */
    double todayMoney=0;
    LocalDate lastedlocalDate;
    /**
     * 正在营业
     **/
    boolean isOpened = true;
    /**
     * 店内余额
     **/
    private double balance;
    /**
     * 顾客列表留作纪念
     **/
    ArrayList<Customer> customerList = new ArrayList<>();
    /**
     * 店内动物列表
     **/
    ArrayList<Animal> animalList = new ArrayList<>();

    public MyAnimalShop(double balance, ArrayList<Customer> customerList, ArrayList<Animal> animalList) {
        this.balance = balance;
        this.animalList = animalList;
        this.customerList = customerList;
        this.lastedlocalDate=LocalDate.now();
    }

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
    }

    public void printShop() {
        System.out.println("\n\nthe info of MyAnimalShop:" + "\nbalance:" + balance);
        if (!this.animalList.isEmpty()) {
            System.out.print("the animalList of MyAnimalShop:\n");
            for (Animal a : this.animalList) {
                System.out.println(a.toString());
            }
        }
        else{
            System.out.println("the animalList of MyAnimalShop is empty!!");
        }
        if(!this.customerList.isEmpty()) {
            System.out.print("the customerList of MyAnimalShop:\n");
            for (Customer c : this.customerList) {
                System.out.println(c.toString());
            }
        }else{
            System.out.println("the customerList of MyAnimalShop is empty!!");
        }
    }

    /**
     * @description:歇业 -> (LocalDate判断) 输出当天光顾的客户的列表信息，计算并输出一天的利润
     **/
    public void setnotOpened() {
        this.isOpened = false;
        LocalDate localDate=LocalDate.now();
        for(Customer c:customerList){
            System.out.print("今天到达店内的顾客有:");
            if(c.getLatestArrivalTime().toString()==localDate.toString()){
                   c.toString();
            }
        }

        System.out.print("今天店内的利润为:"+todayMoney+"\n");
    }

    /**
     * @param animal: 动物
     * @description: 为宠物店购入一只宠物
     */
    @Override
    public void addAnimal(Animal animal) {
        if (this.balance >= animal.getPrice()) {
            balance -= animal.getPrice();
            animalList.add(animal);
        } else {
            throw new InsufficientBalanceException(balance, animal.getPrice());
        }
        if(this.lastedlocalDate.toString()==LocalDate.now().toString()){
            todayMoney-=animal.getPrice();
        }else{
            this.lastedlocalDate=LocalDate.now();
            todayMoney=0;
        }
    }

    /**
     * @param customer : 顾客
     * @description: 招待顾客，如果动物列表为空则抛出异常,否则输出动物信息，赚钱，移除列表
     */
    @Override
    public void entertainCustomers(Customer customer) {
        if(!this.isOpened){
            System.out.print("抱歉，该店铺已经关门了！！\n\n");
        }
        boolean isNew = true;
        for (Customer c : customerList) {
            if (c == customer) {
                isNew = false;
                c.visitTimes++;
                c.LatestArrivalTime = LocalDate.now();
            }
        }
        if (isNew) {
            customerList.add(customer);
        }
        if (animalList.isEmpty()) throw new AnimalNotFountException();
        else {
            for (Animal a : animalList) {
                System.out.print("正在招待顾客的动物是:");
                System.out.print(a.toString());
                this.balance += a.getPrice();
                if(this.lastedlocalDate.toString()==LocalDate.now().toString()){
                    todayMoney+=a.getPrice();
                }else{
                    this.lastedlocalDate=LocalDate.now();
                    todayMoney=0;
                }
                System.out.println("此次收入为:"+a.getPrice()+"余额为："+balance+"\n");
            }
            animalList.removeAll(animalList);
        }


    }
}
