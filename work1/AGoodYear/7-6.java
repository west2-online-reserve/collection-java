import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double n1 = scan.nextDouble();
        int n2 = scan.nextInt();
        String n3 = scan.next();
        double n4 = scan.nextDouble();
        System.out.printf(n3 + " " + n2 + " %.2f %.2f", n1, n4);
    }
}