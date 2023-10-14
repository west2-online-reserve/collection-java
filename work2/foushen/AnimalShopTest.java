public class AnimalShopTest {
    public static void main(String[] args) {
        MyAnimalShop shop = new MyAnimalShop(100);
        shop.closeShop();
        shop.openShop();
        Animal cat = new Cat("泡泡", 2, false, 150);
        Animal chinaDog = new ChinaDog("小黑", 1, true, true, 80);
        Customer Lily = new Customer("李丽", 10, false);
        shop.addCustomer(Lily);
        //测试余额不足异常
        try {
            shop.buyNewAnimal(cat);
        } catch (InsufficientBalanceException insufficientBalanceException) {
            double restMoney = insufficientBalanceException.getRestMoney();
            System.out.println(insufficientBalanceException.getMessage() + restMoney);
        }
        //测试成功购买动物
        shop.buyNewAnimal(chinaDog);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //测试找不到动物异常
        Customer Mike = new Customer("Mike", 24, true);
        try {
            shop.soldAnimal(cat, Mike);

        } catch (AnimalNotFountException animalNotFountException) {
            System.out.println(animalNotFountException.getMessage() + animalNotFountException.getAnimal());
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //测试成功售出动物
        shop.soldAnimal(chinaDog, Mike);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        //测试关店并输出利润
        shop.closeShop();


    }
}
