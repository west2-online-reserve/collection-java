/**
 * @author 102301617
 */

public class Jerry extends Animal {
    public Jerry(String name, int age, double price, String sex) {
        super(name, age, 1000, sex,500,isVaccinated,true);
    }

    @Override
    public String toString() {
        return "name"+name +
                "age" +age +
                "price"+price +
                "sex"+ sex;
    }
}
