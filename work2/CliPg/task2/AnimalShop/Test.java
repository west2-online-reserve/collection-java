package com.PeanutJava.task2;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop CliPg = new MyAnimalShop(1000,true);
        ChineseRuralDog OriginDog1 = new ChineseRuralDog("KevinDurant",7,"male",true);
        Cat OriginCat1 = new Cat("DevinBooker",8,"male");
        CliPg.getAnimalList().add(OriginDog1);
        CliPg.getAnimalList().add(OriginCat1);
        ChineseRuralDog dog1 = new ChineseRuralDog(null,2,"male",true);
        ChineseRuralDog dog2 = new ChineseRuralDog(null,3,"female",false);
        ChineseRuralDog dog3 = new ChineseRuralDog();
        Cat cat1 = new Cat(null,3,"male");
        Cat cat2 = new Cat(null,4,"female");
        Customer c1 = new Customer("PaulGeorge",0);
        Customer c2 = new Customer("StepenCurry",1);

        CliPg.Buy(dog1,1);
        CliPg.Buy(dog2,1);
        CliPg.Buy(cat1,0);
        CliPg.Buy(cat2,0);

        CliPg.Serve(c1);
        CliPg.Serve(c2);
        CliPg.isClosed();

        CliPg.Serve(c1);

    }
}
