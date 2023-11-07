package work2;


/**
 * @author Mia
 * @date 2023/11/1
 */
public class Test {
    public static void main(String[] args) {
        MyAnimalShop petShop = new MyAnimalShop(1000);
        //初始化动物列表
        Dogs d1 = new Dogs("小白", 1, "雄性", true);
        Cats c1 = new Cats("小花", 2, "雌性");
        petShop.getAnimalList().add(d1);
        petShop.getAnimalList().add(c1);

        Dogs d2 = new Dogs("小黄", 1, "雌性", false);
        Dogs d3 = new Dogs("耶耶", 1, "雄性", true);
        Rabbit r1 = new Rabbit("小粉", 1, "雄性");
        Cats c2 = new Cats("斑斑", 3, "雌性");
        //买入测试
        try {
            petShop.buy(d2, 80);
            petShop.buy(r1, 90);
            //余额不足
            petShop.buy(d3, 8000);

        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
        Customer customer1 = new Customer("小王");
        Customer customer2 = new Customer("小李");
        Customer customer3 = new Customer("小胡");

        //招待顾客
        //店内没有该动物
        try {
            petShop.treat(customer1, "小黄");
            petShop.treat(customer1, "小花");
            petShop.treat(customer2, "小白");
            petShop.treat(customer1, "小黄");

        } catch (AnimalNotFountException e) {
            System.out.println(e.getMessage());
        }
        try {
            petShop.treat(customer3, "福福");

        } catch (AnimalNotFountException e) {
            System.out.println(e.getMessage());
        }
        //店内无动物可售
        try {
            petShop.treat(customer1, "小粉");
            petShop.treat(customer2, "小黄");
        } catch (AnimalNotFountException e) {
            System.out.println(e.getMessage());
        }
        petShop.buy(c2, 50);
        //歇业
        petShop.close();
        //买入测试——歇业
        petShop.buy(r1, 100);
        //招待顾客——歇业
        petShop.treat(customer2, "小粉");
        petShop.treat(customer3, "小黄");
        petShop.close();


    }
}
