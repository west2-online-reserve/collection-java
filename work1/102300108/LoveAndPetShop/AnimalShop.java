package LoveAndPetShop;

/**
 * 接口AnimalShop的创建
 *
 * @author xumostar
 * @date 2024/10/26
 */

interface AnimalShop{
    //买入新动物
    void buyNewAnimal(Animal animal);

    //招待顾客
    void serveCustomer(Customer customer,Animal animal);

    //歇业
    void closeShop();
}