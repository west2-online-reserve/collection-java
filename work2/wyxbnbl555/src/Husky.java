public class Husky extends Animal{
    public double price = 300;
    public Husky() {
    }

    public Husky(double price){
        super(price);
    }

    public Husky(String name, int age, String gender) {
        super(name, age, gender);
        price = this.getPrice();
    }

    @Override
    public String toString() {
        return "Husky{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender=" + getGender() +
                ",price=" + getPrice() +
                '}';
    }

    public void makeTrouble() {
        System.out.println("哈士奇在捣乱");
    }
}
