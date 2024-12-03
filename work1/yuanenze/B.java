package petStore;

import java.util.Arrays;

public class B extends Thread {
    private static int count = 0;
    private int[] arr1;

    public B(int[] arr1) {
        this.arr1 = Arrays.copyOf(arr1, arr1.length);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (Temp.lock) {
                if (count == this.arr1.length) {
                    Temp.flag = true;
                    break;
                } else {
                    if (!Temp.flag) {
                        System.out.println("B 数组输出 ：" + this.arr1[count]);
                        count++;
                        Temp.flag = true;
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
