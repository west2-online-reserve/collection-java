import java.time.LocalDate;

public class Test {
    public static void main(String[] args){
        MyAnimalShop shop=new MyAnimalShop(1000,true);
        Animal lingo=new dog("lingo",5,"M","dog",true);
        Animal kelly=new cat("kelly",2,"F","cat");
        Animal animal=new hamster("hu",3,"M","hamster");
        shop.buyAnimal(animal);
        shop.buyAnimal(kelly);
        shop.buyAnimal(lingo);
        System.out.println(shop.toString());
        Customer customer1=new Customer("XQX",3, LocalDate.now());
        Customer customer2=new Customer("peter",5, LocalDate.now());
        shop.serviceForCustomer(customer1);
        shop.serviceForCustomer(customer2);
        System.out.println(shop.toString());
        shop.stopShopping();
    }
}















