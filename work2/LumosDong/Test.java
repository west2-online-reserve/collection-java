package com.dong.westtwowork;

public class Test {
    public static void main(String[] args) {
        Customer customer1 = new Customer("kunkun",2);
        Customer customer2 = new Customer("xiaohao",3);

        MyAnimalShop shop1 = new MyAnimalShop(500,false);

        shop1.purchaseAnimal(new Cat ("miaomiao",4,"boy",200));
        shop1.purchaseAnimal(new ChineseRuralDog("wangcai",4,"girl",true));
        shop1.purchaseAnimal(new Parrot("boli",5,"boy",10000));
        shop1.treatCustomers(customer1);
        shop1.treatCustomers(customer2);
        shop1.closureOfBusiness();

        MyAnimalShop shop2 = new MyAnimalShop(1000,true);
        shop2.purchaseAnimal(new Parrot("nana",2,"girl",10000));
        shop2.purchaseAnimal(new ChineseRuralDog("maomao",6,"girl",false));
        shop2.purchaseAnimal(new Cat("xiaohei",1,"girl",200));
        shop1.treatCustomers(customer1);
        shop1.treatCustomers(customer2);
        shop1.closureOfBusiness();
    }
}
