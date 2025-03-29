package Bonus_01;

public class PrintArrays {
    //接收两个数组并使用多线程交替输出
    public static void printArrays(int[] arr1, int[] arr2) throws InterruptedException {
        //创建线程t1负责输出arr1
        Thread t1 = new Thread(() -> Out.out (arr1));

        //创建线程t2负责输出arr2
        Thread t2 = new Thread(() -> Out.out (arr2));

        //启动线程
        t1.start();
        t2.start();

        //等待线程执行完毕
        t1.join();
        t2.join();
    }
}
