import java.nio.file.attribute.UserPrincipalLookupService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop{

    //用来判断是否可以买入宠物
    private boolean a;
    //余额
    private double balance;
    //宠物品种
    private ArrayList<String> AnimalType;
    //顾客列表
    private ArrayList<Customer>  Customers;
    //想要买入的宠物
    private String wantAnimal;

    //构造函数
    public MyAnimalShop(ArrayList<String> animalType,double balance,ArrayList<Customer> Customers){
        AnimalType = new ArrayList<>();
        AnimalType.addAll(animalType);
        this.Customers = Customers;
        this.balance = balance;
    }

    //判断是或成功买入宠物
    public void buyAnimal(){
        if (wantAnimal.equals("ChineseDog")){
            if (balance >= 1000) {
                System.out.println("买入成功！");
                balance -= 1000;
            }

            else
                throw new InsufficientBalanceException("余额不足！！");
        }
        else if (wantAnimal.equals("Cat")){
            if (balance >= 2000){
                System.out.println("买入成功！");
                balance -= 2000;
            }
            else
                throw new InsufficientBalanceException("余额不足！！");
        }
        else if (wantAnimal.equals("Sheep")){
            if (balance >= 3000) {
                System.out.println("买入成功！");
                balance -= 3000;
            }

            else
                throw new InsufficientBalanceException("余额不足！！");
        }

    }

    //招待顾客
    public void serveCustomer(Customer customer){
        //交易过程
        System.out.println(customer.toString());
        System.out.println("欢迎光临!!!请问如何称呼您？");
        System.out.println("你好！我的名字是"+customer.getName());
        System.out.println("好的，请问您有什么需要的宠物吗?");
        System.out.println("我想要买一只"+customer.getWantToBuy());
        if (!AnimalType.contains(customer.getWantToBuy())) {
            throw new AnimalNotFountException("抱歉，我们没有这只宠物，请您换一个吧!");
        }
        else {
            for (int i=0;i<AnimalType.size();i++) {
                if (customer.getWantToBuy().equals(AnimalType.get(0)))
                    balance+=1000;
                else if (customer.getWantToBuy().equals(AnimalType.get(1)))
                    balance+=2000;
                else
                    balance+=3000;
            }
            AnimalType.remove(customer.getWantToBuy());
            System.out.println("好的！");
        }
        Customers.add(customer);
    }

    //是否营业
    public boolean isOpen(LocalDate localDate) {
        LocalDate[] notOpen = {LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 2),LocalDate.of(2021, 1, 3)};
        for (LocalDate a : notOpen) {
            if (a.equals(localDate)) {
                return false;
            }
        }
        return true;
    }

    //set
    public void setWantAnimal(String wantAnimal) {
        this.wantAnimal = wantAnimal;
    }
    //get
    public String getWantAnimal() {
        return wantAnimal;
    }
    public double getBalance() {
        return balance;
    }
}
