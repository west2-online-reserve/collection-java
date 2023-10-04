import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

interface AnimalShop{
    void BuyAnimal(Animal animal,MyAnimalShop shop);
    void ServiceForCustomer(Customer customer,MyAnimalShop shop);
    void StopShopping();
}



public class MyAnimalShop implements AnimalShop{
    private double LeftMoney;
    private boolean isShopping;
    ArrayList<Animal> AnimalList=new ArrayList<>();
    ArrayList<Customer> CustomerList=new ArrayList<>();
    public MyAnimalShop(double LeftMoney,boolean isShopping){
        this.LeftMoney=LeftMoney;
        this.isShopping=isShopping;
    }

    @Override
    public String toString() {
        return "MyAnimalShop{" +
                "LeftMoney=" + LeftMoney +
                ", isShopping=" + isShopping +
                ", AnimalList=" + AnimalList +
                ", CustomerList=" + CustomerList +
                '}';
    }

    public ArrayList<Animal> getAnimalList() {
        return AnimalList;
    }

    public void setAnimalList(ArrayList<Animal> animalList) {
        AnimalList = animalList;
    }

    public ArrayList<Customer> getCustomerList() {
        return CustomerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        CustomerList = customerList;
    }

    public double getLeftMoney() {
        return LeftMoney;
    }

    public void setLeftMoney(double leftMoney) {
        LeftMoney = leftMoney;
    }

    public boolean isShopping() {
        return isShopping;
    }

    public void setShopping(boolean shopping) {
        isShopping = shopping;
    }

    @Override
    public void BuyAnimal(Animal animal,MyAnimalShop shop) {//买入动物
        if (shop.LeftMoney<animal.price) {
            throw new InsufficientBalanceException("Such a poor guy don't deserve this pet");
        } else {
            shop.LeftMoney=shop.LeftMoney-animal.price;
            AnimalList.add(animal);
            System.out.println("You got the perfect animal");
        }

    }

    @Override
    public void ServiceForCustomer(Customer customer,MyAnimalShop shop) {
        CustomerList.add(customer);
        if (AnimalList.size()==0){
            throw new AnimalNotFountException("there's no animal for you");
        } else {
            for (Animal animal:AnimalList){
                System.out.println(animal.toString());//遍历list输出动物信息
            }
            System.out.println("please input the number of the animal you want");
            Scanner scanner=new Scanner(System.in);
            int num=scanner.nextInt();
            shop.LeftMoney=shop.LeftMoney+AnimalList.get(num).price+100;
            AnimalList.remove(num);
            customer.ArrivingTime=LocalDate.now();
        }

    }

    @Override
    public void StopShopping() {
            int a=0;
            for (Customer customer:CustomerList){
                if (customer.ArrivingTime.equals(LocalDate.now())){
                    System.out.println(customer.toString());
                    a=a+100;//因为设定中每个顾客买的动物利润都是100
                }
            }
            System.out.println(a);
    }
}
