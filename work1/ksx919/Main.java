import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

abstract class Animal{
    protected String name;
    protected int age;
    protected String sex;
    protected double price;

    public Animal(String name,int age,String sex,double price)
    {
        this.name=name;
        this.age=age;
        this.sex=sex;
        this.price=price;
    }
    public Animal(){};

    public abstract String toString();
}

class GardenDog extends Animal{
    private boolean isVaccineInjected;
    public GardenDog(String name,int age,String sex,boolean isVaccineInjected)
    {
        this.name=name;
        this.age=age;
        this.sex=sex;
        this.isVaccineInjected=isVaccineInjected;
        this.price=100;
    }
    public String toString()
    {
        String a=isVaccineInjected?"是":"否";
        return String.format("动物名：%s，年龄：%d，性别：%s，价格：%.2f，是否注射狂犬疫苗：%s",name,age,sex,price,a);
    }
}

class Cat extends Animal{
    public Cat(String name,int age,String sex)
    {
        this.name=name;
        this.age=age;
        this.sex=sex;
        this.price=200;
    }
    public String toString()
    {
        return String.format("动物名：%s，年龄：%d，性别：%s，价格：%.2f",name,age,sex,price);
    }
}

class Customer{
    protected String name;
    protected int count;
    protected LocalDate lastdate;

    public Customer(String name,int count,LocalDate lastdate)
    {
        this.name=name;
        this.count=count;
        this.lastdate=lastdate;
    }
    public String toString()
    {
        return String.format("顾客名：%s，到店次数：%d，最新到店时间：%tF",name,count,lastdate);
    }
}

interface AnimalShop{
    void buyAnimal(Animal animal);
    void serveCustomer(Customer customer) throws AnimalNotFoundException;
    void closeShop();
}

class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animals = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private boolean isOpen;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) {
        if (balance >= animal.price) {
            animals.add(animal);
            balance -= animal.price;
        } else {
            throw new InsufficientBalanceException("Insufficient funds to buy the animal");
        }
    }

    @Override
    public void serveCustomer(Customer customer) {
        while (!animals.isEmpty()) { // 循环直到动物列表为空
            Animal animal = animals.remove(0); // 销售列表中的第一个动物
            System.out.println(animal); // 打印动物信息
            balance += animal.price; // 更新余额
        }
        customers.add(customer); // 将顾客添加到顾客列表
    }

    @Override
    public void closeShop() {
        isOpen = false;
        System.out.println("Closing shop...");
        // Display all today's customers
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}

class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class Test {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(1000.0);
        shop.buyAnimal(new Cat("Whiskers", 2, "Male"));
        shop.buyAnimal(new GardenDog("Buddy", 3, "Male", true));

        Customer customer1 = new Customer("John Doe", 1, LocalDate.now());
        shop.serveCustomer(customer1);

        shop.closeShop();
    }
}