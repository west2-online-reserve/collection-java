public class AnimalNotFoundException extends RuntimeException {
    protected Animal animal;

    public AnimalNotFoundException(Animal animal) {
        super(animal.name + " could not be found!");
        this.setAnimal(animal);
    }

    public Animal getAnimal() {
        return this.animal;
    }

    protected void setAnimal(Animal animal) throws NullPointerException{
        if (animal == null) {
            throw new NullPointerException();
        }
        this.animal = animal;
    }

}
