package work2;

import java.util.ArrayList;

/**
 * @author jason
 */
public interface AnimalShop {

    /**
     * 买入动物, 在动物列表arrayListOfAnimals中添加.
     *
     * @param marketOfAnimals 传入动物市场列表marketOfAnimals用于买入
     */
    public abstract void buyNewAnimal(ArrayList<AbstractAnimal> marketOfAnimals);

    /**
     * 接受顾客参数, 在顾客列表arrayListOfCustomers中加入新顾客.
     * 出售动物，如果店内没有动物，抛出AnimalNotFoundException.
     * 通过toString输出动物信息，并把钱入账，将动物移除列表arrayListOfAnimals.
     *
     * @param customer 传入Customer的对象customer作为要招待的顾客
     */
    public abstract void serveCustomer(Customer customer);

    /**
     * 输出当天光顾的客户的列表信息，计算并输出一天的利润.
     */
    public abstract void closeBusiness();
}
