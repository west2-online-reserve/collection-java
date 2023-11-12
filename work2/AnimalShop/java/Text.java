package collection

/**
 * @author xzqsxy
 * @date 2023-11-05
 */
public class Text {
    public static void main(String[] args){
        MyAnimalShop shop1=new MyAnimalShop(1000,false);
        MyAnimalShop shop2=new MyAnimalShop(1000,true);
        Tiger tiger=new Tiger("xuan",2,'m',1);
        Cat cat=new Cat("dun",1,'w',1);
        ChineseFieldDog chineseFieldDog=new ChineseFieldDog("qzxx",2,'m',true);
        Customer customer1=new Customer("xzq",0);
        Customer customer2=new Customer("qzx",0);
        shop1.buyAnimal(cat);
        shop1.buyAnimal(cat);
        shop1.buyAnimal(tiger);
        shop1.buyAnimal(tiger);//余额不足,抛出异常
        shop1.inCUstomer(customer1);
        shop1.inCUstomer(customer2);
        shop1.Closed();//输出名单
        shop2.inCUstomer(customer1);
        shop2.Closed();


    }
}
