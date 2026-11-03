import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 两个数组交替输出
 用两个线程分别负责数组，共享变量turn控制轮到哪个数组输出
 较短数组的线程先结束时，另一个线程把剩下的元素输出完
 */

public class AlternatePrint{

    static class SharedState {
        final Object lock = new Object();
        int     turn  = 0;
        boolean aDone = false;
        boolean bDone = false;
    }

    static class ArrayPrinter extends Thread {
        private final int[]       data;
        private final SharedState state;
        private final boolean     isFirst;

        ArrayPrinter(String name, int[] data, SharedState state, boolean isFirst) {
            super(name);
            this.data    = data;
            this.state   = state;
            this.isFirst = isFirst;
        }

        private int myTurn()        { return isFirst ? 0 : 1; }
        private int otherTurn()     { return isFirst ? 1 : 0; }
        private boolean otherDone() { return isFirst ? state.bDone : state.aDone; }

        @Override
        public void run() {
            for (int v : data) {
                synchronized (state.lock) {
                    while (state.turn != myTurn() && !otherDone()) {
                        try {
                            state.lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    System.out.print(v + " ");
                    state.turn = otherTurn();
                    state.lock.notifyAll();
                }
            }

            synchronized (state.lock) {
                if (isFirst) state.aDone = true; else state.bDone = true;
                state.turn = otherTurn();
                state.lock.notifyAll();
            }
        }
    }

    public static void printAlternately(int[] arr1, int[] arr2) {
        SharedState state = new SharedState();
        Thread tA = new ArrayPrinter("A", arr1, state, true);
        Thread tB = new ArrayPrinter("B", arr2, state, false);
        tA.start();
        tB.start();
        try {
            tA.join();
            tB.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println();
    }

//先输入数组的长度再输入元素
    private static int[] readIntArrayWithLength(Scanner sc, String name) {
        while (true) {
            System.out.println("请输入数组" + name + "的元素个数：");
            if (!sc.hasNextLine()) {
                System.out.println("空数组");
                return new int[0];
            }
            String lenLine = sc.nextLine().trim();
            if (lenLine.isEmpty()) {
                System.out.println("个数不能为空，请重新输入。");
                continue;
            }
            int n;
            try {
                n = Integer.parseInt(lenLine);
            } catch (NumberFormatException e) {
                System.out.println("\"" + lenLine + "\" 不是整数，请重新输入。");
                continue;
            }
            if (n < 0) {
                System.out.println("个数不能为负数，请重新输入。");
                continue;
            }
            if (n == 0) {
                return new int[0];
            }

            System.out.println("请输入数组" + name + "的 " + n + " 个元素（空格或逗号分隔）：");
            if (!sc.hasNextLine()) {
                System.out.println("空数组");
                return new int[0];
            }
            String line = sc.nextLine().trim();
            String[] parts = line.split("[\\s,，;；]+");

            List<Integer> list = new ArrayList<>();
            boolean ok = true;
            for (String p : parts) {
                if (p.isEmpty()) continue;
                try {
                    list.add(Integer.parseInt(p));
                } catch (NumberFormatException e) {
                    System.out.println("无法识别的数字：\"" + p + "\"，请重新输入数组" + name + "。");
                    ok = false;
                    break;
                }
            }
            if (!ok) continue;

            int[] arr = new int[n];
            for (int i = 0; i < n; i++) arr[i] = list.get(i);
            return arr;
        }
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int[] arr1 = readIntArrayWithLength(sc, "1");
            int[] arr2 = readIntArrayWithLength(sc, "2");

            System.out.print("交替输出：");
            printAlternately(arr1, arr2);
        }
    }
}