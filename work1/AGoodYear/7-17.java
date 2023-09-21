import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int N, U, D;
        N = s.nextInt();
        U = s.nextInt();
        D = s.nextInt();
        int min = 0;
        while (true) {
            min++;
            N -= U;
            if (N > 0) {
                N += D;
                min++;
            } else {
                break;
            }
        }
        System.out.println(min);
    }
}