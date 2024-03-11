public abstract class Animal {
    protected String animalName;
    protected int age;
    protected String gender;
    protected double price;

    public Animal(String animalName, int age, String gender, double price) {
        this.animalName = animalName;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    public abstract String toString();

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
