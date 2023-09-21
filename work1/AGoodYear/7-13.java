import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double open, high, low, close;
        Scanner scanner = new Scanner(System.in);
        open = scanner.nextDouble();
        high = scanner.nextDouble();
        low = scanner.nextDouble();
        close = scanner.nextDouble();
        boolean isLower, isUpper;
        isLower = isUpper = false;
        if (close < open){
            System.out.print("BW-Solid");
        } else if (close > open){
            System.out.print("R-Hollow");
        } else if (close == open){
            System.out.print("R-Cross");
        }
        if (low < open && low < close){
            isLower = true;
        }
        if (high > open && high > close){
            isUpper = true;
        }
        if (isLower == true && isUpper == false){
            System.out.print(" with Lower Shadow");
        } else if (isLower == false && isUpper == true){
            System.out.print(" with Upper Shadow");
        } else if (isLower == true && isUpper == true){
            System.out.print(" with Lower Shadow and Upper Shadow");
        }
    }
}