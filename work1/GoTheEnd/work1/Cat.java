public class Cat extends Animal {
    public Cat(String name, int age, String gender, double price, double cost, String temperament) {
        super(name, age, gender, price, cost, temperament);
    }

    @Override
    public String toString(){
        return "Cat" + "\n" +
                "-----------\n" +
                "name: " + name + "\n" +
                "age: " + age + "\n" +
                "gender: " + gender + "\n" +
                "price: " + price + "\n" +
                "temperament: " + temperament;
    }

    @Override
    public void feed(){
        System.out.println("【你喂食了Cat " + this.name + "】");
        System.out.println("\"mew!!\"\n");
    }
}
