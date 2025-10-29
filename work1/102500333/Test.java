import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
      
        MyAnimalShop shop = new MyAnimalShop(1000.0);

       
        try {
            shop.buyAnimal(new ChinesePastoralDog("大黄", 2, "公", true));
            shop.buyAnimal(new Cat("小白", 1, "母"));
            shop.buyAnimal(new Parrot("彩彩", 1, "公", 150.0, "彩色"));
            
            shop.buyAnimal(new Parrot("金刚", 3, "公", 1000.0, "蓝色"));
        } catch (InsufficientBalanceException e) {
            System.out.println("买入失败：" + e.getMessage());
        }
      
        LocalDate today = LocalDate.now();
        try {
            shop.serveCustomer(new Customer("张三", 1, today));
            shop.serveCustomer(new Customer("李四", 2, today));
           
            shop.serveCustomer(new Customer("王五", 1, today));
        } catch (AnimalNotFoundException e) {
            System.out.println("招待客户失败：" + e.getMessage());
        }

        shop.closeShop(today);

        
        System.out.println("\n===== Bonus方法测试 =====");

        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};
        System.out.println("多线程交替输出数组结果：");
        alternatePrintArrays(arr1, arr2);

        String[] emails = {"test@163.com", "invalid-email", "user@qq.cn", "123@.com"};
        for (String email : emails) {
            System.out.println("邮箱 '" + email + "' 格式合法？" + isEmailValid(email));
        }
    }

    public static void alternatePrintArrays(int[] arr1, int[] arr2) {
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                for (int num : arr1) {
                    System.out.print(num + " ");
                    lock.notify();                     try {
                        lock.wait();                     } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                lock.notify();、            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                for (int num : arr2) {
                    System.out.print(num + " ");
                    lock.notify(); 
                    try {
                        lock.wait(); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                lock.notify(); 
            }
        }).start();
    }


    public static boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return email.matches(regex);
    }
}
