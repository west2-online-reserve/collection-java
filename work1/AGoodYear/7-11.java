import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int water = scan.nextInt();
        double money = 0;
        if (water <= 15){
            money = (double) (water * 4) / 3;
        } else {
            money = 2.5 * water - 17.5;
        }
        System.out.printf("%.2f", money);
    }
}