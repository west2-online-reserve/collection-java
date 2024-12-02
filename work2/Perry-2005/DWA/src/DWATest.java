import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DWATest extends Core{
    public static void main(String[] args) throws IOException {
        Core Core = new Core();
        Scanner scan = new Scanner(System.in);
        System.out.print("请输入你想查询的运动员/决赛结果/具体赛况: ");
        String index = scan.nextLine();
        switch (index){
            case "players":
                File file = new File("athletes.json");
                Core.displayAllPlayersInfo();
                break;
            case "result women 1m springboard":
                File file1 = new File("Women1mSpringboard.json");
                Core.displayContest(file1);
                break;
            case "result women 3m springboard":
                File file2 = new File("Women3mSpringboard.json");
                Core.displayContest(file2);
                break;
            case "result women 10m platform":
                File file3 = new File("Women10mPlatform.json");
                Core.displayContest(file3);
                break;
            case "result women 3m synchronised ":
                File file4 = new File("Women3mSynchronised.json");
                Core.displayContest(file4);
                break;
            case "result women 10m synchronised ":
                File file5 = new File("Women10mSynchronised.json");
                Core.displayContest(file5);
                break;
            case "result men 1m springboard":
                File file6 = new File("Men1mSpringboard.json");
                Core.displayContest(file6);
                break;
            case "result men 3m springboard":
                File file7 = new File("Men3mSpringboard.json");
                Core.displayContest(file7);
                break;
            case "result men 10m platform":
                File file8 = new File("Men10Platform.json");
                Core.displayContest(file8);
                break;
            case "result men 3m synchronised ":
                File file9 = new File("Men3mSynchronised.json");
                Core.displayContest(file9);
                break;
            case "result men 10m synchronised ":
                File file10 = new File("Men10mSynchronised.json");
                Core.displayContest(file10);
                break;
            case "result women 1m springboard detail":
                File file11 = new File("Women1mSpringboard.json");
                Core.displayContestsDetailed(file11);
                break;
            case "result women 3m springboard detail":
                File file12 = new File("Women3mSpringboard.json");
                Core.displayContestsDetailed(file12);
                break;
            case "result women 10m platform detail":
                File file13 = new File("Women10mPlatform.json");
                Core.displayContestsDetailed(file13);
                break;
            case "result women 3m synchronised detail":
                File file14 = new File("Women3mSynchronised.json");
                Core.displayContestSynchronised(file14);
                break;
            case "result women 10m synchronised detail":
                File file15 = new File("Women10mSynchronised.json");
                Core.displayContestSynchronised(file15);
                break;
            case "result men 1m springboard detail":
                File file16 = new File("Men1mSpringboard.json");
                Core.displayContestsDetailed(file16);
                break;
            case "result men 3m springboard detail":
                File file17 = new File("Men3mSpringboard.json");
                Core.displayContestsDetailed(file17);
                break;
            case "result men 10m platform detail":
                File file18 = new File("Men10Platform.json");
                Core.displayContestsDetailed(file18);
                break;
            case "result men 3m synchronised detail":
                File file19 = new File("Men3mSynchronised.json");
                Core.displayContestSynchronised(file19);
                break;
            case "result men 10m synchronised detail":
                File file20 = new File("Men10mSynchronised.json");
                Core.displayContestSynchronised(file20);
                break;
            default:
                if (index.contains("result")){
                    writeError_2();
                }else {
                    writeError_1();

                }
        }


    }
}
