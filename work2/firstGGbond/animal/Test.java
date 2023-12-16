package animal;
/**
 * @author MR.瑜
 */
public class Test {
    public static void main(String[] args) {
        Customer customer1 = new Customer("张三");
        Customer customer2 = new Customer("李四");
        Customer customer3 = new Customer("王五");

        Animal animalCat = new Cat("旺财", 2,"male",300,"yellow");
        Animal animalChineseRuralDog = new ChineseRuralDog("来福", 5, "female", 150,true);
        AnimalShop myAnimalShop = new MyAnimalShop(500,false);

        try {
            myAnimalShop.purchaseAnimal(animalCat);
            myAnimalShop.purchaseAnimal(animalChineseRuralDog);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
            System.out.println("Balance: " + e.getBalance());
            System.out.println("Expected Cost :" + e.getExpectedCost());
        }

        try {
            myAnimalShop.receiveCustomer(customer1, animalCat);
            myAnimalShop.receiveCustomer(customer1, animalChineseRuralDog);
        } catch (AnimalNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            myAnimalShop.receiveCustomer(customer1, animalCat);
        } catch (AnimalNotFoundException e) {
            System.out.println(e.getMessage());
        }

        myAnimalShop.close();
        myAnimalShop.receiveCustomer(customer3, animalChineseRuralDog);
    }
}
