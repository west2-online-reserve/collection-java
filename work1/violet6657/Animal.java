public  abstract class Animal {
    protected String name;
    protected int age;
    protected String gender;
    protected double price ;
    protected int weight;


    public Animal() {
    }

    public Animal(String name, int age, String gender, double price, int weight) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public abstract String toString();
}
