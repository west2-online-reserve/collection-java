public class Pig extends Animal {
    private String Color; 

    public Pig(String name, int age, String gender, double price, String Color) {
        super(name, age, gender, price);
        this.Color = Color;
    }

    @Override
    public String toString() {
        return "猪：[" +
                "姓名='" + getName() + '\'' +
                ", 年龄=" + getAge() +
                ", 性别='" + getGender() + '\'' +
                ", 价格=" + getPrice() + "元" +
                ", 羽毛颜色='" + Color + '\'' +
                ']';
    }

    public String getColor() { return Color; }
    public void setColor(String Color) { this.Color = Color; }
}
