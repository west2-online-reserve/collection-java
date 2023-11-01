import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

//interface AnimalShop{
//    void buyAnimal(Animal animal, MyAnimalShop shop);
//    void serviceForCustomer(Customer customer, MyAnimalShop shop);
//    void stopShopping();
//}



/**
 * @author xuqianxun
 * 我在MyAnimalShop的类中加了初始资金的字段，能更简单地算出总利润
 */
public class MyAnimalShop implements AnimalShop{
    private double leftMoney;
    private boolean isShopping;
    private double firstMoney;
    private ArrayList<Animal> animalList =new ArrayList<>();
    private ArrayList<Customer> customerList =new ArrayList<>();
    public MyAnimalShop(double leftMoney,boolean isShopping){
        this.leftMoney =leftMoney;
        this.isShopping=isShopping;
        this.firstMoney=leftMoney;
    }

    @Override
    public String toString() {
        return "MyAnimalShop{" +
                "LeftMoney=" + leftMoney +
                ", isShopping=" + isShopping +
                ", AnimalList=" + animalList +
                ", CustomerList=" + customerList +
                ", firstMoney=" + firstMoney +
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
    public void buyAnimal(Animal animal) {//买入动物
        try{
        if (this.leftMoney < animal.price) {
            throw new InsufficientBalanceException("Such a poor guy don't deserve this pet");
        } else {
            this.leftMoney = this.leftMoney - animal.price;
            this.animalList.add(animal);
        }
        } catch (InsufficientBalanceException e){
           e.printStackTrace();
        }

    }

    @Override
    public void serviceForCustomer(Customer customer) {

        try {
            if (this.animalList.isEmpty()) {
                throw new AnimalNotFountException("there's no animal for you");
            } else {
                this.customerList.add(customer);
                System.out.println("welcome,please input how many animals you want");
                Scanner scanner = new Scanner(System.in);
                int num = scanner.nextInt();
                if (num == 0) {
                    System.out.println("you buy nothing");
                } else {

                    System.out.println("please input the animal you want");
                    String[] n = new String[num];
                    for (int k = 0; k < num; k++) {
                        n[k] = scanner.next();//记录每个买入的名字，对比字段属性来判断删除哪个
                    }
                    Iterator<Animal> it = this.animalList.iterator();//使用iterator遍历列表，实现遍历时修改列表
                    while (it.hasNext()) {
                        Animal animal = it.next();
                        for (int m = 0; m < num; m++) {
                            if (animal.name.equals(n[m])) {
                                this.leftMoney = this.leftMoney + animal.price + 100;
                                it.remove();
                            }
                        }


                    }


                }customer.setArrivingTime(LocalDate.now());
                System.out.println("thank you for buying");
            }
        } catch (AnimalNotFountException a){
            a.printStackTrace();
        }


    }

    @Override
    public void stopShopping() {
            double a;
            for (Customer customer: this.customerList){
                if (customer.getArrivingTime().equals(LocalDate.now()))
                {
                    System.out.println(customer.toString());
                }
            }
         a=this.leftMoney-this.firstMoney;
            System.out.printf("the profit is %f",a);
    }
}
