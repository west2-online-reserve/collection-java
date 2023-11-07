package Xuxiiii;
/* @author Xuxiiii
 * @data 2023/11/5
 */


import java.time.LocalDate;
import java.util.ArrayList;

public class MyAnimalShop implements Animalshop{
    private String name;
    private double money;
    private boolean isOnBusiness;
    // 用close方法关闭
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    // 动物与顾客的列表
    public MyAnimalShop(String name, double money, boolean isOnBusiness){
        this.name = name;
        this.money = money;
        this.isOnBusiness = isOnBusiness;
    }

    @Override
    public void buy(Animal animal) throws AnimalNotFountException {
        if( (this.money-animal.getPrice() )<0){
            throw new InsufficientBalanceException(name,money);
        }else{this.money-=animal.getPrice();
            animals.add(animal);
        }
    }
    @Override
    public void entertain(Customer b, int i) {
        try {
            if(isOnBusiness){
                if(animals.isEmpty()){
                    throw new AnimalNotFountException(animals.size(),b);
                }else {
                    System.out.println(
                            b.getName()+
                                    "您购买的" + animals.get(i-1).getName() +
                                    "性别为" + animals.get(i-1).getSex()+
                                    "年龄为" + animals.get(i-1).getAge()+
                                    "价格为" + animals.get(i-1).getPrice()

                    );
                    money-=animals.get(i-1).getPrice();
                    animals.remove(i-1);
                }
            }else {
                System.out.println("抱歉我们歇业了");
            }
        }catch (AnimalNotFountException e){
            System.out.println(e);
        }
        customers.add(b);
        b.setTimes(b.getTimes() + 1);
        b.setNow(LocalDate.now());
        //顾客状态更新

    }

    @Override
    public void close() {
        this.isOnBusiness=false;
        for (int i = 0; i < customers.size(); i++) {
            System.out.println("第"+(i+1)+"顾客的个人信息为 "+customers.get(i));

        }
        System.out.println("总钱数"+money);
    }
}
