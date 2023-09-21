import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int A, B, sum;
        A = scan.nextInt();
        B = scan.nextInt();
        sum = 0;
        int lineNum = 0;
        for (int i=A; i<=B; i++){
            if (lineNum == 0) {
                System.out.printf("%5d", i);
                lineNum++;
                sum += i;
            } else if (lineNum < 5) {
                System.out.printf("%5d", i);
                lineNum++;
                sum += i;
            } else {
                System.out.print("\n");
                System.out.printf("%5d", i);
                lineNum = 1;
                sum += i;
            }
        }
            System.out.printf("\nSum = %d", sum);
    }
}