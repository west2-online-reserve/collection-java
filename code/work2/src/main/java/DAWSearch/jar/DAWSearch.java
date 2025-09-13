package DAWSearch.jar;

import java.util.Scanner;

public class DAWSearch {
    public static void main(String[] args) {
        String contentName;
        Lib application = new Lib();
        Scanner scanner=new Scanner(System.in);
        System.out.println("输入你想要展示的内容：");
        contentName=scanner.nextLine();
        while (!contentName.equals("exit")) {
            switch (contentName) {
                case "players":
                    application.inputCommand("players");
                    application.displayAllPlayersInfo("D:\\java\\code\\work2\\src\\main\\java\\Data\\AthleteInfo\\Athletes.json");
                    break;
                case "result women 1m springboard":
                    application.inputCommand("result women 1m springboard");
                    application.displayFinalResults("D:\\java\\code\\work2\\src\\main\\java\\Data\\AthleteInfo\\Men 1m Springboard.json");
                    break;
                case "result women 3m springboard":
                    application.inputCommand("result women 3m springboard");
                case "result women 10m platform":
                    application.inputCommand("result women 10m platform");
                case "result women 3m synchronised":
                    application.inputCommand("result women 3m synchronised");
                case "result women 10m synchronised":
                    application.inputCommand("result women 10m synchronised");
                case "result men 1m springboard":
                    application.inputCommand("result men 1m springboard");
                case "result men 3m springboard":
                    application.inputCommand("result men 3m springboard");
                case "result men 10m platform":
                    application.inputCommand("result men 10m platform");
                case "result men 3m synchronised":
                    application.inputCommand("result men 3m synchronised");
                case "result men 10m synchronised":
                    application.inputCommand("result men 10m synchronised");
                case "result women 1m springboard detail":
                    application.inputCommand("result women 1m springboard detail");
                case "result women 3m springboard detail":
                    application.inputCommand("result women 3m springboard detail");
                case "result women 10m platform detail":
                    application.inputCommand("result women 10m platform detail");
                case "result women 3m synchronised detail":
                    application.inputCommand("result women 3m synchronised detail");
                case "result women 10m synchronised detail":
                    application.inputCommand("result women 10m synchronised detail");
                case "result men 1m springboard detail":
                    application.inputCommand("result men 1m springboard detail");
                case "result men 3m springboard detail":
                    application.inputCommand("result men 3m springboard detail");
                case "result men 10m platform detail":
                    application.inputCommand("result men 10m platform detail");
                case "result men 3m synchronised detail":
                    application.inputCommand("result men 3m synchronised detail");
                case "result men 10m synchronised detail":
                    application.inputCommand("result men 10m synchronised detail");
                default:
                    application.inputCommand("N/A");
            }
            System.out.println("输入你想要展示的内容：");
            contentName=scanner.nextLine();
        }
        scanner.close();
    }
}
