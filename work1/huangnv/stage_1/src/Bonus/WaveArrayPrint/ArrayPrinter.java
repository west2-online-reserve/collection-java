package Bonus.WaveArrayPrint;

public class ArrayPrinter {
    private final TurnLock turnLock = new TurnLock(true);

    private void printArrayOnTurn(boolean isMyTurn, int[] array) {
        for (int i = 0; i < array.length; i++) {
            synchronized (turnLock) {
                while (turnLock.isFirstTurn() != isMyTurn) {
                    try {
                        turnLock.wait(); // 注意：对 lock 调用 wait
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                System.out.print(array[i] + " ");
                turnLock.setFirstTurn(!isMyTurn);
                turnLock.notifyAll();
            }
        }
    }

    public void printInterleavedArrays(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            System.out.println("不等长，退出");
            return;
        }

        Thread t1 = new Thread(() -> printArrayOnTurn(true, array1));
        Thread t2 = new Thread(() -> printArrayOnTurn(false, array2));
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        int[] array1 = {1, 3, 5, 7};
        int[] array2 = {2, 4, 6, 8};
        new ArrayPrinter().printInterleavedArrays(array1, array2);
    }
}
