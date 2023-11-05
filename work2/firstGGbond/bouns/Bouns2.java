package bouns;
import java.util.Scanner;
public class Bouns2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.nextLine();
        String [] arr1 = n.split(" ");
        String n2 = sc.nextLine();
        String [] arr2 = n2.split(" ");
        test(arr1,arr2);
    }
    public static void test(String[]a1,String[]a2){
        for(int i =0;i<a1.length;i++){
            System.out.print(a1[i]+" "+a2[i]+" ");
        }
    }
}
