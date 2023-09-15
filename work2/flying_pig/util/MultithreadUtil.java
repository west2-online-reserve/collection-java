package util;

public class MultithreadUtil {
    private int[] arr1;
    private int[] arr2;

    MultithreadUtil(int[] arr1, int[] arr2) {
        this.arr1 = arr1;
        this.arr2 = arr2;
    }

    public void printArrays() {
        Thread thread1 = new Thread(() -> {
            for (int i : arr1) {
                System.out.print(i + " ");
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i : arr2) {
                System.out.print(i + " ");
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};

        MultithreadUtil multithreadUtil = new MultithreadUtil(arr1, arr2);
        multithreadUtil.printArrays();
    }
}
