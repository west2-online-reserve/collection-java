import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        int n1 = input / 100;
        int n2 = (input - n1 * 100) / 10;
        int n3 = (input - n1 * 100 - n2 * 10);
        if (n3 != 0){
            System.out.print(n3);
        }
        if (n2 != 0 | n3 != 0){
            System.out.print(n2);
        }
        if (n1 != 0){
            System.out.print(n1);
        }
    }
}