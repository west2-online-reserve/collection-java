public class ArrayAlternatePrinter {
    private static final Object lock = new Object();
    private static boolean isFirstArrayTurn = true;

    /**
     * 多线程交替输出两个int数组的元素，输入不合法返回false
     * @param arr1 第一个数组
     * @param arr2 第二个数组
     * @return 输入合法且执行成功返回true，否则返回false
     */
    public static boolean printAlternately(int[] arr1, int[] arr2) {
        // 校验输入合法性：数组为null则返回false
        if (arr1 == null || arr2 == null) {
            return false;
        }
        int minLength = Math.min(arr1.length, arr2.length);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < minLength; i++) {
                synchronized (lock) {
                    while (!isFirstArrayTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.print(arr1[i] + " ");
                    isFirstArrayTurn = false;
                    lock.notify();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < minLength; i++) {
                synchronized (lock) {
                    while (isFirstArrayTurn) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.print(arr2[i] + " ");
                    isFirstArrayTurn = true;
                    lock.notify();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false; // 线程被中断时返回false
        }
        return true; // 正常执行完成返回true
    }
    /*test*/
    public static void main(String[] args) {
        // 测试合法输入
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};
        System.out.print("合法输入输出：");
        boolean result1 = printAlternately(arr1, arr2);
        System.out.println("\n执行结果：" + result1); // 输出true

        // 测试非法输入（null数组）
        int[] arr3 = null;
        int[] arr4 = {2, 4};
        System.out.print("非法输入输出：");
        boolean result2 = printAlternately(arr3, arr4);
        System.out.println("\n执行结果：" + result2); // 输出false
    }
}