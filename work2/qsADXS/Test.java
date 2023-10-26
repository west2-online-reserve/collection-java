import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //测试中华田园犬类
        ChineseRuralDog aDog = new ChineseRuralDog("中华",2,2,false);
        System.out.println(aDog.toString());
        aDog.vaccinate();;
        System.out.println(aDog.toString());
        aDog.vaccinate();
        System.out.println("=====================================");//华丽的分割线
        //测试猫类
        Cat aCat = new Cat("喵喵",1,1);
        System.out.println(aCat.toString());
        aCat.mouseWreckers();
        System.out.println("=====================================");//华丽的分割线
        //测试鸡类
        Chook aChook = new Chook("cxk",25,1,false);
        System.out.println(aChook.toString());
        aChook.dancing();
        aChook.teachDance();
        aChook.teachDance();
        aChook.dancing();
        System.out.println(aChook.toString());
        System.out.println("=====================================");//华丽的分割线
        //测试顾客类
        Customer aCustomer = new Customer("不知道取啥名字");
        System.out.println(aCustomer.toString());
        aCustomer.arrive();
        System.out.println(aCustomer.toString());
        System.out.println("=====================================");//华丽的分割线
        //测试宠物店类
        AnimalShopImpl MyAnimalsShop = new AnimalShopImpl(100);
        MyAnimalsShop.entertainCustomer();
        MyAnimalsShop.open();
        MyAnimalsShop.entertainCustomer();//抛出异常类AnimalNotFountException
        MyAnimalsShop.buyNewAnimals(aDog,2);//抛出异常类InsufficientBalanceException
        MyAnimalsShop.buyNewAnimals(aChook,5);
        MyAnimalsShop.entertainCustomer();
        MyAnimalsShop.open();
        MyAnimalsShop.entertainCustomer();
        MyAnimalsShop.entertainCustomer();
        MyAnimalsShop.entertainCustomer();
        MyAnimalsShop.close();

    }
}