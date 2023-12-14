public class Test {
    public static void main(String[] args) {
    MyAnimalShop shop1=new MyAnimalShop(1000,false);
    MyAnimalShop shop2=new MyAnimalShop(100,false);
    Customer Steve =new Customer("Steve",1);
    Customer Alex =new Customer("Alex",5);
    Customer Greg=new Customer("Greg",2);
    Cat cat1=new Cat("cat1",2,200,"male");
    Cat cat2=new Cat("cat2",1,200,"female");
    ChineseDog dog1=new ChineseDog("dog1",2,100,"male",true);
    ChineseDog dog2=new ChineseDog("dog2",3,100,"female",true);
    shop1.buyNewAnimal(dog1);
    shop1.buyNewAnimal(cat2);
    shop2.buyNewAnimal(cat1);
    shop2.buyNewAnimal(dog2);
    shop1.serve(Alex);
    shop2.serve(Steve);
    shop2.serve(Greg);
    shop1.close();
    shop2.close();
    shop2.serve(Alex);
    }
}
