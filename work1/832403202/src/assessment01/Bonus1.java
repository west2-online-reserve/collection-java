package assessment01;

public class Bonus1 {
    // 接收两个int数组，使用多线程交替输出元素
    public static void printAlternately(int[] arr1, int[] arr2) {
        // 校验输入数组合法性
        if (arr1 == null || arr2 == null) {
            System.out.println("输入数组不能为null");
            return;
        }

        // 取两个数组的最小长度作为输出上限（避免越界）
        int length = Math.min(arr1.length, arr2.length);
        if (length == 0) {
            System.out.println("数组长度不能为0");
            return;
        }

        // 共享锁对象（用于线程同步）
        Object lock = new Object();
        // 标志位：true表示线程1（输出arr1）执行，false表示线程2（输出arr2）执行
        boolean[] isThread1Turn = {true};

        // 线程1：负责输出arr1的元素
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < length; i++) {
                synchronized (lock) {
                    try {
                        // 若不是线程1的回合，等待
                        while (!isThread1Turn[0]) {
                            lock.wait();
                        }
                        // 输出arr1的第i个元素
                        System.out.print(arr1[i] + " ");
                        // 切换标志位，轮到线程2执行
                        isThread1Turn[0] = false;
                        // 唤醒等待的线程2
                        lock.notify();
                    } catch (InterruptedException e) {
                        // 处理中断异常
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }, "Thread-arr1");

        // 线程2：负责输出arr2的元素
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < length; i++) {
                synchronized (lock) {
                    try {
                        // 若不是线程2的回合，等待
                        while (isThread1Turn[0]) {
                            lock.wait();
                        }
                        // 输出arr2的第i个元素
                        System.out.print(arr2[i] + " ");
                        // 切换标志位，轮到线程1执行
                        isThread1Turn[0] = true;
                        // 唤醒等待的线程1
                        lock.notify();
                    } catch (InterruptedException e) {
                        // 处理中断异常
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }, "Thread-arr2");

        // 启动线程
        thread1.start();
        thread2.start();

        // 主线程等待两个子线程执行完毕
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        // 测试案例
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};
        printAlternately(arr1, arr2); // 输出：1 2 3 4 5 6 7 8 9 10
    }
}
