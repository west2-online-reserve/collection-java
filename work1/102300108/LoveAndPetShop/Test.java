package LoveAndPetShop;
import java.time.LocalDate;

/**
 * test类，用于检测功能是否正常
 *
 * @author xumostar
 * @date 2024/10/26
 */
public class Test {

    public static void main(String[] args) {
        //创造宠物店实例
        MyAnimalShop love=new MyAnimalShop(1115);

        //创造一些动物实例
        Dog cindy=new Dog("Cindy",2,"female", false);
        Dog rola=new Dog("Rola", 1, "male", true);
        Cat mimi=new Cat("Mimi", 2, "female");
        Cat wudong=new Cat("WuDong", 3, "female");
        Fox iris=new Fox("Iris", 2, "male");
        Fox lucien=new Fox("Lucien", 3, "male");

        //创造一些顾客实例
        Customer amy=new Customer("Amy", 0, LocalDate.now());
        Customer andy=new Customer("Andy", 1, LocalDate.of(2023,11,10));
        Customer xumostar=new Customer("Xumostar", 0, LocalDate.now());
        
        //接下来测试买入动物操作
        try{
            love.buyNewAnimal(iris);
            love.buyNewAnimal(lucien);
            love.buyNewAnimal(cindy);
            love.buyNewAnimal(mimi);
            love.buyNewAnimal(rola);
            love.buyNewAnimal(wudong);
        }catch(InsufficientBalanceException ex){
            System.out.println(ex.getMessage());
        }

        //测试开业操作
        love.OpenShop();
        System.out.println();

        //测试顾客购入动物操作
        try{
            love.serveCustomer(xumostar,iris);
            love.serveCustomer(amy,mimi);
            //测试动被买走的情况
            love.serveCustomer(amy,iris);
        }catch(AnimalNotFountException ex){
            System.out.println(ex.getMessage());
        }

        //测试动物被买空的异常状态
        try{
            love.serveCustomer(amy, rola);
            love.serveCustomer(xumostar,lucien);
            love.serveCustomer(amy,cindy);
            love.serveCustomer(xumostar, lucien);
        }catch(AnimalNotFountException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println();
        
        //加入以前未记录的老顾客andy
        love.addOldCustomer(andy);

        //测试歇业
        love.closeShop();
    }
}
