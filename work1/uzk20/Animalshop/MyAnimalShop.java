package work1.Animalshop;

import java.util.ArrayList;
import java.time.LocalDate;

class MyAnimalShop implements Animalshop{

    private double balance;
    private double retainedProfits;
    private int day=0;
    private ArrayList<Animal> animalList;
    private ArrayList<Customer> customerList;
    private boolean startBusiness;

    public void addOldCustomer(Customer customer){
        customerList.add(customer);
    }

    public MyAnimalShop(double b,boolean s){
        balance=b;
        retainedProfits=0;
        startBusiness=s;
        animalList=new ArrayList<Animal>();
        customerList=new ArrayList<Customer>();
    }

    public void isOperateNormally(){
        startBusiness= !sellOutJudge();
    }

    public void openShop(){
        if(startBusiness){
            System.out.println("今日正常营业，欢迎光临\n目前本店可供购买的动物如下：");
            System.out.println(animalList);
        }
        else{
            System.out.println("今日歇业，对给您造成的不便，本店深表歉意");
        }
    }

    @Override
    public void buyAnimal(Animal animal) {
        if(balance>=animal.purchasePrice){
            animalList.add(animal);
            balance-=animal.purchasePrice;
        }
        else{
            throw new InsufficientBalanceException("破产了,买不起"+animal.name);
        }
    }

    public boolean sellOutJudge(){
        return animalList.size()==0;
    }

    public boolean successfulService(Animal animal){
        return animalList.contains(animal);
    }

    public void sellAnimal(Animal animal) {
        if(sellOutJudge()){
            throw new AnimalNotFoundException("抱歉，但我们今天没"+animal.name+"可卖了");
        }
        if(successfulService(animal)){
            animalList.remove(animal);
            balance+=animal.sellingPrice;
            retainedProfits+=animal.sellingPrice-animal.purchasePrice;
        }
        else{
            throw new AnimalNotFoundException("我们店没有这种动物，抱歉");
        }
    }

    public void register(Customer customer){
        if(customerList.contains(customer)){
            int temp= customer.getTimes();
            customer.changeTimes(temp+1);
            customer.changeArrivalTime();
        }
        else{
            int temp=customer.getTimes();
            customer.changeTimes(temp+1);
            customer.changeArrivalTime();
            customerList.add(customer);
        }
    }

    @Override
    public void serveCustomer(Customer customer,Animal animal){
        if(!startBusiness)
        {
            throw new IllegalStateException("今日未开业，不提供顾客服务");
        }
        sellAnimal(animal);
        register(customer);
    }

    public void todayCustomer(){
        System.out.println();
        for (Customer customer : customerList) {
            if (customer.getArrivalTime().equals(LocalDate.now())) {
                System.out.println(customer);
            }
        }
    }

    public void ClearCustomer(){
        int judge=0;
        for(Customer customer : customerList) {
            if (customer.getArrivalTime().equals(LocalDate.now())) {
                judge=1;
                customer.backToYesterday();
            }
        }
        if(judge==0){
            System.out.println("哇今天一个顾客都没有");
        }
    }

    public void closeShop(){
        System.out.println("本店已打烊");
        System.out.println("Day "+day+":");
        System.out.println("今日净收入："+retainedProfits);
        System.out.println("今天的顾客列表如下：");
        todayCustomer();
        ClearCustomer();
        day++;
        retainedProfits=0;
        System.out.println();
    }

}
