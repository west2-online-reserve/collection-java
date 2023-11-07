/**
 * 宠物店接口
 *
 * @author pany0593
 * @date 2023/11/5
 */
public interface AnimalShop {


    /**
     * 传入购买的宠物
     * 添加到宠物店
     * 余额中扣去宠物价格
     *
     * @return 余额
     */
    double buyNewAnimal(AbstractAnimal animal);

    /**
     * 传入需要招待的客户
     * 添加到宠物店
     */
    void serveCustomer(Customer customer,AbstractAnimal animal);

    /**
     * 关店
     */
    void closeShop();

}
