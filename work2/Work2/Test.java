package Work2;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        MyAnimalShop Shop= new MyAnimalShop();;
        buyAnimal buyAnimal = new buyAnimal();

        Cat cat1 = new Cat("Lily","5","female","120");
        Parrot parrot1 = new Parrot("Alice","3","female","150");
        Dog dog = new Dog("Aatrox","3","male","130");
        Customer customer1 = new Customer("Leblanc");
        LocalDate date1 = LocalDate.of(2024, 10, 1);
        try {
            buyAnimal(cat1);
        } finally {

        }
    }

    private static void buyAnimal(Cat cat1) {
    }

    private static class buyAnimal {
    }
}
