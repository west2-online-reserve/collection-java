package chongwudian;

public class Bird extends Animal {
    private String color;

    public Bird(String color) {
        this.color = color;
    }

    public Bird(String name, int age, String gender, Double price, String color) {
        super(name, age, gender, price);
        this.color = color;
        this.kind=3;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "鸟鸟"+ " name:"+getName()+" price:"+getPrice()+" color:"+getColor()+" gender:"+getGender()+" age:"+getAge();
    }
}
