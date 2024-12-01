import org.junit.Test;

import java.io.*;
import java.util.Map;

import static org.junit.Assert.*;

public class CoreModuleTest {

    @Test
    public void displayAllPlayersInfo() {
        CoreModule coreModule = new CoreModule();
        try (
                Writer wr = new FileWriter("src\\main\\testResources\\output.txt");
                BufferedWriter out = new BufferedWriter(wr);
        ) {
            coreModule.displayAllPlayersInfo(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void displayResultsForEachEvent() {
        CoreModule coreModule = new CoreModule();
        String contestType = "men 10m synchronised";
        try (
                Writer wr = new FileWriter("src\\main\\testResources\\output.txt");
                BufferedWriter out = new BufferedWriter(wr);
        ) {
            coreModule.displayResultsForEachEvent(contestType, out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getContest() {
        CoreModule coreModule = new CoreModule();
        String contestType = "women 1m springboard";
        Map<String, ContestResult> map = coreModule.getContest(contestType);
        assertNotNull(map);
    }

    @Test
    public void displayDetailResult() {
        CoreModule coreModule = new CoreModule();
        String contestType = "women 10m synchronised";
        try (
                Writer wr = new FileWriter("src\\main\\testResources\\output.txt");
                BufferedWriter out = new BufferedWriter(wr);
        ) {
            coreModule.displayDetailResult(contestType, out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
