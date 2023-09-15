import java.time.LocalDate;

public class Test {
    public static void main(String[] args){
        AnimalShop animalShop=new MyAnimalShop();
        Cat cat=new Cat("一只猫",4,true,6);
        animalShop.buyAnimal(cat);
        ChineseRuralDog chineseRuralDog=new ChineseRuralDog("一只狗",4,true,2);
        animalShop.buyAnimal(chineseRuralDog);
        Customer customer=new Customer("flyingpig",100, LocalDate.now());
        animalShop.entertainingCustomer(customer);
        animalShop.exit();
    }
}
