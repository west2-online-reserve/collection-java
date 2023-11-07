package Xuxiiii;
/* @author Xuxiiii
 * @data 2023/11/5
 */


import java.time.LocalDate;
import java.util.ArrayList;

public class MyAnimalShop implements Animalshop{
    private String name;
    private double money;
    private boolean isOnBusiness =true;
    // 用close方法关闭

    private ArrayList<Animal> animals=new ArrayList<>();
    private ArrayList<Customer> customers=new ArrayList<>();
    // 动物与顾客的列表

    public MyAnimalShop(String name, double money, boolean isOnBusiness){
        this.name=name;
        this.money=money;
        this.isOnBusiness=isOnBusiness;
    }

    @Override
    public void buy(Animal animal) throws AnimalNotFountException {
        if( (this.money-animal.getPrice() )<0){
            throw new InsufficientBalanceException(name,money);
        }else{this.money-=animal.getPrice();
            animals.add(animal);}
    }

    @Override
    public void entertain(Customer b, int i) {
        //判断是否有库存宠物
        try {
            if (animals.size() != 0){
                System.out.println("买第" + i + "只宠物");
                System.out.println(animals.get(i - 1));
                money += animals.get(i-1).price;
                animals.remove(i - 1);
            }else {
                throw new AnimalNotFountException(animals.size(), b);
            }
        } catch (AnimalNotFountException e) {
            System.out.println(e);
        }
        customers.add(b);
        b.setTimes(b.getTimes() + 1);
        b.setNow(LocalDate.now());

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
