public abstract class Animal {
    protected String animalName;
    protected int age;
    protected String sex;
    protected double price;

    public Animal(String animalName, int age, String sex, double price) {
        this.animalName = animalName;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public abstract String toString();
}
