import java.util.Scanner;
import java.util.Arrays;
//javase基础部分
public class Hello{
    public static void main(String[] args){
        int[][] ns = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 }
        };
        System.out.println(Arrays.deepToString(ns));
        int [] arr=new int[10];
        for (int i=0;i<10;i++){
            arr[i]=i+1;
        }
        for (int i=0;i<10;i++){
            System.out.println(arr[i]);
        }
        int x=1100;
        String s1="HELLO".toLowerCase();
        String s2="hello";
        //判断引用的内容相同
        if(s1!=null && s1.equals(s2)){
            System.out.println("the same");
        }
        System.out.println(x);
        Scanner scanner = new Scanner(System.in); // 创建Scanner对象
        System.out.print("Input your name: "); // 打印提示
        String name = scanner.nextLine(); // 读取一行输入并获取字符串
        System.out.print("Input your age: "); // 打印提示
        int age = scanner.nextInt(); // 读取一行输入并获取整数
        System.out.printf("Hi, %s, you are %d\n", name, age); // 格式化输出
        int[] nss = { 1, 1, 2, 3, 5, 8 };
        for (int n : nss) {
            System.out.print(n + ", ");
        }
    }
}