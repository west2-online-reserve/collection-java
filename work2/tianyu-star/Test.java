import java.time.LocalDate;

public class Test {
    public static void main(String[] args){
        MyAnimalShop shop=new MyAnimalShop(100,true);
        Animal animal=new dog("hu",2,"M",100,"dog",true);
        shop.buyAnimal(animal,shop);
        System.out.println(shop.toString());
        Customer customer=new Customer("XQX",3, LocalDate.now());
        shop.serviceForCustomer(customer,shop);
        System.out.println(shop.toString());

    }
}















