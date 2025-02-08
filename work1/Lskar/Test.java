import java.time.LocalDate;
public class Test {
    public static void main(String[] args) {
        //建立自己的宠物店，初始资金为1000
        AnimalShop myAnimalShop = new MyAnimalShop(1000);
        //初始化两位客人
        LocalDate localDate1 = LocalDate.parse("2018-10-01");
        LocalDate localDate2 = LocalDate.parse("2024-07-12");
        Customer customer1=new Customer("小明",5,localDate1);
        Customer customer2=new Customer("小红",2,localDate2);
        //初始化3个宠物
        Animal cat=new Cat("小花",1);
        Animal dog1=new ChineseRuralDog("小黑",2,100,false);
        Animal dog2=new ChineseRuralDog("小黄",2,200,true);
        //买入3个动物
        myAnimalShop.buyAnimal(cat);
        myAnimalShop.buyAnimal(dog1);
        myAnimalShop.buyAnimal(dog2);
        //招待客人
        myAnimalShop.treatCustomer(customer1);
        myAnimalShop.treatCustomer(customer2);
        myAnimalShop.close();
    }
}
