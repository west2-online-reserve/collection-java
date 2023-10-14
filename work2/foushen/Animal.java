public abstract class Animal {

    protected String name;
    protected int age;
    protected boolean sex;//规定雄性为true
    protected double price;//售价
    protected double restorePrice;//进价

    public Animal(String name, int age, boolean sex, double price,double restorePrice) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
        this.restorePrice=restorePrice;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean getSex() {
        return sex;
    }
    public double getRestorePrice(){
        return  restorePrice;
    }

    abstract public String toString();
}