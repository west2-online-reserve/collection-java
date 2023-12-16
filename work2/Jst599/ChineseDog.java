package PetShop;

/**
 * Chinese_dog表示宠物店的中华田园犬
 *
 * 该类包含中华田园犬的是否注射狂犬疫苗的变量和价格
 * @author Jst599
 * @date 2023/10/17
 */
public class ChineseDog extends PetShop.Animal {
    private boolean isVaccineInjected;

    public ChineseDog() {
    }

    public boolean getisVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    public ChineseDog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, 100, 25);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "ChineseDog{" + "name = " + name + ";"+
                "age = " + age + ";" +
                "gender = " + gender + ";" +
                "price = " + price + ";" +
                "isVaccineInjected = " + isVaccineInjected + ";" +
                "}";
    }
}
