package work2;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        ArrayList animalList=new ArrayList();
        ArrayList customerList=new ArrayList();
        LocalDate defaultDate=LocalDate.of(2000,1,1);

        Animal cat1=new Cat("HuaHua",2,"male");
        Animal dog1=new Dog("Wang",3,"female",true);
        Animal dog2=new Dog("Gosh",5,"male",true);
        Animal fox1=new Fox("FuFu",3,"female",500,"pure");
        Animal fox2=new Fox("BuFu",3,"female",1000,"pure");

        Customer customer1=new Customer("Smith",0,defaultDate);
        Customer customer2=new Customer("Jack",0,defaultDate);

        MyAnimalShop shop=new MyAnimalShop(1500,animalList,customerList,false);

        try {
            shop.purchaseAnimal(cat1);
            shop.purchaseAnimal(dog1);
            shop.purchaseAnimal(dog2);
            shop.purchaseAnimal(fox1);
            shop.purchaseAnimal(fox2);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }

        try {
            shop.entertainCustomer(customer1,dog1);
            shop.entertainCustomer(customer1,dog2);
            shop.entertainCustomer(customer2,cat1);
            shop.entertainCustomer(customer1,fox1);
            shop.entertainCustomer(customer1,fox2);
        } catch (AnimalNotFountException e) {
            System.out.println(e.getMessage());
        }

        shop.closeDown();
        shop.closeDown();

    }
}
