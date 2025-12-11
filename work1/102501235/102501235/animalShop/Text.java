package animalShop;

import java.time.LocalDate;

public class Text {
    public static void main(String[] args) {
        MyAnimalShop shop = new  MyAnimalShop(1000.0);

        try{
            shop.buyNewAnimal(new ChineseRuralDog("茶叶盒",1,"公",false));
            shop.buyNewAnimal(new cat("茶叶和",1,"母"));
        } catch (InsufficientBalanceException e) {
            System.err.println("购买失败"+e.getMessage());
        }

        Customer c1 = new Customer("小陈",91, LocalDate.now());
        shop.serveCustomer(c1,"茶叶和");
        try{
            shop.serveCustomer(c1,"何意味");
        }catch(AnimalNotFountException e){
            System.err.println("购买失败,"+e.getMessage()+"\n");
        }

        shop.closeShop(LocalDate.now());

        int[] a ={1,3,5,7,9};
        int[] b ={2,4,6,8,10};
        shop.printArray(a,b);

        String email1 = "cyh919191@qq.com";
        String email2 = "919191cyh.email.com";
        if(shop.isValidEmail(email1)){
            System.out.println(email1+" 合法");
        }else{
            System.out.println(email1+" 不合法");
        }
        if(shop.isValidEmail(email2)){
            System.out.println(email2+" 合法");
        }else{
            System.out.println(email2+" 不合法");
        }
    }
}
