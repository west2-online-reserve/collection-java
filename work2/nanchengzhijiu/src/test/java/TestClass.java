import org.FZU.Utils.ParseCommand;
import org.FZU.Utils.HttpUtil;
import org.FZU.Utils.CompetitionUrlConfig;
import org.FZU.pojo.HttpResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class TestClass {
    private HttpUtil httpUtil;
    private HttpResponse response;
    private String url;
    private ParseCommand command;
    private ArrayList<String> commands;
    @BeforeEach
    public void setUp(){
        httpUtil = new HttpUtil();
        response=new HttpResponse();
        command=new ParseCommand();
        commands=new ArrayList<>();
    }
    @Test
    public void displayAllPlayersInfoTest(){
        commands=new ArrayList<>();
        String target = "players";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("Full Name"));
        Assertions.assertTrue(results.getFirst().contains("Gender"));
        Assertions.assertTrue(results.getFirst().contains("Country"));
    }
    @Test
    public void displayResultsForEachEvenTest(){
        commands=new ArrayList<>();
        String target = "result women 1m springboard";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("Full Name"));
        Assertions.assertTrue(results.getFirst().contains("Rank"));
        Assertions.assertTrue(results.getFirst().contains("Score"));
    }
    @Test
    public void displayDetailForEachEvenAllTest(){
        commands=new ArrayList<>();
        String target = "result men 10m platform detail";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("Final Score"));
        Assertions.assertTrue(results.getFirst().contains("Preliminary Score"));
        Assertions.assertTrue(results.getFirst().contains("Semifinal Score"));
        Assertions.assertFalse(results.getFirst().contains("Final Score:*"));
        Assertions.assertFalse(results.getFirst().contains("Preliminary Score:*"));
        Assertions.assertFalse(results.getFirst().contains("Semifinal Score:*"));
    }
    @Test
    public void displayDetailForEachEvenOnlyFinalTest(){
        commands=new ArrayList<>();
        String target = "result men 10m synchronised detail";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("Final Score"));
        Assertions.assertFalse(results.getFirst().contains("Final Score:*"));
        Assertions.assertTrue(results.getFirst().contains("Preliminary Score:*"));
        Assertions.assertTrue(results.getFirst().contains("Semifinal Score:*"));
    }
    @Test
    public void displayDetailForEachEvenFinalAndPreliminaryTest(){
        commands=new ArrayList<>();
        String target = "result women 1m springboard detail";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("Final Score"));
        Assertions.assertFalse(results.getFirst().contains("Final Score:*"));
        Assertions.assertTrue(results.getFirst().contains("Preliminary Score"));
        Assertions.assertFalse(results.getFirst().contains("Preliminary Score:*"));
        Assertions.assertTrue(results.getFirst().contains("Semifinal Score:*"));
    }
    @Test
    public void ErrorTest(){
        commands=new ArrayList<>();
        String target = " ";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("Error"));
    }
    @Test
    public void Error2(){
        commands=new ArrayList<>();
        String target = "resultwomen";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("Error"));
    }
    @Test
    public void NATest(){
        commands=new ArrayList<>();
        String target = "result";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("N/A"));
    }
    @Test
    public void NATest2(){
        commands=new ArrayList<>();
        String target = "result women";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("N/A"));
    }
    @Test
    public void NATest3(){
        commands=new ArrayList<>();
        String target = "result Men 10M Synchronised";
        commands.add(target);
        url = CompetitionUrlConfig.findUrlByTarget(target);
        response=httpUtil.getUrl(url, "GET");
        ArrayList<String> results=command.parseAllCommand(commands,"output.txt");
        Assertions.assertTrue(results.getFirst().contains("N/A"));
    }
}
