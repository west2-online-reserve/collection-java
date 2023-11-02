package src;

import java.time.LocalDate;
import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop{




    /*
            变量：
            店的余额double
            一个存放动物的列表 (使用ArrayList、LinkedList或其他你喜欢的List实现)
            一个顾客列表留作纪念
            是否正在营业
            ....
            实现接口中的方法

            买入动物 -> 买入一只动物，记得在动物列表中添加，

            如余额不足则抛出异常InsufficientBalanceException

            招待客户 -> 接受客户参数，在顾客列表中加入新顾客，

            出售动物，如果店内没有动物，抛出AnimalNotFoundException。

            通过toString输出动物信息，并把钱入账，将动物移除列表

            歇业 -> (LocalDate判断) 输出当天光顾的客户的列表信息，计算并输出一天的利润

             */
    private double insufficientBalance;
    private ArrayList listOfAnimal;
    private ArrayList<Customer> listOfCustomer;
    private boolean isShutDown;
    public MyAnimalShop() {
    }

    public MyAnimalShop(double insufficientBalance, ArrayList listOfAnimal, ArrayList listOfCustomer, boolean isShutDown) {
        this.insufficientBalance = insufficientBalance;
        this.listOfAnimal = listOfAnimal;
        this.listOfCustomer = listOfCustomer;
        this.isShutDown = isShutDown;
    }
    public double getInsufficientBalance() {
        return insufficientBalance;
    }

    public void setInsufficientBalance(double insufficientBalance) {
        this.insufficientBalance = insufficientBalance;
    }

    public ArrayList getListOfAnimal() {
        return listOfAnimal;
    }

    public void setListOfAnimal(ArrayList listOfAnimal) {
        this.listOfAnimal = listOfAnimal;
    }

    public ArrayList getListOfCustomer() {
        return listOfCustomer;
    }

    public void setListOfCustomer(ArrayList listOfCustomer) {
        this.listOfCustomer = listOfCustomer;
    }

    public boolean isShutDown() {
        return isShutDown;
    }

    public void setShutDown(boolean shutDown) {
        isShutDown = shutDown;
    }



    //买入动物 -> 买入一只动物，记得在动物列表中添加，
@Override
    public void buyNewAnimals( AbstractAnimal animal) {
        if(insufficientBalance<animal.getPurchasingPrice()){
            throw new InsufficientBalanceException(" 余额不足，购买失败 ");
    }
    listOfAnimal.add(animal);
    setInsufficientBalance(insufficientBalance-animal.getPurchasingPrice());
    }

   // 招待客户 -> 接受客户参数，在顾客列表中加入新顾客，
    @Override
    public void serveCustomer(Customer customer) {
        listOfCustomer.add(customer);
    }

   // 歇业 -> (LocalDate判断) 输出当天光顾的客户的列表信息，计算并输出一天的利润
    @Override
    public void shutdown(LocalDate localDate,ArrayList listOfCustomer) {
        double incomeOfToday=0;
        for(int i=0;i<listOfCustomer.size();i++){
            Customer c= (Customer) listOfCustomer.get(i);
            if(c.getLocalVisit().compareTo(localDate)==0){
                AbstractAnimal animal=c.getLocalBuy();
                System.out.println(c.toString());
                incomeOfToday=incomeOfToday+(animal.getSellingPrice()-animal.getPurchasingPrice());
             }
         }
        System.out.println(" 当天利润 "+incomeOfToday);
    }

    /*出售动物，如果店内没有动物，抛出AnimalNotFoundException。

通过toString输出动物信息，并把钱入账，将动物移除列表
     */
    @Override
    public void sellAnimals(Customer customer, ArrayList listOfAnimal) {
        AbstractAnimal animal = customer.getLocalBuy();
        if (listOfAnimal.contains(animal) == false) {
            throw new AnimalNotFoundException("该动物已被售卖，交易失败");
        }
        System.out.println(" 卖出动物的 " + animal.toString());
        setInsufficientBalance(insufficientBalance + animal.getSellingPrice());
        listOfAnimal.remove(animal);
    }
}
