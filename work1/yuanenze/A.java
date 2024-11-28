package petStore;

import java.util.Arrays;

public class A extends Thread {
    private static int count = 0;
    private int[] arr1;

    public A(int[] arr1) {
        this.arr1 = Arrays.copyOf(arr1, arr1.length);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (Temp.lock) {
                if (count == this.arr1.length) {
                    break;
                } else {
                    if (Temp.flag) {
                        System.out.println("A 数组输出 ：" + this.arr1[count]);
                        count++;
                        Temp.flag = false;
                        Temp.lock.notifyAll();
                    } else {
                        try {
                            Temp.lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }


                    }
                }

            }

        }
    }
}


