package Bonus;

public class PrintAns {

    private static Object lock = new Object(); // 创建一个对象，让两个线程共同使用同一把锁
    private static boolean isArrayTrue = true; //当 isArrayTrue 为 true 时第一个进程

    public static void printAns(int[] arr1, int[] arr2) {
        Thread r1 = new Thread(new MyRunnable(arr1, true));
        Thread r2 = new Thread(new MyRunnable(arr2, false));

        r1.start();
        r2.start();

        try {
            r1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            r2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class MyRunnable implements Runnable {

        private int[] arr;
        private boolean isArray;

        MyRunnable(int[] arr, boolean isArray) {
            this.arr = arr;
            this.isArray = isArray;
        }

        @Override
        public void run() {
            for (int i : arr) {
                synchronized (lock) {
                    // 对线程进行筛选
                    while (isArray && !isArrayTrue || !isArray && isArrayTrue) {
                        try {
                            lock.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    System.out.print(i + " ");

                    isArrayTrue = !isArrayTrue;
                    lock.notifyAll();
                }
            }
        }
    }
}
