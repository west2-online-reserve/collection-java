/**
 * 宠物店接口
 * @author 1293978818
 */
public interface AnimalShop {

    /**
     * 购买动物
     * @param animal
     */
    void buy(AbstractAnimal animal);

    /**
     * 招待客户
     * @param customer
     * @param animal
     * @param money
     */
    void treatCustomers(Customer customer,AbstractAnimal animal,double money);

    /**
     * 歇业
     */
    void getClose();
}
