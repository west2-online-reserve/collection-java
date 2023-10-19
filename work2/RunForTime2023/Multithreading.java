public class Multithreading {
    public static void alternate(int[] array1, int[] array2) {
        Object lock = new Object();
        Thread a = new Thread(() -> {
            for (int x : array1) {
                synchronized (lock) {
                    System.out.printf("%d ", x);
                    try {
                        lock.wait();
                        lock.notifyAll();
                    } catch (InterruptedException e) {

                    }
                }
            }
            try {
                lock.notifyAll();
            } catch (IllegalMonitorStateException e) {

            }
        }
        );
        Thread b = new Thread(() -> {
            try {
                Thread.sleep(9);
            } catch (InterruptedException e) {
            }
            for (int x : array2) {
                synchronized (lock) {
                    System.out.printf("%d ", x);
                    try {
                        lock.notifyAll();
                        lock.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        );
        a.start();
        b.start();
    }

    public static void main(String[] argv) {
        int[] array1 = {1, 3, 5, 7, 9};
        int[] array2 = {2, 4, 6, 8, 10};
        alternate(array1, array2);
    }
}