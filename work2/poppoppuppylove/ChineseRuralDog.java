// 中华田园犬类
public class ChineseRuralDog extends Animal {

    private boolean isVaccinated;

    public ChineseRuralDog(String name, int age, String gender, boolean isVaccinated) {
        super(name, age, gender, 100.0);
        this.setVaccinated(isVaccinated);
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    @Override
    public String toString() {
        return "Woof woof！" +
                "age=" + this.getAge() + '，' +
                "gender='" + this.getGender() + '，' +
                "Is Vaccinated: " + this.isVaccinated() + '，' +
                "price=" + this.getPrice();
    }


}