import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MutilThread implements Runnable{
    private  final int myTurn;
    private  final int otherTurn;
    private  final ArrayList<Integer> arr;
    public MutilThread(ArrayList<Integer> arr,int myTurn,int otherTurn) {
        this.arr = arr;
        this.myTurn=myTurn;
        this.otherTurn = otherTurn;
    }
    @Override
    public void run() {
        for(int number:this.arr){
            synchronized (AlternatingState.Lock) {
                while (AlternatingState.currentTurn != this.myTurn) {
                    try {
                        AlternatingState.Lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.print(number + ",");
                AlternatingState.currentTurn = otherTurn;
                AlternatingState.Lock.notifyAll();
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("请输入第一个数组中的元素，用','隔开");
        Scanner sc = new Scanner(System.in);
        String numString = sc.nextLine();
        List<Integer> numList;
        numList = Arrays.stream(numString.split(","))
                .map(Integer::parseInt)
                .toList();
        ArrayList<Integer> arr1 = new ArrayList<>(numList);

        System.out.println("请输入第二个数组中的元素，用','隔开");
        sc = new Scanner(System.in);
        numString = sc.nextLine();

        numList = Arrays.stream(numString.split(","))
                .map(Integer::parseInt)
                .toList();
        ArrayList<Integer> arr2 = new ArrayList<>(numList);
        //两个线程创建，需要设置锁来进行交替运行
        Thread thread1 = new Thread(new MutilThread(arr1,1,2),"thread1");
        Thread thread2 = new Thread(new MutilThread(arr2,2,1),"thread2");
        thread1.start();
        thread2.start();
    }
}

class AlternatingState{
    public static final Object Lock = new Object();
    public static int currentTurn =1;//表示当前哪个线程拥有执行权
}
