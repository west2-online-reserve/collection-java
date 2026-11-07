import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class CoreModuleTest {
    CoreModule core;

    @BeforeEach
    void 准备() throws IOException {
        core = new CoreModule(Files.readString(
                Path.of("data_test.json")));
    }
    // 五个 @Test 方法往下排

    @Test
    void players输出以第一名开头() {
        String out = core.handleLine("players");          // 执行
        assertTrue(out.startsWith("Full Name:COLE Ellie\n"));  // 断言
    }

    @Test
    void 分割线(){
        String out = core.handleLine("players");
        long count = out.lines()
                .filter(l -> l.equals("-----"))
                .count();
        assertEquals(89L, count);
    }

    @Test
    void N和A测试(){
        String out = core.handleLine("result sss");
        assertEquals("N/A\n-----\n", out);
    }

    @Test
    void Error(){
        String out = core.handleLine("player");
        assertEquals("Error\n-----\n", out);
    }

    @Test
    void 考核样例(){
        String out = core.handleLine("result women 3m springboard");
        String expected = "Score:73.50 + 74.40 + 76.50 + 72.00 + 78.00 = 374.40";
        assertTrue(out.contains(expected));
    }
}