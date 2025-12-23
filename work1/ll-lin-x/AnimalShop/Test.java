package west2.task1.AnimalShop;


public class Test {
    public static void main(String[] args) {
        AnimalShop myAnimalShop = new MyAnimalShop(1000.00,true);
        Animal ADog = new ChineseDog("ADog",4,1,300.10);
        Animal ACat = new Cat("ACat",2,0,100.50);
        myAnimalShop.buyNewAnimal(ADog);
        myAnimalShop.buyNewAnimal(ACat);
        Custormer custormer = new Custormer("Tom");
        myAnimalShop.entertainCustomer(custormer,ADog);
        myAnimalShop.closed();
    }
}
