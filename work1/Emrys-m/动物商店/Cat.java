package HomeWork;

public class Cat extends Animal{
    public Cat(String name,int age,String gender) {
        super(name,age,gender,200,160);
    }

    public Cat() {
        super();
    }

    @Override
    public String toString() {
        return "Cat's name:" + this.name + "\n" + "age:" + this.age + "\n" + "gender:" + this.gender + "\n" + "price:" + this.price;
    }
}
