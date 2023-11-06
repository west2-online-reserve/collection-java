public class GuineaPig extends Animal {

    public GuineaPig(String name, int age, String gender) {
        super(name, age, gender, 320.0);
    }

    @Override
    public String toString() {
        return "Aww awww！" +
                "name='" + this.getName() + '，' +
                "age=" + this.getAge() + '，' +
                "gender='" + this.getGender() + '，' +
                "price=" + this.getPrice();
    }
}