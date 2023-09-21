import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        LocalDate localDate=LocalDate.now();
        if(localDate.toString()==LocalDate.now().toString())System.out.print("test");
        Cat cat1=new Cat("mewwwww",18,200,true);
        Dog dog1=new Dog("wowwowwow",8,200,false);
        System.out.println(cat1.toString()+"\n"+dog1.toString());

        ArrayList<Animal>animalArrayList=new ArrayList<Animal>();
        animalArrayList.add(cat1);
        animalArrayList.add(dog1);

        Customer customer1=new Customer("robinrugan");
        Customer customer2=new Customer("mewchao");


        ArrayList<Customer>customerArrayList=new ArrayList<Customer>();
        customerArrayList.add(customer1);
        customerArrayList.add(customer2);


        MyAnimalShop myAnimalShop=new MyAnimalShop(0,customerArrayList,animalArrayList);
        myAnimalShop.printShop();

        myAnimalShop.entertainCustomers(customer2);
        myAnimalShop.printShop();

        Cat cat3=new Cat("mayuxin",18,200,false);
        myAnimalShop.addAnimal(cat3);
        myAnimalShop.printShop();

        try {
            myAnimalShop.addAnimal(cat3);
        }catch (InsufficientBalanceException e){
            e.printDetailedError();
        }

        myAnimalShop.entertainCustomers(customer1);

        try {
            myAnimalShop.entertainCustomers(customer1);
        }catch (AnimalNotFountException e){
            e.printDetailedError();
        }
        myAnimalShop.setnotOpened();
        try {
            myAnimalShop.entertainCustomers(customer2);
        }catch (AnimalNotFountException e){
            e.printDetailedError();
        }

    }
}