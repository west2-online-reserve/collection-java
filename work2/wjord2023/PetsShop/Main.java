import Animal.Cat;
import Animal.ChinesePastoralDogs;
import Shop.MyAnimalShop;
import Shop.ThreeInfo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author  wjord
 */

public class Main {
    public static void main(String[] args) {
        LinkedList<Object> listOfAnimal = new LinkedList<>();
        ChinesePastoralDogs dog1 = new ChinesePastoralDogs("小花", 3, "boy", "傻");
        ChinesePastoralDogs dog2 = new ChinesePastoralDogs("大胖", 5, "girl", "胖");
        Cat cat1 = new Cat("小乖", 2, "girl", "乖");
        listOfAnimal.add(dog1);
        listOfAnimal.add(dog2);
        listOfAnimal.add(cat1);

        // 初始化商店
        HashMap<String, ThreeInfo> listOfCustomer = new HashMap<>();
        MyAnimalShop myAnimalShop = new MyAnimalShop(300, listOfAnimal, listOfCustomer, true);


        /* 以下是我在chatGPT上学会的操作，并与我要完成的任务相结合写出的代码
         这个操作非常牛逼，可以一边进行顾客到店的倒计时操作，一边访问basicPage
         */

        // 创建一个ScheduledExecutorService，用于周期性地执行任务
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        // 创建输入任务
        Runnable inputTask = myAnimalShop::basicPage;

        // 创建倒计时任务
        Runnable countdownTask = () -> {
            if (myAnimalShop.isOpen()) {
                myAnimalShop.setCustomerArrive(false);
                Random random = new Random();
                // 顾客会在开店时以一个随机的时间进入商店
                for (int r = random.nextInt(5) + 2; r >= 0; r--) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (r == 0) {
                        if (myAnimalShop.isOpen()) {
                            System.out.println("有顾客到店！");
                            myAnimalShop.setCustomerArrive(true);
                        }
                        // 为防止已经关店但程序还在运行这里再做一个判断
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                myAnimalShop.setCustomerArrive(false);
            }

        };

        // 周期性地执行输入任务（每1秒执行一次）
        executorService.scheduleAtFixedRate(inputTask, 0, 1, TimeUnit.SECONDS);

        // 周期性地执行倒计时任务（每2秒执行一次）
        executorService.scheduleAtFixedRate(countdownTask, 0, 2, TimeUnit.SECONDS);

    }


}