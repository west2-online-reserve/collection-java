/**
 * @author XiaoxiongMeng
 */
public interface AnimalShop {

    /**
     *
     * @param animal
     *
     */
    void buy(BaseAnimal animal);

    void close();

    void welcome(Customer customer);

}
