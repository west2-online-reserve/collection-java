package Eyrine;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop shop=new MyAnimalShop(500,true);
        Animal cat=new Cat("小猫",8,"male",200);
        shop.getList().add(cat);

        Animal dog =new Dog("中华田园犬",11,"male",100,false);
        shop.getList().add(dog);
        Animal rabbit=new Rabbit("兔子",6,"female",160);
        shop.buyAnimal(rabbit);
        Animal tiger=new Tiger("老虎",7,"female",300);
        shop.buyAnimal(tiger);
        Customer write=new Customer("write",0, LocalDate.now());
        shop.treat(write);
        shop.isClosed();
    }




}
