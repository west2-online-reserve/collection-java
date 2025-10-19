package petShop;

import petShop.animal.Bird;
import petShop.animal.Cat;
import petShop.animal.ChineseRuralDog;
import petShop.customer.Customer;
import petShop.shop.AnimalShop;
import petShop.shop.MyAnimalShop;

import java.util.Random;

public class Test {
    //鸟300买入，赚70
    //猫200买入，赚50
    //狗100买入，赚30
    public static void main(String[] args) {
        AnimalShop myShop=new MyAnimalShop(500);
        //测试余额不足异常
//        test1(myShop);
        //测试没找到动物异常
//        test2(myShop);
        //测试一天的正常营业
//        test3(myShop);
        //测试同天回头客
//        test4(myShop);
        //测试隔天回头客
        test5(myShop);
    }
    public static Cat generateCat(){
        return new Cat("cat"+System.currentTimeMillis(),1,true);
    }
    public static ChineseRuralDog  generateDog(){
        return new ChineseRuralDog("dog"+System.currentTimeMillis(),1,true);
    }
    public static Bird generateBird(){
        return new Bird("bird"+System.currentTimeMillis(),1,true);
    }
    public static void test1(AnimalShop myShop){
        myShop.buyAnimal(generateCat());
        myShop.buyAnimal(generateCat());
        myShop.buyAnimal(generateCat());
    }
    public static Customer generateCustomer(){
        return new Customer("customer"+System.currentTimeMillis());
    }
    public static void test2(AnimalShop myShop){
        myShop.entertainCustomer(generateCustomer(),0);
    }
    public static void test3(AnimalShop myShop){
        myShop.buyAnimal(generateCat());
        myShop.buyAnimal(generateBird());
        myShop.entertainCustomer(generateCustomer(),1);
        myShop.entertainCustomer(generateCustomer(),2);
        myShop.buyAnimal(generateDog());
        myShop.entertainCustomer(generateCustomer(),3);
        myShop.close();
    }
    public static void test4(AnimalShop myShop){
        myShop.buyAnimal(generateCat());
        myShop.buyAnimal(generateBird());
        myShop.entertainCustomer(generateCustomer(),1);
        Customer oldCustomer=generateCustomer();
        myShop.entertainCustomer(oldCustomer,2);
        myShop.buyAnimal(generateDog());
        myShop.entertainCustomer(oldCustomer,3);
        myShop.close();
    }
    public static void test5(AnimalShop myShop){
        myShop.buyAnimal(generateCat());
        myShop.buyAnimal(generateBird());
        myShop.entertainCustomer(generateCustomer(),1);
        Customer oldCustomer=generateCustomer();
        myShop.entertainCustomer(oldCustomer,2);
        myShop.buyAnimal(generateDog());
        myShop.entertainCustomer(oldCustomer,3);
        myShop.close();
        myShop.open();
        myShop.buyAnimal(generateDog());
        myShop.entertainCustomer(oldCustomer,4);
        myShop.close();
    }

}
