package PetShop;

import java.time.LocalDate;
import java.util.ArrayList;

//我的宠物店类
public class MyAnimalShop implements AnimalShop {
    private double balance;
    private double balanceAfterBuy;
    private double income;
    private double sell;
    private ArrayList<Animal> animalList;
    private ArrayList<Customer> customerList;

    //买入动物
    @Override
    public void buyNewAnimal(Animal animal) throws InsufficientBalanceException{
        //若余额不足抛出异常
        if (balance < animal.getPurchasePrice ()) {
            throw new InsufficientBalanceException ("余额不足");
        }
        animalList.add(animal);
        balance -= animal.purchasePrice;
        balanceAfterBuy = balance;
    }

    //招待顾客
    @Override
    public void serveCustomer(Customer customer, Animal animal){
        //更新顾客列表
        updateCustomerList(customer);
        //顾客买走动物
        sellAnimal(animal);
    }

    //歇业
    @Override
    public void close(){
        System.out.println ("该休息了");
        System.out.println ("今天来的客人有：");
        chooseCustomer();
        System.out.println ("今天卖了：" + sell + "块钱💴");
        System.out.println ("利润为：" + income);
    }

    //卖出动物
    public void sellAnimal(Animal animal)throws AnimalNotFountException{
        if(animalList.isEmpty ()){
            throw new AnimalNotFountException("不好意思，动物卖完了");
        }

        //找到要卖的动物
        if(animalList.contains(animal)){
            //余额加入卖出动物的钱，动物列表中删除动物
            balance+=animal.getSellPrice();
            sell+=animal.getSellPrice();
            income+=(animal.getSellPrice()-animal.getPurchasePrice());
            animalList.remove(animal);
        }

        //找不到就抛出异常
        else{
            throw new AnimalNotFountException("不好意思," + animal.animalName + "卖完了");
        }
    }

    //更新顾客列表
    public void updateCustomerList(Customer customer){
        customer.setVisitCount(customer.getVisitCount ()+1);
        if(customerList.contains(customer)){
            //最近来访时间改为今天
            customer.setDate(customer.getDate());
        }

        //如果顾客没来过
        else{
            //把顾客加入列表
            customerList.add(customer);
        }
    }

    //从顾客列表中筛选出当天来的顾客并输出
    public void chooseCustomer(){
        for (Customer customer : customerList) {
            //筛选出当天来的顾客
            if (customer.getDate ().equals (LocalDate.now ())) {
                System.out.println (customer);
            }
        }
    }

    //买完动物后的余额
    public void showBalanceAfterBuy(){
        System.out.println ("买完动物后的余额为：" + balanceAfterBuy);
    }

    //卖完动物后的余额
    public void showBalanceAfterSell(){
        System.out.println ("卖完动物后的余额为：" + balance);
    }

    public MyAnimalShop(){}

    public MyAnimalShop(double balance){
        this.balance = balance;
        this.animalList = new ArrayList<> ();
        this.customerList = new ArrayList<> ();
    }

    public double getBalance () {
        return balance;
    }

    public void setBalance (double balance) {
        this.balance = balance;
    }

    public double getIncome () {
        return income;
    }

    public void setIncome (double income) {
        this.income = income;
    }

    public ArrayList<Animal> getAnimalList () {
        return animalList;
    }

    public void setAnimalList (ArrayList<Animal> animalList) {
        this.animalList = animalList;
    }

    public ArrayList<Customer> getCustomerList () {
        return customerList;
    }

    public void setCustomerList (ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }
}
