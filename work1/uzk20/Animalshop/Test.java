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

        Customer John=new Customer("John",2,LocalDate.of(2024,10,4));
        Customer Coke=new Customer("Coke",0,LocalDate.now());
        Customer Kobe=new Customer("Kobe",1,LocalDate.of(2020,1,26));
        Customer Hawking=new Customer("Hawking",4,LocalDate.of(2018,3,14));

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

        animalShop.isOperateNormally();

        try {
            animalShop.serveCustomer(John,Bruce);
            animalShop.serveCustomer(John,Hachimi);
            animalShop.serveCustomer(Coke,Dog);
            animalShop.serveCustomer(Kobe,Helicopter);
            animalShop.serveCustomer(Kobe,Cat);
            animalShop.serveCustomer(John,phoenix);
        }catch(AnimalNotFoundException ex){
            System.out.println(ex.getMessage());
            System.out.println();
        }

        animalShop.addOldCustomer(Hawking);
        animalShop.closeShop();
    }
}
