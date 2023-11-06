package animal;

/**
 * @author MR.瑜
 */
public class AnimalNotFoundException extends RuntimeException {
    private Animal animal;
    public AnimalNotFoundException(Animal animal) {
        super(animal.name + " cannot be found.");
        this.setAnimal(animal);
    }
    protected void setAnimal(Animal animal) throws NullPointerException {
        if (animal == null) {
            throw new NullPointerException();
        }
        this.animal = animal;
    }
}
