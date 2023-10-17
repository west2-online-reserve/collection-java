package homework.work2;

import java.time.LocalDate;
import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop{
    boolean x=false;
    String isOpen="营业";
    public MyAnimalShop(double balance,double initial) {
        this.balance = balance;
        this.initial=initial;
    }

    double balance;
    double initial;


    ArrayList<Animal> animalList=new ArrayList<>();
    ArrayList<Customer> customerList=new ArrayList<>();

    //动物进货
    @Override
    public void buyAnimals(Animal a) {

        animalList.add(a);
        balance-=a.petImport;
        if(balance<0){
            throw new InsufficientBalanceException("余额不足！无法进货");
        }

    }

    //动物的出售
    @Override
    public void treatCustomers(Animal a,Customer b) {
        x=true;
        animalList.remove(a);
        System.out.println("购买成功！");
        balance+=a.petPrice;
        customerList.add(b);
        System.out.println("店内的动物"+this.animalList);
        //System.out.println(customerList);
        if(x==false){
            throw new AnimalNotFountException("店内没有该动物！");
        }

    }

    //计算利润
    @Override
    public void isOpenShop() {

        this.isOpen="歇业";
        System.out.println(this.isOpen);
        System.out.println("今天的利润为"+(this.balance-initial));
        System.out.println("今天的顾客"+customerList);
    }
}
