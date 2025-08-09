package HomeWork;

public class Bird extends Animal {
    public Bird(String name,int age,String gender) {
        super(name,age,gender,300,240);
    }

    public Bird(){};

    @Override
    public String toString() {
        return "Bird's name:" + this.name + "\n" + "age:" + this.age + "\n" + "gender:" + this.gender + "\n" + "price:" + this.price;
    }
}
