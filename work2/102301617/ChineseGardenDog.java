/***
 * @author 102301617
 *
  */


public class ChineseGardenDog extends Animal {
    private Boolean isVaccinated;

    public Boolean getVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(Boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public ChineseGardenDog(String name, int age, double price, String sex, Boolean isVaccinated) {
        super(name, age, 100, sex, 50);
         this.isVaccinated=isVaccinated;
    }

    @Override
    public String toString() {
        return "name" + name +
                "age" + age +
                "price" + price +
                "sex" + sex;
    }
}
