package work2;

public class Bonus1 {
    protected int[] arr1;
    protected int[] arr2;
    protected Object lock = new Object();
    protected boolean printFirstArray = true;

    public void printArrays(int[] arr1, int[] arr2) {
        this.arr1 = arr1;
        this.arr2 = arr2;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr1.length; i++) {
                    synchronized (lock) {
                        while (!printFirstArray) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print(arr1[i] + " ");
                        printFirstArray = false;
                        lock.notifyAll();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr2.length; i++) {
                    synchronized (lock) {
                        while (printFirstArray) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.print(arr2[i] + " ");
                        printFirstArray = true;
                        lock.notifyAll();
                    }
                }
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};
        Bonus1 printer = new Bonus1();
        printer.printArrays(arr1, arr2);
    }
}
