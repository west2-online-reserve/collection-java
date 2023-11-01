import java.util.ArrayList;

/**
 * 自己的宠物店
 * @author 1293978818
 */
public class MyAnimalShop implements AnimalShop{
    private double restMoney;
    private ArrayList<AbstractAnimal> animals;
    private ArrayList<Customer> customers;
    private boolean isClose;
    private double earned;


    @Override
    public void buy(AbstractAnimal animal) throws InsufficientBalanceException{
        if(restMoney >= animal.getAnimalPrice()){
            animals.add(animal);
            restMoney -= animal.getAnimalPrice();
            earned -= animal.getAnimalPrice();
            System.out.println("购买成功");
        }else{
            throw new InsufficientBalanceException("余额不足，购买失败");
        }
    }

    @Override
    public void treatCustomers(Customer customer,AbstractAnimal animal,double money) throws AnimalNotFoundException{
        if(!isClose){
            
            for(int i = 0;i < animals.size();i ++){
                if(animals.get(i) instanceof Dogs && animal instanceof Dogs){
                    if(money < animals.get(i).animaiPrice){
                        System.out.println("出价过低，出售失败");
                        return;
                    }
                    if (customer.getCustomerVisitTime() == 0){
                        customers.add(customer);
                    }
                    customer.setCustomerVisitTime(customer.getCustomerVisitTime() + 1);
                    restMoney += money;
                    earned += money;
                    System.out.println("出售完成");
                    System.out.println(animals.get(i));
                    animals.remove(i);
                    return;
                }else if(animal instanceof Cat && animals.get(i) instanceof Cat){
                    if(money < animals.get(i).animaiPrice){
                        System.out.println("出价过低，出售失败");
                        return;
                    }
                    if (customer.getCustomerVisitTime() == 0){
                        customers.add(customer);
                    }
                    customer.setCustomerVisitTime(customer.getCustomerVisitTime() + 1);
                    earned += money;
                    System.out.println("出售完成");
                    System.out.println(animals.get(i));
                    animals.remove(i);
                    return;
                }
            }
            throw new AnimalNotFoundException("店内暂无该动物");
        }else{
            System.out.println("店铺歇业中");
        }        
    }

    @Override
    public void getClose() {
        for(int i = 0;i < customers.size();i ++){
            System.out.println(customers.get(i));
        }
        System.out.println("共赚了" + earned + "元");
        isClose = true;
    }

    public MyAnimalShop(double restMoney, ArrayList<AbstractAnimal> animals, ArrayList<Customer> customers,
            boolean isClose) {
        this.restMoney = restMoney;
        this.animals = animals;
        this.customers = customers;
        this.isClose = isClose;
    }

    public double getRestMoney() {
        return restMoney;
    }

    public void setRestMoney(double restMoney) {
        this.restMoney = restMoney;
    }

    
    
    
}
