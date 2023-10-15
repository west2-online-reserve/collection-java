/**
 * @author pengyuezhe
 */
public interface AnimalShop {
    /**
     * 购买功能
     * @param animal 商店购买的动物
     */
    void purchase(Animal animal);
    /**
     * 服务顾客
     * @param customer 前来购买的顾客
     * @param animal 顾客想买的动物
     */
    void service(Customer customer, Animal animal);
    /**
     * 是否关闭
     */
    void isClosed();
}
