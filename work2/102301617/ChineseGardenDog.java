public class ChineseGardenDog extends Animal {
    Boolean isVaccinated;


    public ChineseGardenDog(String name, int age, double price,String sex) {
        super(name, age, 100,sex,50);

    }

    @Override
    public String toString() {
        return "name"+name+
                "age"+age+
                "price"+price+
                "sex"+sex;
    }
}
