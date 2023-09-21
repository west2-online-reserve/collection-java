import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        int n1 = input / 16;
        int n2 = input - n1 * 16;
        if (n1 != 0){
            System.out.print(n1);
        }
        System.out.print(n2);
    }
}