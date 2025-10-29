public class AlternateOutput {
    private static final Object lock = new Object();
    private static boolean isArray1Turn = true;

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7 ,9};
        int[] arr2 = {2, 4, 6, 8, 10};
        printArrays(arr1, arr2);
    }

    public static void printArrays(int[] arr1, int[] arr2) {
        Thread thread1 = new Thread(() -> {
            for (int i : arr1) {
                synchronized (lock) {
                    while (!isArray1Turn){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print(i + " ");
                    isArray1Turn = false;
                    lock.notify();
                }
            }
        }, "Thread1");
        Thread thread2 = new Thread(() -> {
            for (int i : arr2) {
                synchronized (lock) {
                    while (isArray1Turn){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print(i + " ");
                    isArray1Turn = true;
                    lock.notify();
                }
            }
        }, "Thread2");
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}