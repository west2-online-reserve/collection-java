public class AnimalShopTest {
    public static void main(String[] args) {
        Customer customerJoe = new Customer("Joe");
        Customer customerCandy = new Customer("Candy");
        Customer customerLucy = new Customer("Lucy");

        Animal animalCat = new Cat("Nancy", 2, false);
        Animal animalChineseRuralDog = new ChineseRuralDog("Kate", 5, false, true);
        Animal animalEmperorPenguin = new EmperorPenguin("Olive", 1, true);

        AnimalShop myAnimalShop = new MyAnimalShop(1000, true);

        try {
            myAnimalShop.purchaseAnimal(animalCat);
            myAnimalShop.purchaseAnimal(animalChineseRuralDog);
            myAnimalShop.purchaseAnimal(animalEmperorPenguin); //InsufficientBalanceException
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage()); //Output: Balance is insufficient to cover the cost!
            System.out.println("Balance: " + e.getBalance());
            System.out.println("Expected Cost :" + e.getExpectedCost());
        }

        try {
            myAnimalShop.receiveCustomer(customerJoe, animalCat);//Output: This is cat! Age: 2, Sex: false, Price: 200.0
            myAnimalShop.receiveCustomer(customerJoe, animalCat);//AnimalNotFoundException
        } catch (AnimalNotFoundException e) {
            System.out.println(e.getMessage()); //Output: Nancy could not be found!
        }

        try {
            myAnimalShop.receiveCustomer(customerLucy, animalEmperorPenguin); //AnimalNotFoundException
        } catch (AnimalNotFoundException e) {
            System.out.println(e.getMessage()); //Output: Olive could not be found!
        }

        myAnimalShop.close();
        //Output:
        //Name: Joe, Visit Times: 1, Latest Visit Date: 2023-10-08
        //Today's profit: -100.0
        myAnimalShop.receiveCustomer(customerCandy, animalChineseRuralDog); //Output: Shop is not open!
    }
}
