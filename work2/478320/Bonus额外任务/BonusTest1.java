package com.huayu.work02;

/**
 * BonusTest1类表示对最终结果进行测试
 *
 * 该类包含了接收数组合并并排序的方法和测试的具体流程
 * @author 余思衡
 * @date 2023/10/27
 */
public class BonusTest1 {
    /**
     * 合并两个数组，并进行排序
     *
     * @param copied1
     * @param copied2
     * @param acceptArray
     * @return null
     */
    public static void accept(int[] copied1, int[] copied2, int[] acceptArray) {
        System.arraycopy(copied1, 0, acceptArray, 0, copied1.length);
        System.arraycopy(copied2, 0, acceptArray, copied1.length, copied2.length);
        for (int i = 0; i < acceptArray.length - 1; i++) {
            boolean flag = false;
            for (int j = 0; j < acceptArray.length - 1; j++) {
                if (acceptArray[j] > acceptArray[j + 1]) {
                    int a = 0;
                    a = acceptArray[j];
                    acceptArray[j] = acceptArray[j + 1];
                    acceptArray[j + 1] = a;
                    flag = true;
                }
            }
            if (flag == false) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 5, 7, 9};
        int[] b = {2, 4, 6, 8, 10};
        int[] c = new int[a.length + b.length];
        accept(a, b, c);
        BonusMyThread bonusMyThread1 = new BonusMyThread(c);
        BonusMyThread bonusMyThread2 = new BonusMyThread(c);
        bonusMyThread1.setName("线程1");
        bonusMyThread2.setName("线程2");
        bonusMyThread1.start();
        bonusMyThread2.start();


    }
}
