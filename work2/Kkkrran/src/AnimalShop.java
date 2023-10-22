/**
 * Date 2023/10/18  13:34
 *
 * @author Kkkrran
 * @version 1.0
 */
public interface AnimalShop {
    void buyIn(Animal animalIn);

    void customerReception(Customer c, String typeOfAnimal);

    void close();
}
