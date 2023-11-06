import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author think
 */
public class MyAnimalShop implements AnimalShop{
    private double balance;
    ArrayList<Animal> animalArrayList = new ArrayList<>();
    ArrayList<Customer> customerArrayList = new ArrayList<>();
    private boolean isClosed;
    private int total;
    public MyAnimalShop() {
    }
    public MyAnimalShop(double balance,boolean isClosed,int total){
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }


    @Override
    public void buy(Animal animal,int num) {
        double buySum = 0;
        try {
            buySum = (animal.getPrice()-55) * num;
            if (buySum > this.balance) {
                throw new InsufficientBalanceException("余额不足");
            }
        } catch (InsufficientBalanceException e) {
            System.out.println(e.toString());
        }
        if (num > 0&& buySum <this.balance) {
            this.balance = this.balance - buySum;
            for (int i = 0; i < num; i++) {
                animalArrayList.add(animal);
            }
            System.out.println("购买成功,购买了" + num + "只" + animal);
        }
        if (num<0) {
            System.out.println("购买失败，购买数量应大于0");
        }
    }
    public void sell(Animal animal,int num){
        try {
            if (this.total<num) {
                throw new AnimalNotFountException("店内没有动物");
            }
        } catch (AnimalNotFountException e) {
            System.out.println(e.toString());
        }
        if (num>0){
            this.balance = this.balance + animal.getPrice() * num;
            System.out.println("已移除出列表的宠物：" + num + "只" + animal.toString());
            for (int i = 0; i <  animalArrayList.size(); i++) {
                if (animalArrayList.get(i).equals(animal)){
                    animalArrayList.remove(i);
                }
            }
            System.out.println("卖出了" + num + "只" + animal.toString());
        }
    }


    public void reception(Customer customer) {
        for (int i = 0; i < customerArrayList.size(); i++) {
            if(customerArrayList.get(i).getName().equals(customer.getName())) {
                System.out.println("欢迎再次光临");
            }else {
                customerArrayList.add(customer);
                System.out.println("欢迎光临");
                customer.setTimes(customer.getTimes()+1);
            }
        }
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
            }
        }
        if(a==1){
            System.out.println("很抱歉，我们这里没有这只宠物.");
        }

    }
    @Override
    public void isClosed() {
        if(LocalDate.now().isAfter(LocalDate.of(2023,1,5))){
            System.out.println("已过零点，我们歇业啦！");
            double income=this.getBalance()-100000;
            System.out.println("今日利润为："+income);
        }else{
            System.out.println("正在营业中~");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(this.getBalance());
        return "今日余额："+sb.toString();
    }
}
