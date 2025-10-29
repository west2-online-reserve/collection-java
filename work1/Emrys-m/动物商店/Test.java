package HomeWork;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop myShop = new MyAnimalShop(300,true);
        ChineseRuralDog dog = new ChineseRuralDog("旺财",3,"公",true);
        Cat cat = new Cat("Tom",5,"Man");
        try {
            myShop.buyNewAnimal(dog);
            myShop.buyNewAnimal(cat);
        }catch (InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }
        System.out.println(myShop);

        Customer Jury = new Customer("Jury");
        Customer Hary = new Customer("Hary");
        try {
            myShop.serveGuests(Jury, new Cat());
            myShop.serveGuests(Hary,new ChineseRuralDog());
        }catch (AnimalNotFountException e){
            System.out.println(e.getMessage());
        }

        myShop.closeDown();
    }
}
