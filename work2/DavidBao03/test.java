public class test {
    public static void main(String[] args) {
        MyAnimalShop west2 = new MyAnimalShop();
        Customer Jordan = new Customer("Jordan", 3);
        Customer John = new Customer("John", 4);
        Cat catOne = new Cat("CatOne", 1, "female", true);
        Cat catTwo = new Cat("CatTwo", 2, "male", false);
        ChineseFieldDog dogOne = new ChineseFieldDog("DogOne", 3, "male", true);
        ChineseFieldDog dogTwo = new ChineseFieldDog("DogTwo", 4, "female", true);
        west2.buy(catOne);
        west2.serve(Jordan);
        west2.serve(John);
        west2.close();
    }
}
