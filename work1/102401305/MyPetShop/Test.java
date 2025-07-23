package MyPetShop;

import java.time.LocalDate;
import java.util.Scanner;

public class Test {

        public static void main(String[] args) {
                Scanner in = new Scanner(System.in);

                System.out.println("BanGDream宠物店开业大吉");
                MyAnimalShop BanGDreamPetShop = new MyAnimalShop();

                //宠物装填
                Dog Uika = new Dog("Uika", 1, 'F', "Yes");
                Cat Lisa = new Cat("Lisa", 1, 'F');
                Penguin Tomorin = new Penguin("Tomorin", 1, 'F');

                //顾客装填
                Customer Sakiko = new Customer("Sakiko", 1, LocalDate.of(2025, 7, 6));
                Customer Yukina = new Customer("Yukina", 1, LocalDate.of(2025, 7, 15));
                Customer Rikki = new Customer("Rikki", 1, LocalDate.of(2025, 7, 23));
                Customer Sayo = new Customer("Sayo", 0, LocalDate.now()); //新客

                System.out.println("请输入昨日店内余额以激活系统:");
                int balance = in.nextInt();
                BanGDreamPetShop.openShop(balance);

                //购买宠物
//                BanGDreamPetShop.buyNewAnimal(Uika);
//                BanGDreamPetShop.buyNewAnimal(Lisa);
//                BanGDreamPetShop.buyNewAnimal(Tomorin);

                //欢迎新老顾客前来选购（载入老顾客信息）
                BanGDreamPetShop.initialCustomerInformation(Sakiko);
                BanGDreamPetShop.initialCustomerInformation(Yukina);
                BanGDreamPetShop.initialCustomerInformation(Rikki);
                //这里如果给顾客设置的最新到店时间是今天 那程序就会变成很麻烦（？ 比如小店没买到宠物今天歇业了 但是Rikki今天来过 我要输出她的信息
                //那我就需要提前载入她的信息（因为后面顾客没办法再到店购买了 更新顾客信息的方法不会被调用
                //如果提前载入的话 那就要在关店输出顾客信息时候做一个筛选

                //倒霉店主钱没带够就去买宠物了
                try {
                        BanGDreamPetShop.buyNewAnimal(Uika);
                        BanGDreamPetShop.buyNewAnimal(Lisa);
                        BanGDreamPetShop.buyNewAnimal(Tomorin);
                } catch (RuntimeException E) {
                        System.out.println("您的余额不足\n");
                }

                //而且店主一只宠物也没买到
                if (!BanGDreamPetShop.isOpening()) {
                        System.out.println("很遗憾通知各位顾客 小店由于店主今天没买到宠物提前歇业了（悲\n");
                        BanGDreamPetShop.closeShop();
                        return;
                }

                //顾客来了
//                BanGDreamPetShop.entertainCustomer(Sakiko, Uika);
//                BanGDreamPetShop.entertainCustomer(Yukina, Lisa);
//                BanGDreamPetShop.entertainCustomer(Rikki, Tomorin);

                //顾客找不到心仪的宠物
                try {
                        BanGDreamPetShop.entertainCustomer(Sakiko, Uika);
                } catch (AnimalNotFoundException E) {
                        System.out.println("不好意思~我们这里可能没有您想要的宠物\n");
                }
                try {
                        BanGDreamPetShop.entertainCustomer(Yukina, Lisa);
                        BanGDreamPetShop.entertainCustomer(Sayo, Lisa);
                } catch (AnimalNotFoundException E) {
                        System.out.println("不好意思~我们这里可能没有您想要的宠物\n");
                }
                try {
                        BanGDreamPetShop.entertainCustomer(Rikki, Tomorin);
                } catch (AnimalNotFoundException E) {
                        System.out.println("不好意思~我们这里可能没有您想要的宠物\n");
                }

                //顾客来晚了 宠物们都找到了新家
                try {
                        BanGDreamPetShop.entertainCustomer(Sakiko, Uika);
                        BanGDreamPetShop.entertainCustomer(Yukina, Lisa);
                        BanGDreamPetShop.entertainCustomer(Rikki, Tomorin);
                } catch (AnimalNotFoundException E) {
                        System.out.println("不好意思~我们的宠物已经卖完了\n");
                }

                //下班
                System.out.println("It's time to go home.");
                BanGDreamPetShop.closeShop();
        }
}

//写在Test类最后的一些疑问 由于找不到宠物和宠物卖完都抛出了同样的异常类 所以我要是想分开两种情况是不是要自己再加额外的判断或者写一个新的异常类？（思考