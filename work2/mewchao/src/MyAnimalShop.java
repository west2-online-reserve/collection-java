import java.util.ArrayList;
import java.util.LinkedList;
/**
 * @projectName（项目名称）: west2online_work2
 * @className（类名称）: MyAnimalShop
 * @description（类描述）: 我的宠物类
 * @author（创建人）: mewchao
 * @createDate（创建时间）: 2023-09-18
 */
public class MyAnimalShop implements AnimalShop{
    /**店内余额**/
    private double balance;
    /**顾客列表留作纪念**/
    LinkedList customerList;
    /**店内动物列表**/
    ArrayList animalList;
    /**是否正在营业**/
    boolean isopened;

    /**
     * 可以直接抛出的异常
     * @param animal
     */
    @Override
    public void addAnimal(Animal animal) {
        if(this.balance>animal.getPrice()){
            balance-=animal.getPrice();
            //添加到列表
        }else{
            throw new InsufficientBalanceException(balance, animal.price);
        }
    }
    //招待客户
    @Override
    public void entertainCustomers() {

    }
}
