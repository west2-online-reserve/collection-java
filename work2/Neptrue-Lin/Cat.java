public class Cat extends Animal{

    public Cat(String name, int age, boolean sex) {
        //Cats are more expensive than dogs, for they are cuuuuuuuuuuuuter than dogs.
        //However, emperor penguin babies are cuuuuuuuuuuuuuuuuuuuuuuuuuuutest!
        super(name, age, sex, 200);
    }

    @Override
    public String toString() {
        return "This is cat! " +
                "Age: " + this.getAge() + ", " +
                "Sex: " + this.getSex() + ", " +
                "Price: " + this.getPrice();
    }
}
