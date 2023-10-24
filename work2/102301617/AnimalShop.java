/**
 * Some method to achieve MyAnimalShop.
 * .....
*@author 102301617
 *
 */
public interface AnimalShop {
    /**
     * @param  animal

     * 买入动物新动物*/


     void perchase(Animal animal);

    /**
     * @param customer
     * 招待顾客*/
     void entertaining(Customer customer);

    /**
     * 歇业
     * 返回顾客列表，剩余动物，利润*/
     void close();


}
