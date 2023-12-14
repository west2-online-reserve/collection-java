import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author think
 */
public class MyAnimalShop implements AnimalShop{
    private double balance;
    private double profit ;
    private boolean isClosed;
    private ArrayList<Animal> animalArrayList = new ArrayList<>();
    private ArrayList<Customer> customerArrayList = new ArrayList<>();

    public MyAnimalShop() {
    }
    public MyAnimalShop(double balance,boolean isClosed){
        this.balance = balance;
        this.isClosed=isClosed;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public boolean getIsClosed(){
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public ArrayList<Animal> getAnimalArrayList() {
        return animalArrayList;
    }

    public void setAnimalArrayList(ArrayList<Animal> animalArrayList) {
        this.animalArrayList = animalArrayList;
    }

    public ArrayList<Customer> getCustomerArrayList() {
        return customerArrayList;
    }

    public void setCustomerArrayList(ArrayList<Customer> customerArrayList) {
        this.customerArrayList = customerArrayList;
    }



    @Override
    public void buy(Animal animal) {
        if (animal.getPrice() > this.balance) {
            throw new InsufficientBalanceException("余额不足！");
        }else{
            animalArrayList.add(animal);
            this.profit=this.profit-animal.getPrice();
            this.balance=this.balance - animal.getPrice();
            System.out.println("进货成功。");
        }
    }
    public void sell(Animal animal){
        if(!animalArrayList.contains(animal)) {
            throw new AnimalNotFountException("店里没有该动物");
        }else{
            animalArrayList.remove(animal);
            this.profit=this.profit+animal.getPrice();
            this.balance=this.balance + animal.getPrice();
            System.out.println("卖出了1只" + animal.toString());
        }
    }

    int q = 0 ;
    public void reception(Customer customer) {
        if(!getIsClosed()){

            if(customerArrayList.contains(customer)){
                System.out.println("欢迎再次光临！");
            }else{
                System.out.println("欢迎光临！");
            }
            customer.setTimes(customer.getTimes()+1);
            customer.setTime(LocalDate.now());
            System.out.println("我们这里有：");
            for (Animal animal1 : animalArrayList) {
                System.out.println(animal1);
            }

            System.out.println(customer.getName() + "先生（女士），请问您想要哪只宠物？");
            Scanner scanner1 = new Scanner(System.in);
            String animal =scanner1.nextLine();

            int a=1;
            for (int i = 0; i < animalArrayList.size(); i++) {
                if(animalArrayList.get(i).getName().equals(animal)){
                    a=0;
                    System.out.println("好的，谢谢惠顾！");
                    animalArrayList.remove(i);
                    this.profit=this.profit+animalArrayList.get(i).getPrice();
                    this.balance=this.balance+animalArrayList.get(i).getPrice();
                }
            }
            if(a==1){
                throw new AnimalNotFountException("店内没有该动物。");
            }

        }else{
            System.out.println("店铺已经歇业。");
        }

    }
    @Override
    public void close() {
        if(!getIsClosed()){
            this.isClosed = true;
        }
        System.out.println("我们歇业啦！");
        for (int i = 0; i < customerArrayList.size(); i++) {
            if(customerArrayList.get(i).getTime().isEqual(LocalDate.now())){
                System.out.println("今日顾客："+customerArrayList.get(i).toString());
            }
        }
        System.out.println("今日利润："+this.profit);
        System.out.println("余额："+this.balance);
    }

    public void reopen(){
        if(getIsClosed()){
            this.isClosed = false;
        }
        this.profit = 0.0 ;
        System.out.println("今天又是美好的一天~");
    }

}
