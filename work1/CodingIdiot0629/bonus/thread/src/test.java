import com.github.lpx.thread_demo.MyThread;


public class test {
    public static void main(String[] args) {
        int arr1[] = {1, 3, 5, 7, 9};
        int arr2[] = {2, 4, 6, 8, 10};
        turnPrint(arr1, arr2);
    }

    public static void turnPrint(int[] arr1, int[] arr2) {
        String lock = "奶龙666";
        new MyThread("线程1", arr1, lock, 0, 0).start();
        new MyThread("线程2", arr2, lock, 0, 1).start();
    }
}
