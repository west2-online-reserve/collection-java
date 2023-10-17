package XWBN2;

public class AnimalNotFountException extends RuntimeException {
    String animalName;

    AnimalNotFountException(String aniName) {
        this.animalName = animalName;
    }

    public String toString() {
        return "宠物店无客户想要购买的宠物" + animalName + '\n';
    }
}
