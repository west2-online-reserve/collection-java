package animalshop;

import java.time.LocalDate;

/**
 *
 * 测试类
 */
public class animalShopTest {
    public static void main(String[] args) {
        // 创建宠物店实例(余额充足版)
        MyAnimalShop shop1 = new MyAnimalShop();
        // 给定余额
        shop1.setBegin(1000);
        System.out.println("我的宠物店1的初始余额为:"+shop1.getBegin());
        // 初始化动物列表
        Cat cat = new Cat("cutie",2,'♀');
        ChineseRuralDog dogIsVaccineInjected = new ChineseRuralDog("有疫苗dog",
                3,'♂', true);
        ChineseRuralDog dogIsntVaccineInjected = new ChineseRuralDog("没疫苗dog",
                2,'♀',false);
        Rabbit rabbit = new Rabbit("kitty",1,'♀');
        // 创建顾客实例化对象
        Customer customer = new Customer();
        // 测试购买动物
        shop1.buyNewAnimal(cat);
        shop1.buyNewAnimal(dogIsntVaccineInjected);
        shop1.buyNewAnimal(dogIsVaccineInjected);
        shop1.buyNewAnimal(rabbit);
        // 测试招待顾客
        shop1.treatCustomer(customer,cat);
        // 测试歇业
        shop1.Closed();

        System.out.println("================");
        System.out.println("以上为余额充足情况测试");
        System.out.println("================");



        // 创建宠物店实例(余额够买一只动物版)
        MyAnimalShop shop2 = new MyAnimalShop();
        // 给定余额
        shop2.setBegin(100);
        System.out.println("我的宠物店2的初始余额为:"+shop2.getBegin());
        // 初始化动物列表
        Cat cat2 = new Cat("lovely",5,'♀');
        ChineseRuralDog dogIsVaccineInjected2 = new ChineseRuralDog("有疫苗dog2",
                3,'♂', true);
        ChineseRuralDog dogIsntVaccineInjected2 = new ChineseRuralDog("没疫苗dog2",
                2,'♀',false);
        Rabbit rabbit2 = new Rabbit("dragon",1,'♀');
        // 创建顾客实例化对象
        Customer customer2 = new Customer();
        // 测试购买动物
        shop2.buyNewAnimal(cat2);
        shop2.buyNewAnimal(dogIsntVaccineInjected2);
        shop2.buyNewAnimal(dogIsVaccineInjected2);
        shop2.buyNewAnimal(rabbit2);
        // 测试招待顾客
        shop2.treatCustomer(customer2,rabbit2);
        // 测试歇业
        shop2.Closed();

        System.out.println("================");
        System.out.println("以上为余额只够买一只情况测试");
        System.out.println("================");

        // 创建宠物店实例(余额不够版)
        MyAnimalShop shop3 = new MyAnimalShop();
        // 给定余额
        shop3.setBegin(50);
        System.out.println("我的宠物店3的初始余额为:"+shop3.getBegin());
        // 初始化动物列表
        Cat cat3 = new Cat("crazy",5,'♀');
        ChineseRuralDog dogIsVaccineInjected3 = new ChineseRuralDog("有疫苗dog3",
                3,'♂', true);
        ChineseRuralDog dogIsntVaccineInjected3 = new ChineseRuralDog("没疫苗dog3",
                2,'♀',false);
        Rabbit rabbit3 = new Rabbit("mad",1,'♀');
        // 创建顾客实例化对象
        Customer customer3 = new Customer();
        // 测试购买动物
        shop3.buyNewAnimal(cat3);
        shop3.buyNewAnimal(dogIsntVaccineInjected3);
        shop3.buyNewAnimal(dogIsVaccineInjected3);
        shop3.buyNewAnimal(rabbit3);
        // 测试招待顾客
        shop3.treatCustomer(customer3,dogIsVaccineInjected3);
        // 测试歇业
        shop3.Closed();

        System.out.println("================");
        System.out.println("以上为余额不足情况测试");
        System.out.println("================");
    }

}

/**
 * 测试结果如下
 *
 *我的宠物店1的初始余额为:1000.0
 * ========正在购买cutie========
 * 购买cutie成功，余额为：800.0
 * ========正在购买没疫苗dog========
 * 该中华田园犬未接种疫苗，不能购买
 * ========正在购买有疫苗dog========
 * 购买有疫苗dog成功，余额为：700.0
 * ========正在购买kitty========
 * 购买kitty成功，余额为：550.0
 * ========正在接待顾客========
 * 请输入顾客姓名：
 * 张三
 * 请输入顾客到店次数：
 * 3
 * 店内有cutie
 * 出售cutie成功，余额为：1250.0
 * =========歇业========
 * 以下为今日光顾的顾客：
 * 顾客的姓名为：张三
 * 该顾客的到店次数为：3
 * 该顾客的最新到店时间为：2023-10-18
 * 今日的利润为：250.0
 * 是否关店：true
 * ================
 * 以上为余额充足情况测试
 * ================
 * 我的宠物店2的初始余额为:100.0
 * ========正在购买lovely========
 * animalshop.InsufficientBalanceException
 * ========正在购买没疫苗dog2========
 * 该中华田园犬未接种疫苗，不能购买
 * ========正在购买有疫苗dog2========
 * 购买有疫苗dog2成功，余额为：0.0
 * ========正在购买dragon========
 * animalshop.InsufficientBalanceException
 * ========正在接待顾客========
 * 请输入顾客姓名：
 * 李四
 * 请输入顾客到店次数：
 * 4
 * 店内没有dragon
 * animalshop.AnimalNotFoundException
 * =========歇业========
 * 以下为今日光顾的顾客：
 * 顾客的姓名为：李四
 * 该顾客的到店次数为：4
 * 该顾客的最新到店时间为：2023-10-18
 * 今日的利润为：-100.0
 * 是否关店：true
 * ================
 * 以上为余额只够买一只情况测试
 * ================
 * 我的宠物店3的初始余额为:50.0
 * ========正在购买crazy========
 * animalshop.InsufficientBalanceException
 * ========正在购买没疫苗dog3========
 * 该中华田园犬未接种疫苗，不能购买
 * ========正在购买有疫苗dog3========
 * animalshop.InsufficientBalanceException
 * ========正在购买mad========
 * animalshop.InsufficientBalanceException
 * ========正在接待顾客========
 * 请输入顾客姓名：
 * 王五
 * 请输入顾客到店次数：
 * 5
 * 店内没有有疫苗dog3
 * animalshop.AnimalNotFoundException
 * =========歇业========
 * 以下为今日光顾的顾客：
 * 顾客的姓名为：王五
 * 该顾客的到店次数为：5
 * 该顾客的最新到店时间为：2023-10-18
 * 今日的利润为：0.0
 * 是否关店：true
 * ================
 * 以上为余额不足情况测试
 * ================
 *
 *
 * 辛苦学长学姐看我的pr！！！
 *
 * 对格式要求还不是特别熟，如果有错的拜托学长学姐指出啦！
 *
 * 我一定尽量改
 *
 * Process finished with exit code 0
 */