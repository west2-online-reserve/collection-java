package westwork2;

public class AnimalNotFoundException extends RuntimeException {
    private Animal animal;
    public AnimalNotFoundException(Animal animal) {
        super(animal.name + " cannot be found.");
        this.setAnimal(animal);//防止被设置为null
    }
    protected void setAnimal(Animal animal) throws NullPointerException {//进行判定
        if (animal == null) {//异常情况
            throw new NullPointerException();
        }
        this.animal = animal;
    }
}