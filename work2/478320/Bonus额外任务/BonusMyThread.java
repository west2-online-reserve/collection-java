package com.huayu.work02;

/**
 * BonusMyThread类表示我自定义的线程
 *
 * 该类包含了自定义线程的运作行式
 * @author 余思衡
 * @date 2023/10/27
 */
public class BonusMyThread extends Thread {
    // 需要被打印的数组的下标
    static int subscript = 0;
    int[] acceptArray;

    /**
     * 构造方法的作用是传递需要打印的数组供多个线程打印
     *
     * @param acceptArray
     */
    public BonusMyThread(int[] acceptArray) {
        this.acceptArray = acceptArray;

    }

    /**
     * run方法按数组下标从小到大顺序打印数组中的元素
     *
     * @return null
     */
    @Override
    public void run() {
        while (true) {
            synchronized (com.huayu.work02.BonusMyThread.class) {
                if (subscript == acceptArray.length) {
                    break;
                } else {
                    System.out.println(getName() + "---" + acceptArray[subscript]);
                    subscript++;
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
