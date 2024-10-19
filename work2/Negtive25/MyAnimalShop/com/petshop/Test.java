package com.petshop;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop myAnimalShop = new MyAnimalShop(300);
        Animal dog1 = new Dog("Rufus",3,"male",100,10,true);
        Animal cat1 = new Cat("Fluffy",2,"female",200,5);
        Animal sheep1= new Sheep("Bessie",4,"female",200,20);
        Animal sheep2= new Sheep("Homie",2,"male",200,25);

        System.out.println();

        myAnimalShop.purchaseAnimal(dog1,50);
        myAnimalShop.purchaseAnimal(cat1,100);
        myAnimalShop.purchaseAnimal(sheep1,150);
        
        //下一行代码会抛出InsufficientBalanceException，因为购买的余额不足
        //myAnimalShop.purchaseAnimal(sheep2,150);

        myAnimalShop.showAnimalList();

        System.out.println();

        myAnimalShop.openShop();
        myAnimalShop.whetherIsOpen();

        Customer customer1 = new Customer("John",5,"08-12");
        Customer customer2 = new Customer("Jane",4,"09-11");
        Customer customer3 = new Customer("Bob",3,"12-12");
        myAnimalShop.entertainCustomer(customer1);
        myAnimalShop.entertainCustomer(customer2);
        myAnimalShop.entertainCustomer(customer3);

        myAnimalShop.sellAnimal("Dog");
        myAnimalShop.sellAnimal("Cat");
        myAnimalShop.sellAnimal("Sheep");

        //下一行代码会抛出AnimalNotFountException，因为宠物店中没有该种类的动物
        //myAnimalShop.sellAnimal("Frog");
        //下一行代码会抛出AnimalNotFountException，因为已经没有可售的动物了
        //myAnimalShop.sellAnimal("Sheep");

        myAnimalShop.showAnimalList();

        System.out.println();

        myAnimalShop.closeShop();
        myAnimalShop.whetherIsOpen();
    }
}
