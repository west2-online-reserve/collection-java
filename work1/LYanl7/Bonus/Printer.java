public class Printer {

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 9};
        int[] b = {2, 4, 6, 8, 10};
        print(a, b);
    }

    public static void print(int[] a, int[] b) {
        Object lock = new Object();
        boolean [] turnA = {true};

        Thread threadA = new Thread(() -> {
            for (int i = 0; i < a.length; i++) {
                synchronized (lock) {
                    while (!turnA[0]) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print(a[i]);
                    turnA[0] = false;
                    lock.notify();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            for (int i = 0; i < b.length; i++) {
                synchronized (lock) {
                    while (turnA[0]) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.print(b[i]);
                    turnA[0] = true;
                    lock.notify();
                }
            }
        });

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
