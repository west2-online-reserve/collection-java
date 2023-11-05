package com.west2.check02.bonus;

/***
 * @author yuyu
 */
class Accept {
    private final String string = new String("同步监视器");
    // 随便创建的一个对象，用来充当同步监视器器

    public void acceptNumber(int[] arr1, int[] arr2) {
        int[] ints = new int[arr1.length + arr2.length];
        int sum = 0;
        // 下面就考虑了其中一个数组比另外一个数组多一个元素的情况
        // 要是多俩个及以上，那就不叫交替输出了
        if (arr1.length == arr2.length) {
            for (int i = 0; i < arr1.length; i++) {
                ints[sum] = arr1[i];
                sum++;
                ints[sum] = arr2[i];
                sum++;
            }
        } else if (arr1.length > arr2.length) {
            for (int i = 0; i < arr2.length; i++) {
                ints[sum] = arr1[i];
                sum++;
                ints[sum] = arr2[i];
                sum++;
            }
            ints[sum] = arr1[arr1.length - 1];
        } else {
            for (int i = 0; i < arr1.length; i++) {
                ints[sum] = arr1[i];
                sum++;
                ints[sum] = arr2[i];
                sum++;
            }
            ints[sum] = arr2[arr2.length - 1];

        }

        CreateThread createThread = new CreateThread(ints, string);
        CreateThread createThread1 = new CreateThread(ints, string);
        createThread.start();
        createThread1.start();
    }
}

class CreateThread extends Thread {
    static int i = 0;
    // 保证俩个线程操作时对同一个量进行修改
    private String string;
    // 为了同步监视器

    private int[] arr;

    public CreateThread(int[] arr, String s) {
        // 通过构造器将同步监视器传入，保证同步监视器的相同
        this.arr = arr;
        this.string = s;
    }

    @Override
    public void run() {

        while (true) {

            synchronized (string) {
                string.notify();
                System.out.println(arr[i]);
                i++;
                try {
                    string.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (i == arr.length) {
                    string.notifyAll();
                    break;

                }
            }
        }
    }


}





