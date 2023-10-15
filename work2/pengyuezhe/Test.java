/**
 * @author pengyuezhe
 */
public class Test {
    public static void main(String[] args) {
        /*
          new两只猫猫（test里会悬空所以该部分注释使用块注释）
         */
        Cat lala = new Cat("lala",1,"female");
        Cat mimi = new Cat("mimi",1,"female");
        /*
          new两只狗狗
         */
        ChineseRuralDog puppy = new ChineseRuralDog("puppy",1,"male",true);
        ChineseRuralDog lucky = new ChineseRuralDog("lucky",1,"female",true);
        /*
          new我的宠物店和对手的宠物店
         */
        MyAnimalShop myShop = new MyAnimalShop(0,false);
        MyAnimalShop hisShop = new MyAnimalShop(0,false);
        /*
          new三位顾客
          mike常客
          marry以前来过一次，今天没来
          lorry从没来过,按默认假定上一次前来日期
         */
        Customer mike = new Customer("mike",2,2023,7,13);
        Customer marry = new Customer("marry",1,2022,10,8);
        Customer lorry = new Customer("lorry",0,0,1,1);
        /*
        宠物店启动资金为300元
         */
        myShop.setMoney(300);
        /*
        店铺购入小猫mimi
         */
        myShop.purchase(mimi);
        myShop.purchase(lucky);
        /*
        顾客mike第一次来想要绕过宠物店购买小狗puppy（失败）
         */
        myShop.service(mike,puppy);
        /*
        mike第二次最终购买了mimi同时到店的所有信息更新
         */
        myShop.service(mike,mimi);
        /*
        新顾客lorry来购买lucky
         */
        myShop.service(lorry,lucky);
        /*
        假设店铺今天打烊
        输出相应信息（测试关店）
         */
        myShop.isClosed();
        /*
        店铺重新开张
        输出相应信息（测试开店）
         */
        myShop.open();
        /*
        测试marry的信息展开
         */
        System.out.println(marry);
        /*
        对手的宠物店没有启动资金就购买宠物（抛出异常）
         */
        hisShop.purchase(lala);
        /*
        当宠物店没有宠物再购买时才会出现AnimaNotFoundException
        有宠物但未找到想购买的宠物会由宠物店提示未查找到指定宠物
         */
    }
}
