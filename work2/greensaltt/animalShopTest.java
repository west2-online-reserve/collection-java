package animalshop;

import java.time.LocalDate;

/**
 * 测试类
 */
public class animalShopTest {
    public static void main(String[] args) {
        // 创建宠物店实例(余额充足版)
        MyAnimalShop shop1 = new MyAnimalShop();
        // 给定余额
        shop1.begin = 1000;
        System.out.println("我的宠物店1的初始余额为:"+shop1.begin);
        // 初始化动物列表
        Cat cat = new Cat("猫猫cutie",2,'♀');
        ChineseRuralDog dogIsVaccinelnjected = new ChineseRuralDog("有疫苗的狗狗",
                3,'♂', true);
        ChineseRuralDog dogIsntVaccinelnjected = new ChineseRuralDog("没疫苗的狗狗",
                2,'♀',false);
        Rabbit rabbit = new Rabbit("怎可吃",1,'♀');
        // 顾客列表为空
        Customer customer = new Customer();
        // 创建顾客实例化对象
        shop1.customers.isEmpty();
        // 测试购买动物
        shop1.buyNewAnimal(cat);
        shop1.buyNewAnimal(dogIsntVaccinelnjected);
        shop1.buyNewAnimal(dogIsVaccinelnjected);
        shop1.buyNewAnimal(rabbit);
        // 测试招待顾客
        shop1.treatCustomer(customer);
        // 测试歇业
        shop1.Closed();

        System.out.println("================");
        System.out.println("以上为余额充足情况测试");
        System.out.println("================");

        // 创建宠物店实例(余额够买一只动物版)
        MyAnimalShop shop2 = new MyAnimalShop();
        // 给定余额
        shop2.begin = 100;
        System.out.println("我的宠物店2的初始余额为:"+shop2.begin);
        // 初始化动物列表
        Cat cat2 = new Cat("猫猫lovely",5,'♀');
        ChineseRuralDog dogIsVaccinelnjected2 = new ChineseRuralDog("有疫苗的狗狗",
                3,'♂', true);
        ChineseRuralDog dogIsntVaccinelnjected2 = new ChineseRuralDog("没疫苗的狗狗",
                2,'♀',false);
        Rabbit rabbit2 = new Rabbit("玉兔",1,'♀');
        // 顾客列表为空
        Customer customer2 = new Customer();
        // 创建顾客实例化对象
        shop2.customers.isEmpty();
        // 测试购买动物
        shop2.buyNewAnimal(cat2);
        shop2.buyNewAnimal(dogIsntVaccinelnjected2);
        shop2.buyNewAnimal(dogIsVaccinelnjected2);
        shop2.buyNewAnimal(rabbit2);
        // 测试招待顾客
        shop2.treatCustomer(customer2);
        // 测试歇业
        shop2.Closed();

        System.out.println("================");
        System.out.println("以上为余额只够买一只情况测试");
        System.out.println("================");

        // 创建宠物店实例(余额不够版)
        MyAnimalShop shop3 = new MyAnimalShop();
        // 给定余额
        shop3.begin = 50;
        System.out.println("我的宠物店2的初始余额为:"+shop3.begin);
        // 初始化动物列表
        Cat cat3 = new Cat("猫猫crazy",5,'♀');
        ChineseRuralDog dogIsVaccinelnjected3 = new ChineseRuralDog("有疫苗的狗狗",
                3,'♂', true);
        ChineseRuralDog dogIsntVaccinelnjected3 = new ChineseRuralDog("没疫苗的狗狗",
                2,'♀',false);
        Rabbit rabbit3 = new Rabbit("不是兔子",1,'♀');
        // 顾客列表为空
        Customer customer3 = new Customer();
        // 创建顾客实例化对象
        shop3.customers.isEmpty();
        // 测试购买动物
        shop3.buyNewAnimal(cat3);
        shop3.buyNewAnimal(dogIsntVaccinelnjected3);
        shop3.buyNewAnimal(dogIsVaccinelnjected3);
        shop3.buyNewAnimal(rabbit3);
        // 测试招待顾客
        shop3.treatCustomer(customer3);
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
 * "C:\Program Files\Java\jdk-1.8\bin\java.exe" "-javaagent:D:\java\IntelliJ IDEA 2023.2.2\lib\idea_rt.jar=60555:D:\java\IntelliJ IDEA 2023.2.2\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk-1.8\jre\lib\charsets.jar;C:\Program Files\Java\jdk-1.8\jre\lib\deploy.jar;C:\Program Files\Java\jdk-1.8\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk-1.8\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk-1.8\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk-1.8\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk-1.8\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk-1.8\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk-1.8\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk-1.8\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk-1.8\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk-1.8\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk-1.8\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk-1.8\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk-1.8\jre\lib\javaws.jar;C:\Program Files\Java\jdk-1.8\jre\lib\jce.jar;C:\Program Files\Java\jdk-1.8\jre\lib\jfr.jar;C:\Program Files\Java\jdk-1.8\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk-1.8\jre\lib\jsse.jar;C:\Program Files\Java\jdk-1.8\jre\lib\management-agent.jar;C:\Program Files\Java\jdk-1.8\jre\lib\plugin.jar;C:\Program Files\Java\jdk-1.8\jre\lib\resources.jar;C:\Program Files\Java\jdk-1.8\jre\lib\rt.jar;D:\javaSE\out\production\javaSE" animalshop.animalShopTest
 * 我的宠物店1的初始余额为:1000.0
 * ========正在购买猫猫========
 * 购买猫猫cutie成功，余额为：800.0
 * ========正在购买中华田园犬========
 * 该中华田园犬未接种疫苗，不能购买
 * ========正在购买中华田园犬========
 * 购买有疫苗的狗狗成功，余额为：900.0
 * ========正在购买兔兔========
 * 购买怎可吃成功，余额为：700.0
 * ========正在接待顾客========
 * 请输入顾客姓名：
 * 张三 （输入）
 * 请输入顾客到店次数：
 * 3 （输入）
 * 猫猫的名字为：猫猫cutie
 * 猫猫的年龄为：2
 * 猫猫的性别为：♀
 * 猫猫的价格为：200.0
 * 猫猫的售价为：700.0
 * 出售猫猫cutie成功，余额为：1400.0
 * =========歇业========
 * 以下为今日光顾的顾客：
 * 顾客的姓名为：张三
 * 该顾客的到店次数为：3
 * 该顾客的最新到店时间为：2023-10-15
 * 今日的利润为：400.0
 * ================
 * 以上为余额充足情况测试
 * ================
 * 我的宠物店2的初始余额为:100.0
 * ========正在购买猫猫========
 * animalshop.InsufficientBalanceException
 * ========正在购买中华田园犬========
 * 该中华田园犬未接种疫苗，不能购买
 * ========正在购买中华田园犬========
 * 购买有疫苗的狗狗成功，余额为：0.0
 * ========正在购买兔兔========
 * animalshop.InsufficientBalanceException
 * ========正在接待顾客========
 * 请输入顾客姓名：
 * 李四 （输入）
 * 请输入顾客到店次数：
 * 4 （输入）
 * 中华田园犬的名字为：有疫苗的狗狗
 * 中华田园犬的年龄为：3
 * 中华田园犬的性别为：♂
 * 中华田园犬的价格为：100.0
 * 中华田园犬是否接种疫苗：true
 * 出售有疫苗的狗狗成功，余额为：200.0
 * =========歇业========
 * 以下为今日光顾的顾客：
 * 顾客的姓名为：李四
 * 该顾客的到店次数为：4
 * 该顾客的最新到店时间为：2023-10-15
 * 今日的利润为：100.0
 * ================
 * 以上为余额只够买一只情况测试
 * ================
 * 我的宠物店2的初始余额为:50.0
 * ========正在购买猫猫========
 * animalshop.InsufficientBalanceException
 * ========正在购买中华田园犬========
 * animalshop.InsufficientBalanceException
 * ========正在购买中华田园犬========
 * animalshop.InsufficientBalanceException
 * ========正在购买兔兔========
 * animalshop.InsufficientBalanceException
 * ========正在接待顾客========
 * 请输入顾客姓名：
 * 王五 （输入）
 * 请输入顾客到店次数：
 * 5 （输入）
 * animalshop.AnimalNotFoundException
 * =========歇业========
 * 以下为今日光顾的顾客：
 * 顾客的姓名为：王五
 * 该顾客的到店次数为：5
 * 该顾客的最新到店时间为：2023-10-15
 * 今日的利润为：-50.0
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