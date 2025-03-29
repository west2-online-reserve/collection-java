package Bonus_01;

public class Out {
    private static final Object lock = new Object();//锁对象,用于同步
    public static void out (int[] arr) {
        synchronized (lock) {
            for (int num : arr) {
                System.out.print(num + " ");
                lock.notify();//通知线程t2可以继续执行
                try {
                    if (num != arr[arr.length - 1]) {
                        lock.wait();//等待线程t2输出
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lock.notify();//唤醒线程t2,确保结束时它可以退出
        }
    }
}
