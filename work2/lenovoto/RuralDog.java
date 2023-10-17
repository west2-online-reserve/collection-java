package homework.work2;

public class RuralDog extends Animal{
    boolean isVaccineInjected;
    double petImport=50;
    public RuralDog(String petName, int petAge, String petSex, double petPrice, boolean isVaccineInjected, double petImport) {
        super(petName, petAge, petSex, petPrice,petImport);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "RuralDog{" +
                "isVaccineInjected=" + isVaccineInjected +
                ", petName='" + petName + '\'' +
                ", petAge=" + petAge +
                ", petSex='" + petSex + '\'' +
                ", petPrice=" + petPrice +
                '}';
    }
}
