package myshop;

public class Test {
    public static void main(String[] args) {
        //完全人肉测试: 敬请见证
        //初始化我的宠物店
        MyAnimalShop myAnimalShop = new MyAnimalShop();
        //启动资金2000元,要试试其它功能再改
        myAnimalShop.setMoney(2000);
        //开店
        myAnimalShop.open();
        //先试试买三只动物来买
        for (int i = 0; i < 3; i++) {
            try {
                myAnimalShop.buyAnimal(myAnimalShop);
            } catch (RuntimeException noMoney) {
                System.out.println(noMoney.getMessage());
                continue;
            }
            System.out.println("==============");
            myAnimalShop.printAnimals(myAnimalShop.getAnimals());
            System.out.println(myAnimalShop.getMoney());
            System.out.println("==============");
        }
        //再试试叫几个客户来买,记得试试相同的客户名
        for (int i = 0; i < 4; i++) {
            try {
                myAnimalShop.serveCustomer(myAnimalShop);
            } catch (RuntimeException noAnimal) {
                System.out.println(noAnimal.getMessage());
                continue;
            }
            System.out.println("==============");
            myAnimalShop.printAnimals(myAnimalShop.getAnimals());
            System.out.println(myAnimalShop.getMoney());
            System.out.println(myAnimalShop.getCustoms());
            System.out.println("==============");
        }
        //最后关店
        myAnimalShop.close(myAnimalShop);
    }
}
