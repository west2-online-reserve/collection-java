package Shop;

/**
 * @author  wjord
 */
interface AnimalShop {

    /**
     * 实现购买宠物，假设市场足够大，可以满足店主的全部要求
     */
    void purchase();

    /**
     * 实现接待顾客
     */
    void serveCustomer();

    /**
     * 关店
     */
    void closeShop();

    /**
     * 开店
     */
    void openShop();

    /**
     * 基础页面，使得店主可以进行一定操作调用其他方法
     */
    void basicPage();

}
