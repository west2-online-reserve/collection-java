public class AnimalShopTest {
    public static void main(String[] args) {
        AnimalShop myShop = new MyAnimalShop(120, true);
        Cat huahua = new Cat("Huahua", 2, false, 150);
        ChinaDog haha = new ChinaDog("哈哈", 5, true, true, 80);
        Customer van = new Customer("van", 0);
        Customer billy = new Customer("billy", 0);
        Customer mike = new Customer("mike", 0);


        // 测试buyNewAnimal方法
        myShop.buyNewAnimal(haha);
        // 测试InsInsufficientBalanceException异常
        myShop.buyNewAnimal(huahua);


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        // 测试招待新顾客
        myShop.serveNewCustomer(van);
        // 测试同时招待同一名顾客
        myShop.serveNewCustomer(van);

        //测试售出动物
        myShop.sellAnimal(billy, haha);
        myShop.sellAnimal(billy, huahua);


        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //关店
        myShop.close();
        //在关店时出售动物会怎么样
        myShop.buyNewAnimal(huahua);
        //重新开业
        myShop.open();

        //测试是否开业成功
        myShop.buyNewAnimal(huahua);


    }
}
