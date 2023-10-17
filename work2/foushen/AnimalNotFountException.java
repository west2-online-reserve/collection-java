public class AnimalNotFountException extends RuntimeException {
    private String message;
    private Animal lackAnimal;

    public AnimalNotFountException(String message, Animal lackAnimal) {
        this.message = message;
        this.lackAnimal = lackAnimal;

    }

    public String toString() {
        return message+" " + lackAnimal;
    }
}
