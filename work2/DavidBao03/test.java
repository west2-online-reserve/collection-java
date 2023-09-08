public class test {
    public static void main(String[] args) {
        MyAnimalShop west2 = new MyAnimalShop(1000, false);
        MyAnimalShop west1 = new MyAnimalShop(1000, true);
        Customer Jordan = new Customer("Jordan", 3);
        Customer John = new Customer("John", 4);
        Cat catOne = new Cat("CatOne", 1, "female", true);
        Cat catTwo = new Cat("CatTwo", 2, "male", false);
        ChineseFieldDog dogOne = new ChineseFieldDog("DogOne", 3, "male", true);
        ChineseFieldDog dogTwo = new ChineseFieldDog("DogTwo", 4, "female", true);
        west2.buy(catOne);
        west2.serve(Jordan);
        west2.serve(John);
        west1.serve(John);
        west1.buy(catOne);
        west1.close();
        west2.close();
    }
}
