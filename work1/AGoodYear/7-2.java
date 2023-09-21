import  java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int now = scanner.nextInt();
        int timePassed = scanner.nextInt();
        int hour = now / 100;
        int min = now - hour * 100;
        int passedHour, passedMin;
        passedHour = timePassed / 60;
        passedMin = timePassed - passedHour * 60;
        hour += passedHour;
        min += passedMin;
        if (min >= 60){
            hour++;
            min -= 60;
        }
        if (min < 0){
            hour--;
            min = 60 + passedMin;
        }
        if (min == 0){
            System.out.println(hour + "00");
        }
        else{
            System.out.println(hour + "" + min);
        }
    }
}