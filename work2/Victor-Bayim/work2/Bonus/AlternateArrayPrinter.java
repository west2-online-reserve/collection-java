public class AlternateArrayPrinter {
    private int[] arr1;
    private int[] arr2;
    private Object lock = new Object();
    private volatile int currentIndex = 0;

    public AlternateArrayPrinter(int[] arr1, int[] arr2) {
        this.arr1 = arr1;
        this.arr2 = arr2;
    }

    public void printAlternately() {
        Thread thread1 = new Thread(this::printArray1);
        Thread thread2 = new Thread(this::printArray2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void printArray1() {
        for (int value : arr1) {
            synchronized (lock) {
                while (currentIndex % 2 != 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.print(value + " ");
                currentIndex++;
                lock.notifyAll();
            }
        }
    }

    private void printArray2() {
        for (int value : arr2) {
            synchronized (lock) {
                while (currentIndex % 2 == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                System.out.print(value + " ");
                currentIndex++;
                lock.notifyAll();
            }
        }
    }
}
