public class Cat extends Animal {

    public Cat(String name, int age) {
        super(name, age, 200);
    }
    @Override
    public String toString() {
        return "Cat{"+
                "name='"+name+'\''+
                ",age='"+age+'\''+
                ",price='"+price+'\'';
    }
}
