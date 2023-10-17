public class EmperorPenguin extends Animal {

    public EmperorPenguin(String name, int age, boolean sex) {
        super(name, age, sex, 99999.9999);
    }

    @Override
    public String toString() {
        return "This is emperor peeeeeeeeeeeenguin! " +
                "Age: " + this.getAge() + ", " +
                "Sex: " + this.getSex() + ", " +
                "Price: " + this.getPrice();
    }
}
