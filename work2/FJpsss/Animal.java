public abstract class Animal {
    protected String animalName;
    protected int animalAge;
    protected String animalSex;
    protected double animalPrice;


    public Animal() {
    }

    ;

    public Animal(String animalName, int animalAge, String animalSex, double animalPrice) {

        this.animalAge = animalAge;
        this.animalName = animalName;
        this.animalPrice = animalPrice;
        this.animalSex = animalSex;

    }

    public double getAnimalPrice() {
        return animalPrice;
    }

    public abstract String toString();
}
