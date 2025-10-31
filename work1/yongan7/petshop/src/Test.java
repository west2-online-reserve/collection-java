public class Test {
    public static void main(String[] args) {
        //  创建一个宠物店实例
        MyAnimalShop shop = new MyAnimalShop(1000);
        System.out.println("宠物店开张啦！初始资金: " + 1000);

        //  测试买入动物
        System.out.println("\n--- 测试买入动物 ---");
        try {
            shop.buyAnimal(new ChineseRuralDog("旺财", 2, "公", true));
            shop.buyAnimal(new Cat("咪咪", 1, "母"));
            shop.buyAnimal(new Rabbit("小白", 1, "公"));
            // 测试余额不足
        } catch (InsufficientBalanceException e) {
            System.err.println("捕获到异常: " + e.getMessage());
        }

        System.out.println("当前店内动物: " + shop.getAnimalList());

        //  测试招待顾客
        System.out.println("\n--- 测试招待顾客 ---");
        try {
            Customer customer1 = new Customer("张三");
            shop.treatCustomer(customer1);

            Customer customer2 = new Customer("李四");
            shop.treatCustomer(customer2);
            
            // 同一个顾客再次光临
            shop.treatCustomer(customer1);

            // 测试动物卖完
        } catch (AnimalNotFoundException e) {
            System.err.println("捕获到异常: " + e.getMessage());
        }
        
        System.out.println("当前店内动物: " + shop.getAnimalList());

        // 歇业
        shop.closeShop();

        // 测试Bonus功能
        System.out.println("\n--- 测试Bonus功能 ---");
        
        // 多线程交替输出
        System.out.println("测试多线程交替输出:");
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};
        BonusFeatures.printAlternately(arr1, arr2);
        
        // 等待线程执行完毕
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n\n测试邮箱格式验证:");
        // 邮箱格式验证
        String email1 = "test@example.com";
        String email2 = "invalid-email";
        System.out.println("邮箱 " + email1 + " 是否合法? " + BonusFeatures.isEmailValid(email1));
        System.out.println("邮箱 " + email2 + " 是否合法? " + BonusFeatures.isEmailValid(email2));
    }
}