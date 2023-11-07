public abstract class Animal {
    private String animalName;
    private int animalAge;
    private String animalSex;
    private double animalPrice;


    public Animal() {
    }

    public Animal(String animalName, int animalAge, String animalSex, double animalPrice) {

        this.animalAge = animalAge;
        this.animalName = animalName;
        this.animalPrice = animalPrice;
        this.animalSex = animalSex;

    }

    public double getAnimalPrice() {
        return animalPrice;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalSex() {
        return animalSex;
    }

    public int getAnimalAge() {
        return animalAge;
    }

    public abstract String toString();
}
