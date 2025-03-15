package Work1;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

//动物的抽象类
abstract class Animal {
    protected String name;
    protected int age;
    protected char sex;
    protected double price;
    protected double salePrice;
    //全参构造方法
    public Animal(String name,int age,char sex,double price,double salePrice) {
        this.name = name;
        this.age = age;
        this.sex=sex;
        this.price=price;
        this.salePrice=salePrice;
    }
    //抽象类
    public abstract void toString(String name, int age,char sex,double price,boolean isVaccineInjected);
    public abstract void action();

}
//======================================================================================================================
//一个中华田园犬类
class ChineseRuralDog extends Animal{
    public boolean isVaccineInjected=true;
    public ChineseRuralDog(String name,int age,char sex,double price,double salePrice) {
        super(name,age,sex,price,salePrice);
    }

    public void toString(String name, int age,char sex,double price,boolean isVaccineInjected){
        System.out.print("中华田园犬的名字: "+name+",年龄: "+age+",性别: "+sex+",价格: "+price);
        if(isVaccineInjected){
            System.out.println(",接种了疫苗");
        }else{
            System.out.println(",没有接种疫苗");
        }
    }

    public void action(){
        System.out.println("狗的叫声是汪汪。");
    }
}
//======================================================================================================================
//一个猫猫类
class Cat extends Animal{
    public boolean isVaccineInjected=true;

    public Cat(String name,int age,char sex,double price,double salePrice) {
        super(name,age,sex,price,salePrice);
    }

    public void toString(String name, int age,char sex,double price,boolean isVaccineInjected){
        System.out.print("这只猫的名字: "+name+",年龄: "+age+",性别: "+sex+",价格: "+price);
        if(isVaccineInjected){
            System.out.println(",接种了疫苗");
        }else{
            System.out.println(",没有接种疫苗");
        }
    }

    public void action(){
        System.out.println("猫的叫声是喵喵。");
    }
}
//======================================================================================================================
//一个羊类
class Sheep extends Animal{
    public boolean isVaccineInjected=true;

    public Sheep(String name,int age,char sex,double price,double salePrice) {
        super(name, age, sex, price,salePrice);
    }

    public void toString(String name, int age,char sex,double price,boolean isVaccineInjected){
        System.out.print("羊的名字: "+name+",年龄: "+age+",性别: "+sex+",价格: "+price);
        if(isVaccineInjected){
            System.out.println(",接种了疫苗");
        }else{
            System.out.println(",没有接种疫苗");
        }
    }

    public void action(){
        System.out.println("羊的叫声是咩咩。");
    }

}
//======================================================================================================================
//一个顾客类
class Customer {
    private String name;
    private int visitCount;
    private LocalDate dateTime;
    public Animal wantAnimal;

    public Customer(String name, int visitCount, LocalDate dateTime, Animal wantAnimal) {
        this.name = name;
        this.visitCount = visitCount;
        this.dateTime = dateTime;
        this.wantAnimal = wantAnimal;
    }

    public String toString() {
        return "顾客的名字："+this.name+",到店铺的次数："+this.visitCount+",最新到店时间"+this.dateTime+"，想要的动物是"+this.wantAnimal;
    }
}
//======================================================================================================================
//用来实现余额不足抛出异常
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
//用来实现宠物店没有所要的宠物而抛出异常
class AnimalNotFoundException extends Exception {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}
//这是一个宠物店的接口
interface AnimalShop {
    void buyAnimal(Animal animal) throws InsufficientBalanceException;
    void serveCustomer(Customer customer) throws AnimalNotFoundException;
    void closeDoor();
}
//=====================================================================================================================
//自己的宠物店
class MyAnimalShop implements AnimalShop {

    private double balance;
    public List<Animal> animalsList = new ArrayList<>();
    public List<Customer> customersList=new ArrayList<>();
    private boolean isOpen;//用来判断是否开关门

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException{
        if (balance<animal.price) {
            throw new InsufficientBalanceException("余额不足！！！");
        }
        balance-=animal.price;
        animalsList.add(animal);
    }

    public void showAnimals(){
        System.out.println("店铺里有：");
        for(Animal animal:animalsList){
            System.out.println(animal);
        }
    }
    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFoundException {
        customersList.add(customer);
        Animal sold=customer.wantAnimal;
        if (animalsList.isEmpty()){
            throw new AnimalNotFoundException("店内没有所需要的宠物!!!");
        }
        Animal soldAnimal = animalsList.remove(0);
        balance+=soldAnimal.salePrice;
        System.out.println("出售了"+soldAnimal+"给"+customer);
    }
    @Override
    public void closeDoor() {
        System.out.println("当天光顾的顾客链表:");
        for (Customer customer : customersList){
            System.out.println(customer);
        }
        System.out.println();
        System.out.println("店铺中还剩：");
        for (Animal animal : animalsList){
            System.out.print(animal+" ");
        }
        System.out.println();
        double profit=calculateProfit();
        System.out.println("一天的利润为"+profit);
    }
    private double calculateProfit(){
        return balance;
    }
}
//======================================================================================================================


//======================================================================================================================
public class test {
    public static void main(String[] args) throws  Exception{
        MyAnimalShop myAnimalShop = new MyAnimalShop(5300);
        ChineseRuralDog dog=new ChineseRuralDog("旺财",10,'雄',1000,1500);
        Cat cat=new Cat("小橘",2,'雌',2000,3000);
        Sheep sheep=new Sheep("小白",1,'雌',2300,2500);
        LocalDate dateTime=LocalDate.now();
        Customer customer1=new Customer("张三",23,dateTime,dog);
        Customer customer2=new Customer("里斯",25,dateTime,sheep);

        myAnimalShop.buyAnimal(dog);
        myAnimalShop.buyAnimal(cat);
        myAnimalShop.buyAnimal(sheep);
        myAnimalShop.showAnimals();
        System.out.println();
        myAnimalShop.serveCustomer(customer1);
        myAnimalShop.serveCustomer(customer2);
        System.out.println();
        myAnimalShop.closeDoor();
    }
}
