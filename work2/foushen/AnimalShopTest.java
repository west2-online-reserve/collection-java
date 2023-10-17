public class AnimalShopTest {
    public static void main(String[] args) {
        AnimalShop myShop = new MyAnimalShop(120, true);
        Cat huahua = new Cat("Huahua", 2, false, 150);
        ChinaDog haha = new ChinaDog("哈哈", 5, true, true, 80);
        Customer van = new Customer("van", 0);
        Customer billy = new Customer("billy", 0);
        Customer mike = new Customer("mike", 0);

        try {
            myShop.buyNewAnimal(haha);
            myShop.buyNewAnimal(huahua);

        } catch (InsufficientBalanceException | AnimalShopIsClosed insufficientBalanceExceptionOrAnimalShopIsClosed) {
            System.out.println(insufficientBalanceExceptionOrAnimalShopIsClosed);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        try {
            myShop.serveNewCustomer(van);
            myShop.sellAnimal(billy, haha);
            myShop.sellAnimal(billy, huahua);
        } catch (AnimalNotFountException | AnimalShopIsClosed animalNotFountExceptionOrAnimalShopIsClosed) {
            System.out.println(animalNotFountExceptionOrAnimalShopIsClosed);
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        myShop.close();
        try {
            myShop.buyNewAnimal(huahua);
        } catch (AnimalShopIsClosed animalShopIsClosed) {
            System.out.println(animalShopIsClosed);
        }
        myShop.open();


    }
}
