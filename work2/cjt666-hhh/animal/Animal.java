package animal;

public  abstract class  Animal {



    int age;
    String name;
    boolean isMale;
    double cost;

    public Animal(int age, String name, boolean isMale, double cost) {
        this.age = age;
        this.name = name;
        this.isMale = isMale;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", isMale=" + isMale +
                ", cost=" + cost +
                '}';
    }
}