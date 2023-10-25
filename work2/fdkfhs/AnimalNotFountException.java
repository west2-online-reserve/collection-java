public class AnimalNotFountException extends RuntimeException {
    private String name;

    public AnimalNotFountException() {
    }

    public AnimalNotFountException(String name) {
        this.name = name;
    }

    public void printDetailedError() {
        System.out.println("本店没有动物" + name);
    }
}