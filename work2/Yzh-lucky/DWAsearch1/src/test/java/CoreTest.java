import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
class CoreTest {

    private Core core = new Core();
    private final String TEST_OUTPUT_PATH = "D:\\java\\code\\ParserDate\\src\\main\\java\\Output.txt"; // 与Core中输出路径一致
    private final String TEST_PLAYER_JSON = "D:\\java\\code\\ParserDate\\src\\main\\java\\Data\\athletes.json"; // 测试用运动员JSON
    private final String TEST_RESULT_JSON = "D:\\java\\code\\ParserDate\\src\\main\\java\\Data\\Men 1m SpringBoard.json"; // 测试用结果JSON

    private static String readFile(String filename) throws IOException {
        if (!Files.exists(Paths.get(filename))){
            fail("文件未打开" + filename);
        }
        return new String(Files.readAllBytes(Paths.get(filename)));
    }


    @Test
    void parserPlayerInfo() {
        core.parserPlayerInfo(TEST_PLAYER_JSON);
        //读取文件内容
        try {
            String content = CoreTest.readFile(TEST_OUTPUT_PATH);
            assertTrue(content.contains("FullName:HART Alexander"));
            assertTrue(content.contains("Gender:MALE"));
            assertTrue(content.contains("Country:Austria"));
            assertTrue(content.contains("-----"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parserResult() {
        core.parserResult(TEST_RESULT_JSON);
        try {
            String content = CoreTest.readFile(TEST_OUTPUT_PATH);
            int num1_player = content.indexOf("FullName:DIXON Matthew");
            int num2_player = content.indexOf("FullName:HOULDEN Jordan Christopher");
            assertTrue(num1_player<num2_player);
            assertTrue(content.contains("Rank:1"));
            assertTrue(content.contains("58.50+61.50+58.50+67.50+57.60+69.75+69.75=373.35"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void parserResultDetail() {
        core.parserResultDetail(TEST_RESULT_JSON);
        try {
            String content = CoreTest.readFile(TEST_OUTPUT_PATH);
            int num1_player = content.indexOf("FullName");
            int num2_player = content.indexOf("DIXON");
            assertTrue(num1_player<num2_player);
            assertTrue(content.contains("Rank:1 | * | 2"));
            assertTrue(content.contains("Final Score:351.50"));
            assertTrue(content.contains("Semifinal Score:*"));
            assertTrue(content.contains("Preliminary Score:368.10"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}