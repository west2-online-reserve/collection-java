public class Text {
    public static void main(String[] args) {
        MyAnimalShop myAnimalShop = new MyAnimalShop();
        Animal dog = new Dog("小黄", 3, true, true);
        Animal cat = new Cat("小猫", 2, false);
        Animal dog02 = new Dog("狗蛋", 4, true, false);
        Customer customer1 = new Customer("张三");
        Customer customer2 = new Customer("李四");
        Customer customer3 = new Customer("王五");

        // 一开始有300的资金
        myAnimalShop.setBalance(300);
        myAnimalShop.setOpen(true);


        try {
            myAnimalShop.newAnimal(dog);
            myAnimalShop.newAnimal(cat);
            myAnimalShop.newAnimal(dog02);// InsufficientBalanceException
        } catch (InsufficientBalanceException i) {
            i.printDetailedError();
        }
        System.out.println("================================================");

        try {
            myAnimalShop.reciveCustomer(customer1, dog);
            myAnimalShop.reciveCustomer(customer2, cat);
            myAnimalShop.reciveCustomer(customer3, dog02);// AnimalNotFountException
        } catch (AnimalNotFountException e) {
            e.printDetailedError();
        }
        System.out.println("===================================================");
        myAnimalShop.Closed();

    }
}