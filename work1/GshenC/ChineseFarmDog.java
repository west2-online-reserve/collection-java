public class ChineseFarmDog extends Animal {
    private boolean hasRabiesVaccine;

    public ChineseFarmDog(String name, int age, String gender, boolean hasRabiesVaccine) {
        super(name, age, gender);
        this.hasRabiesVaccine = hasRabiesVaccine;
    }

    @Override
    public String toString() {
        return "ChineseFarmDog{" +
               "name='" + getName() + '\'' +
               ", age=" + getAge() +
               ", gender='" + getGender() + '\'' +
               ", hasRabiesVaccine=" + hasRabiesVaccine +
               '}';
    }
}
