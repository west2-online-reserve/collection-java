package animal;

import java.time.LocalDate;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {




        LocalDate today=LocalDate.of(2023,9,26);//设置今天的日期


     //以下设置小猫小狗的属性

        //这里是店里出售的小猫小狗
        Cat c1=new Cat(11,"花花",true,200);
        Cat c2=new Cat(12,"草草",false,200);
        Dog d1=new Dog(11,"狗一",false,100);
        d1.setVaccineInjected(true);
        Dog d2=new Dog(11,"狗二",true,100);
        d2.setVaccineInjected(false);



   //这里是批发的小猫小狗
        Cat c3=new Cat(15,"红红",true,150);
        Cat c4=new Cat(15,"黄黄",false,150);
        Dog d3=new Dog(11,"狗三",false,80);
        d1.setVaccineInjected(true);
        Dog d4=new Dog(16,"狗四",false,80);
        d1.setVaccineInjected(true);

       //然后我在这里设置顾客的到店日期
        LocalDate dd1=LocalDate.of(2023,9,26);
        LocalDate dd2=LocalDate.of(2023,8,26);


        //然后这里初始化顾客的一些属性
        Customer cu1=new Customer("华强",33,dd1);
        Customer cu2=new Customer("撒日朗",33,dd2);
        Customer cu3=new Customer("蔡徐坤",250,dd1);


       //然后这里初始化我的宠物店的一些信息
        MyAnimalShop mine=new MyAnimalShop(10,true);


//这里初始化一个集合，等一下会导入宠物店
        ArrayList animalForSell=new ArrayList<Animal >();
        animalForSell.add(c1); animalForSell.add(c2);animalForSell.add(d1);animalForSell.add(d2);

//把上面的集合导入宠物店，作为宠物店在卖的宠物列表
        mine.setList1(animalForSell);






//然后下面就是按照任务走的自定义异常，然后把它红体字打印出来
        // try {
        //mine.setMoney(0);
         //mine.buyDog(d3);
        //}catch (InsufficientBalanceException e){
         //e.printStackTrace();
        //};


//这里测试我的异常能否运行，这个是顾客买宠物
        //try{
        //mine.list1.clear()

         //   mine.treatCustomer(cu1);
        //}catch(AnimalNotFoundException e){
          //  e.printStackTrace();
        //}
//下面就是为了测试offDuty 方法
        //mine.treatCustomer(cu1);
        //mine.treatCustomer(cu2);
        //mine.treatCustomer(cu3);


        //mine.offDuty(today);



   //下面是为了测试  buyDog   buyCat方法
        //mine.setMoney(0);
        //mine.setMoney(300);
        //mine.buyDog(d3);
        //mine.buyCat(c3);
        //System.out.println(list1.get(4));
        //System.out.println(list1.get(5));
        //System.out.println("余额为"+mine.getMoney());

        //下面测试treatCustomer方法

        //mine.setMoney(0);
        //mine.treatCustomer(cu1);
        //mine.treatCustomer(cu2);
        //for(Customer customer: mine.list2)
        //{
        //    System.out.println(customer.toString());

        //}
        //System.out.println(mine.getMoney());













    }


}
