public class Husky extends Animal{
    public Husky(){

    }

    public Husky(String name, int age, String gender,double price) {
        super(name, age, gender,300);
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
