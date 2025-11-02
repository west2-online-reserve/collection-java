package P;

public abstract class Animals {
    protected String name;
    protected int age;
    protected String gender;
    protected double monney;

    public Animals(String name, int age, String gender, double monney) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.monney = monney;
    }
    public double getMonney() {
        return monney;
    }
    public abstract String toString();
}
