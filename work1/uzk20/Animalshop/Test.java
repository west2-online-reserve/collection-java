package work1.Animalshop;

import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {

        MyAnimalShop animalShop=new MyAnimalShop(1000,true);
        Dog Bruce=new Dog("Bruce",3,"male",true);
        Dog Cat=new Dog("Cat",5,"male",false);
        Cat Hachimi=new Cat("Hachimi",2,"female");
        Cat Dog=new Cat("Dog",1,"male");
        Brid Helicopter=new Brid("Helicopter",4,"female");
        Brid GarlicSprout=new Brid("GarlicSprout",2,"male");
        Brid phoenix=new Brid("phoenix",120,"male");
        Cat Yellow=new Cat("Yellow",1,"male");

        Customer John=new Customer("John",2,LocalDate.of(2024,10,4));
        Customer Coke=new Customer("Coke",0,LocalDate.now());
        Customer Kobe=new Customer("Kobe",1,LocalDate.of(2020,1,26));
        Customer Hawking=new Customer("Hawking",4,LocalDate.of(2018,3,14));

        //此处为开业第一天店内有的动物，购买动物这一流程其实是在每一天的末尾开始的
        try{
            animalShop.buyAnimal(Bruce);
            animalShop.buyAnimal(Cat);
            animalShop.buyAnimal(Hachimi);
            animalShop.buyAnimal(Dog);
            animalShop.buyAnimal(Helicopter);
            animalShop.buyAnimal(GarlicSprout);
        }catch (InsufficientBalanceException ex){
            System.out.println(ex.getMessage());
            System.out.println();
        }
        //Day 0
        animalShop.isOperateNormally();

        try {
            animalShop.serveCustomer(John,Bruce);
            animalShop.serveCustomer(John,Hachimi);
            animalShop.serveCustomer(Coke,Dog);
            animalShop.serveCustomer(Kobe,Helicopter);
            animalShop.serveCustomer(Kobe,Cat);
            animalShop.serveCustomer(John,GarlicSprout);
            animalShop.serveCustomer(John,phoenix);
        }catch(IllegalStateException ex){
            System.out.println(ex.getMessage());
            System.out.println();
        }catch(AnimalNotFoundException ex){
            System.out.println(ex.getMessage());
            System.out.println();
        }


        animalShop.addOldCustomer(Hawking);
        animalShop.closeShop();

        //Day 1（测试动物为空时能不能服务顾客）

        animalShop.isOperateNormally();
        try{
            animalShop.serveCustomer(John,GarlicSprout);
        }catch(IllegalStateException ex){
            System.out.println(ex.getMessage());
            System.out.println();
        }catch(AnimalNotFoundException ex){
            System.out.println(ex.getMessage());
            System.out.println();
        }

        try{
            animalShop.buyAnimal(GarlicSprout);
            animalShop.buyAnimal(Yellow);
        }catch(AnimalNotFoundException ex){
            System.out.println(ex.getMessage());
            System.out.println();
        }

        animalShop.closeShop();

        //Day 2
        animalShop.isOperateNormally();

        try{
            animalShop.serveCustomer(John,GarlicSprout);
            animalShop.serveCustomer(Hawking,Yellow);
        }catch(IllegalStateException ex){
            System.out.println(ex.getMessage());
            System.out.println();
        }catch(AnimalNotFoundException ex){
            System.out.println(ex.getMessage());
            System.out.println();
        }

        try{
            animalShop.buyAnimal(phoenix);
        }catch(AnimalNotFoundException ex){
            System.out.println(ex.getMessage());
            System.out.println();
        }

        animalShop.closeShop();
    }
}
