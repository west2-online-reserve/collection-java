import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 三条考核必须满足的测试要求：
 *   1. 空输入文件 → 空输出文件
 *   2. 空行 → 按「非法命令」处理，输出 Error 块（行为已固定，README 有约定）
 *   3. JSON 损坏 / 缺字段 → 必须明确失败（抛 IllegalArgumentException，不静默产出错误结果）
 *
 * 说明：断言期望值来自「行为契约」（README 中写明的输出规则），不来自被测代码的输出。
 */
class DataLoaderTest {

    private static final Path DATA = Path.of("data_test.json");

    private static String realData() throws IOException {
        return Files.readString(DATA, StandardCharsets.UTF_8);
    }

    // ================= 1. 空文件 → 空输出 =================

    @Test
    void 空输入文件产生空输出() throws IOException {
        Path in = Files.createTempFile("dwa-empty-in", ".txt");
        Path out = Files.createTempFile("dwa-empty-out", ".txt");
        try {
            Files.writeString(in, "", StandardCharsets.UTF_8);

            // 与 DWASearch.main 相同的处理路径：逐行 handleLine 后拼接
            CoreModule core = new CoreModule(realData());
            StringBuilder sb = new StringBuilder();
            List<String> lines = Files.readAllLines(in);
            for (String line : lines) sb.append(core.handleLine(line));

            Files.writeString(out, sb.toString(), StandardCharsets.UTF_8);

            assertEquals(0, Files.size(out), "空输入文件必须产生 0 字节输出文件");
            assertEquals(0, lines.size(), "空文件不应产生任何命令行");
        } finally {
            Files.deleteIfExists(in);
            Files.deleteIfExists(out);
        }
    }

    // ================= 2. 空行 → 非法命令 =================

    @Test
    void 空行按非法命令处理() throws IOException {
        CoreModule core = new CoreModule(realData());

        // 契约：空行不是合法命令，也不是合法 result，输出 Error 块
        assertEquals("Error\n-----\n", core.handleLine(""));

        // 纯空白行同样不是合法命令（命令匹配不做空格归一化）
        assertEquals("Error\n-----\n", core.handleLine("   "));
        assertEquals("Error\n-----\n", core.handleLine("\t"));
    }

    @Test
    void 多个空行逐行各自产出Error块() throws IOException {
        CoreModule core = new CoreModule(realData());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) sb.append(core.handleLine(""));

        assertEquals("Error\n-----\n".repeat(3), sb.toString(),
                "3 个空行应产出 3 个 Error 块，每个块自带结尾换行");
    }

    // ================= 3. JSON 损坏 / 缺字段 → 明确失败 =================

    @Test
    void 缺events键必须失败() throws IOException {
        String broken = realData().replace("\"events\"", "\"notEvents\"");
        assertThrows(IllegalArgumentException.class,
                () -> new CoreModule(broken),
                "缺 events 键时不得静默返回空选手列表");
    }

    @Test
    void 选手缺gender必须失败() throws IOException {
        // 删掉第一个选手的 gender 字段（含其后的逗号和换行）
        String broken = realData().replaceFirst(
                "\"gender\": \"[^\"]*\",\\s*", "");
        assertThrows(IllegalArgumentException.class,
                () -> new CoreModule(broken),
                "选手缺 gender 时不得静默取下一个人的值");
    }

    @Test
    void 选手缺country必须失败() throws IOException {
        String broken = realData().replaceFirst(
                "\"country\": \"[^\"]*\",\\s*", "");
        assertThrows(IllegalArgumentException.class,
                () -> new CoreModule(broken),
                "选手缺 country 时不得静默取下一个人的值");
    }

    // 注意：replaceFirst 命中的是数据快照中 events[0] 的成绩记录，
    // 即 "result women 3m springboard"，因此必须查询该命令才能走到损坏处。
    private static final String FIRST_EVENT = "result women 3m springboard";

    @Test
    void 成绩缺scores必须失败() throws IOException {
        String original = realData();
        String broken = original.replaceFirst("\"scores\": \\[[^\\]]*\\],\\s*", "");
        assertNotEquals(original, broken, "测试数据构造失败：未删除任何 scores 字段");
        assertThrows(IllegalArgumentException.class,
                () -> new CoreModule(broken).handleLine(FIRST_EVENT),
                "成绩缺 scores 时不得静默取下一条记录的值");
    }

    @Test
    void 成绩缺rank必须失败() throws IOException {
        String original = realData();
        String broken = original.replaceFirst("\"rank\": [0-9]+,\\s*", "");
        assertNotEquals(original, broken, "测试数据构造失败：未删除任何 rank 字段");
        assertThrows(IllegalArgumentException.class,
                () -> new CoreModule(broken).handleLine(FIRST_EVENT),
                "成绩缺 rank 时不得静默取下一条记录的值");
    }

    @Test
    void 成绩缺totalPoints必须失败() throws IOException {
        String original = realData();
        // totalPoints 是每条成绩记录的最后一个字段，其后没有逗号
        String broken = original.replaceFirst("\"totalPoints\": \"[^\"]*\"", "");
        assertNotEquals(original, broken, "测试数据构造失败：未删除任何 totalPoints 字段");
        assertThrows(IllegalArgumentException.class,
                () -> new CoreModule(broken).handleLine(FIRST_EVENT),
                "成绩缺 totalPoints 时不得静默取下一条记录的值");
    }

    @Test
    void 结果数组未闭合必须失败() throws IOException {
        String original = realData();
        String broken = original.replaceFirst("\\],\\s*\"totalPoints\": \"374.40\"", "\"totalPoints\": \"374.40\"");
        assertNotEquals(original, broken, "测试数据构造失败：未破坏 scores 数组");
        assertThrows(IllegalArgumentException.class,
                () -> new CoreModule(broken).handleLine(FIRST_EVENT),
                "scores 数组缺右括号时必须明确失败");
    }

    @Test
    void 空数据字符串必须失败() {
        assertThrows(IllegalArgumentException.class, () -> new CoreModule(""),
                "空数据不得静默产出空选手块");
    }
}
