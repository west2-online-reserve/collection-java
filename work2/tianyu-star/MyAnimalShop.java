import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

interface AnimalShop{
    void buyAnimal(Animal animal, MyAnimalShop shop);
    void serviceForCustomer(Customer customer, MyAnimalShop shop);
    void stopShopping();
}



/**
 * @author xuqianxun
 */
public class MyAnimalShop implements AnimalShop{
    private double leftMoney;
    private boolean isShopping;
    ArrayList<Animal> animalList =new ArrayList<>();
    ArrayList<Customer> customerList =new ArrayList<>();
    public MyAnimalShop(double leftMoney,boolean isShopping){
        this.leftMoney =leftMoney;
        this.isShopping=isShopping;
    }

    @Override
    public String toString() {
        return "MyAnimalShop{" +
                "LeftMoney=" + leftMoney +
                ", isShopping=" + isShopping +
                ", AnimalList=" + animalList +
                ", CustomerList=" + customerList +
                '}';
    }

    public ArrayList<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(ArrayList<Animal> animalList) {
        this.animalList = animalList;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public double getLeftMoney() {
        return leftMoney;
    }

    public void setLeftMoney(double leftMoney) {
        this.leftMoney = leftMoney;
    }

    public boolean isShopping() {
        return isShopping;
    }

    public void setShopping(boolean shopping) {
        isShopping = shopping;
    }

    @Override
    public void buyAnimal(Animal animal, MyAnimalShop shop) {//买入动物
        if (shop.leftMoney <animal.price) {
            throw new InsufficientBalanceException("Such a poor guy don't deserve this pet");
        } else {
            shop.leftMoney =shop.leftMoney -animal.price;
            animalList.add(animal);
            System.out.println("You got the perfect animal");
        }

    }

    @Override
    public void serviceForCustomer(Customer customer, MyAnimalShop shop) {
        customerList.add(customer);
        if (animalList.isEmpty()){
            throw new AnimalNotFountException("there's no animal for you");
        } else {
            for (Animal animal: animalList){
                System.out.println(animal.toString());//遍历list输出动物信息
            }
            System.out.println("please input the number of the animal you want");
            Scanner scanner=new Scanner(System.in);
            int num=scanner.nextInt();
            shop.leftMoney =shop.leftMoney + animalList.get(num).price+100;
            animalList.remove(num);
            customer.ArrivingTime=LocalDate.now();
        }

    }

    @Override
    public void stopShopping() {
            int a=0;
            for (Customer customer: customerList){
                if (customer.ArrivingTime.equals(LocalDate.now())){
                    System.out.println(customer.toString());
                    a=a+100;//因为设定中每个顾客买的动物利润都是100
                }
            }
            System.out.println(a);
    }
}
