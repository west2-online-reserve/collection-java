package modification;


public class Text2 {
    public static void main(String[] args) {
        ChineseDog dog = new ChineseDog("  ",2,"male");
        dog.setVaccineInjected(true);
        cat cat=new cat("肥波",2,"male");
        bird bird=new bird("涂涂",2,"male");
        Customer w = new Customer("王女士",2);
        Customer x= new Customer("谢老板",0);
        Customer L=new Customer("刘老板",0);
        MyAnimalShop yu=new MyAnimalShop(1000,false);
        System.out.println(yu.getAnimalList());
        yu.purchase(bird,2);//白手起家，先买两只鸟，购买成功。
        yu.purchase(cat,2);//再买两只猫，购买成功。
        yu.purchase(dog,2);//再买两只狗，购买成功。

        yu.treat(x,dog);//谢老板买了只狗。
        yu.treat(w,cat);
        yu.treat(w,bird);
        yu.isClosed();//歇业
        yu.treat(L,bird);//失败，已歇业
        yu.purchase(dog,9);//失败，已歇业
        yu.isOpen();//开张
        yu.purchase(cat,2);
        yu.treat(w,cat);
    }
}
