import Instance.Animal;
import Instance.Animals.Cat;
import Instance.Customers.Customer;
import Instance.MyAnimalShop;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Animal> animals = new ArrayList<Animal>();
        ArrayList<Customer> customers=new ArrayList<Customer>();
        MyAnimalShop animalShop=new MyAnimalShop(100,animals,customers,true);
        Cat cat=new Cat("小黑",3,"母");
        Customer customer=new Customer("王南皓",1);
    try {
        System.out.println("测试买入动物");
        animalShop.buyAnimal(cat,1);
        }
    catch (Exception e){
        System.out.println(e.getMessage());
    }
    try {
        System.out.println("----------------------------------");
        System.out.println("测试招待客户");
        animalShop.welcomeCustomer(customer,cat);
    }
    catch (Exception e){
        System.out.println(e.getMessage());
    }
        System.out.println("----------------------------------");
        System.out.println("测试歇业");
        animalShop.closeShop();
    }
}