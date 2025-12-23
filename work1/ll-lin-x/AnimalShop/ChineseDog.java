package west2.task1.AnimalShop;

public class ChineseDog extends Animal{
    boolean isVaccineInjected;

    ChineseDog(String name, int age, int gender, double price) {
        super(name, age, gender, price);
    }


    @Override
    public String toString() {
        return "类型：ChineseDog，名字：" + super.getName();
    }
}
