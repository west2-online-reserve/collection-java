public class AnimalNotFountException extends RuntimeException {
    private String message;//存放异常信息
    private Animal animal;//缺失的动物

    public AnimalNotFountException(String message, Animal animal) {
        this.message = message;
        this.animal=animal;
    }

    public String getMessage() {
        return message;
    }

    public Animal getAnimal() {
        return animal;
    }
}
