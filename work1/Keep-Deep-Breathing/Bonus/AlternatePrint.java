package Bonus;

public class AlternatePrint {

    private int flag = 0;

    private int index1 = 0, index2 = 0;
    private int[] arr1, arr2;

    public AlternatePrint(int[] arr1, int[] arr2) {
        this.arr1 = arr1;
        this.arr2 = arr2;
    }


    public synchronized void printArr1() {
        while (index1 < arr1.length) {

            while (flag != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(arr1[index1++] + " ");

            flag = 1;

            notify();
        }
    }


    public synchronized void printArr2() {
        while (index2 < arr2.length) {

            while (flag != 1) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(arr2[index2++] + " ");

            flag = 0;

            notify();
        }
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};
        AlternatePrint alternatePrint = new AlternatePrint(arr1, arr2);


        Thread thread1 = new Thread(() -> alternatePrint.printArr1());

        Thread thread2 = new Thread(() -> alternatePrint.printArr2());

        thread1.start();
        thread2.start();
    }
}