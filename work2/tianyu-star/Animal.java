
public abstract class Animal{
    protected String name;
    protected int age;
    protected String sex;
    protected double price;
    protected String species;
    public Animal(String name,int age,String sex,double price,String species){
        this.name=name;
        this.age=age;
        this.sex=sex;
        this.price=price;
        this.species=species;
    }
    public abstract String toString();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }
}

