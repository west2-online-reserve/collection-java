/***
 * @author 102301617
 *
 *
 * */


public class Cat extends Animal{
    public Cat(String name, int age, double price, String sex) {
        super(name, age, 200,sex,100);
    }

    @Override
    public String toString() {
        return "name"+name+
                "age"+age+
                "price"+ price+
                "sex"+sex;

    }
}

