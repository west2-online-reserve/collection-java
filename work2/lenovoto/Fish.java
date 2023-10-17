package homework.work2;

public class Fish extends Animal{
    double petImport=70;
    @Override
    public String toString() {
        return "Fish{" +
                "petName='" + petName + '\'' +
                ", petAge=" + petAge +
                ", petSex='" + petSex + '\'' +
                ", petPrice=" + petPrice +
                '}';
    }

    public Fish(String petName, int petAge, String petSex, double petPrice,double petImport) {
        super(petName, petAge, petSex, petPrice,petImport);
        this.petPrice=500;




    }
}
