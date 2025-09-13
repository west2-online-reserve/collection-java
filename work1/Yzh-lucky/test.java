package Work1;
import java.time.LocalDate;

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
