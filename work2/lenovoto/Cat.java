package homework.work2;

public class Cat extends Animal{
    double petImport=60;
    public Cat(String petName, int petAge, String petSex, double petPrice,double petImport) {
        super(petName, petAge, petSex, petPrice,petImport);

        this.petPrice=200;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "petName='" + petName + '\'' +
                ", petAge=" + petAge +
                ", petSex='" + petSex +'\''+
                ", petPrice=" + petPrice +
                '}';
    }
}
