package chongwudian;

public abstract class Animal {
   protected String name;
    protected int age;
    protected String gender;
    protected Double price;
    protected int kind;
    public Animal(){

    }

    public Animal(String name, int age, String gender, Double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getKind() {
        return kind;
    }

    public abstract String toString();
}
