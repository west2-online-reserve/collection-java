public class ThreadToPrint extends Thread {
    int[]arr;
    public ThreadToPrint(int[] arr) {
        this.arr = arr;
    }
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(this.getName()+": "+arr[i]);
        }
    }
}
