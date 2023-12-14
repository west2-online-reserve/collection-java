public class Bonus1 {
    private int[] arr1;
    private int[] arr2;

    public static void main(String[] args) throws InterruptedException {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};
        Bonus1 output = new Bonus1(arr1, arr2);
        output.printArray();
    }

    public Bonus1(int[] arr1, int[] arr2) {
        this.arr1 = arr1;
        this.arr2 = arr2;
    }

    public void printArray() throws InterruptedException {
        Object lock = new Object();
        Thread t1 = new Thread(new PrintThread(arr1, lock));
        Thread t2 = new Thread(new PrintThread(arr2, lock));

        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }

    private class PrintThread implements Runnable {
        private int[] array;
        private Object lock;

        public PrintThread(int[] array, Object lock) {
            this.array = array;
            this.lock = lock;
        }

        @Override
        public void run() {
            synchronized (lock) {
                for (int i : array) {
                    System.out.print(i + " ");
                    lock.notify();

                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                lock.notify();
            }
        }
    }

}
