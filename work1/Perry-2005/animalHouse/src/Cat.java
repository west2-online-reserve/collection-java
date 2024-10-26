public class Cat extends Animal {
    public Cat() {
        super();
    }
    public Cat(String name,int age, String gender,double price) {
        super(name, age, gender, price);
    }

    @Override
    public String toString() {
        return  "姓名"+getName()+"   年龄"+getAge()+"  性别"+getGender()+"    价格"+getPrice();
    }
}
