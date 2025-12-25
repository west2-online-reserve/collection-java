
public abstract class  Animal {
    protected String name;
    protected int age;
    protected String gender;
    protected double cost;//成本
    protected double price;//售价


    public Animal(String name, double cost, String gender, int age) {
        this.name = name;
        this.cost = cost;
        this.gender = gender;
        this.age = age;
    }

    public Animal(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract String toString();

}
