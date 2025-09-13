import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestCore {
    @After
    public void test() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("E:\\develop\\java_text\\basic_code\\DWA\\src\\output", true));
        bw.write("_______________________");
        bw.newLine();
    }
    @Test
    public void testDisplayAllPlayersInfo() throws IOException {
        Core core = new Core();
        core.displayAllPlayersInfo();
    }
    @Test
    public void testDisplayContest1() throws IOException {
        Core core = new Core();
        File file = new File("Men1mSpringboard.json");
        core.displayContest(file);
    }
    @Test
    public void testDisplayContest2() throws IOException {
        Core core = new Core();
        File file = new File("Men3mSpringboard.json");
        core.displayContest(file);
    }
    @Test
    public void testDisplayContest3() throws IOException {
        Core core = new Core();
        File file = new File("Men10Platform.json");
        core.displayContest(file);
    }
    @Test
    public void testDisplayContest4() throws IOException {
        Core core = new Core();
        File file = new File("Men3mSynchronised.json");
        core.displayContest(file);
    }
    @Test
    public void testDisplayContest5() throws IOException {
        Core core = new Core();
        File file = new File("Men10mSynchronised.json");
        core.displayContest(file);
    }
    @Test
    public void testDisplayContest6() throws IOException {
        Core core = new Core();
        File file = new File("Women1mSpringboard.json");
        core.displayContest(file);
    }
    @Test
    public void testDisplayContest7() throws IOException {
        Core core = new Core();
        File file = new File("Women3mSpringboard.json");
        core.displayContest(file);
    }
    @Test
    public void testDisplayContest8() throws IOException {
        Core core = new Core();
        File file = new File("Women10mPlatform.json");
        core.displayContest(file);
    }
    @Test
    public void testDisplayContest9() throws IOException {
        Core core = new Core();
        File file = new File("Women3mSynchronised.json");
        core.displayContest(file);
    }
    @Test
    public void testDisplayContest10() throws IOException {
        Core core = new Core();
        File file = new File("Women10mSynchronised.json");
        core.displayContest(file);
    }
    @Test
    public void testDisplayContestsDetailed1() throws IOException {
        Core core = new Core();
        File file = new File("Women1mSpringboard.json");
        core.displayContestsDetailed(file);
    }
    @Test
    public void testDisplayContestsDetailed2() throws IOException {
        Core core = new Core();
        File file = new File("Women3mSpringboard.json");
        core.displayContestsDetailed(file);
    }
    @Test
    public void testDisplayContestsDetailed3() throws IOException {
        Core core = new Core();
        File file = new File("Women10mPlatform.json");
        core.displayContestsDetailed(file);
    }
    @Test
    public void testDisplayContestsDetailed4() throws IOException {
        Core core = new Core();
        File file = new File("Men1mSpringboard.json");
        core.displayContestsDetailed(file);
    }
    @Test
    public void testDisplayContestsDetailed5() throws IOException {
        Core core = new Core();
        File file = new File("Men3mSpringboard.json");
        core.displayContestsDetailed(file);
    }
    @Test
    public void testDisplayContestsDetailed6() throws IOException {
        Core core = new Core();
        File file = new File("Men10Platform.json");
        core.displayContestsDetailed(file);
    }
    @Test
    public void testdisplayContestSynchronised1() throws IOException {
        Core core = new Core();
        File file = new File("Men3mSynchronised.json");
        core.displayContestSynchronised(file);
    }
    @Test
    public void testdisplayContestSynchronised2() throws IOException {
        Core core = new Core();
        File file = new File("Men10mSynchronised.json");
        core.displayContestSynchronised(file);
    }
    @Test
    public void testdisplayContestSynchronised3() throws IOException {
        Core core = new Core();
        File file = new File("Women3mSynchronised.json");
        core.displayContestSynchronised(file);
    }
    @Test
    public void testdisplayContestSynchronised4() throws IOException {
        Core core = new Core();
        File file = new File("Women10mSynchronised.json");
        core.displayContestSynchronised(file);
    }
}
