import java.util.ArrayList;

/**
 * @projectName（项目名称）: west2online_work2
 * @className（类名称）: MyAnimalShop
 * @description（类描述）: 我的宠物类
 * @author（创建人）: mewchao
 * @createDate（创建时间）: 2023-09-18
 */
public class MyAnimalShop implements AnimalShop {
    /**
     * 店内余额
     **/
    private double balance;
    /**
     * 顾客列表留作纪念
     **/
    ArrayList<Customer> customerList = new ArrayList<>();
    /**
     * 店内动物列表
     **/
    ArrayList<Animal> animalList = new ArrayList<>();
    /**
     * 是否正在营业
     **/
    boolean isopened;

    /**
     * @param animal: 动物
     * @description: 为宠物店购入一只宠物
     */
    @Override
    public void addAnimal(Animal animal) {
        if (this.balance >= animal.getPrice()) {
            balance -= animal.getPrice();
            animalList.add(animal);
        } else {
            throw new InsufficientBalanceException(balance, animal.price);
        }
    }

    /**
     * @param customer: 顾客
     * @description: 招待顾客
     */
    @Override
    public void entertainCustomers(Customer customer, Animal animal) {
        boolean isNew = true;
        boolean isExist = false;
        for (Customer c : customerList) {
            if (c == customer) isNew = false;
        }
        if (isNew) {
            customerList.add(customer);
        }
        for (Animal a : animalList) {
            if (a == animal) isExist = true;
        }
        if(!isExist){
            throw new AnimalNotFountException();
        }
        else{
            this.balance+=animal.getPrice();
            animalList.remove(animal);
        }

    }
}
