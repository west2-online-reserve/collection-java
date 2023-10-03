import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        MyAnimalShop animalShop = new MyAnimalShop(1000.0,true);
        MyAnimalShop animalShop2 = new MyAnimalShop(1000.0,false);

        // 创建动物实例
        Cat cat1 = new Cat("kebi",15,"Male",true);
        Cat cat2 = new Cat("laoda",16,"Male",true);
        ChineseRuralDog dog = new ChineseRuralDog("Tommy", 2, "Male", true);
        ChineseRuralDog costlydog = new ChineseRuralDog("IKUN",19,"Male",true);

        Customer  p1 = new Customer("zhangsan", 4, LocalDate.now());
        Customer  p2 = new Customer("lisi",5,LocalDate.now());
        Customer  p3=new Customer("wangwu",1,LocalDate.now());

        animalShop.buyAnimal(cat1);
        animalShop.buyAnimal(dog);
        animalShop.serveCustomer(p1);
        animalShop.serveCustomer(p2);
        animalShop.serveCustomer(p3);
        animalShop.closeShop();

        animalShop2.buyAnimal(dog);
        animalShop2.serveCustomer(p1);//第二个店铺已经关门

    }
}