public class MyThread extends Thread{
    int[] arr;
    @Override
    public void run() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+"\t");
        }
    }
}
