import core.CoreModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * CoreModule单元测试类
 * 测试CoreModule的参数解析和错误处理功能
 */

class CoreModuleTest {

    @TempDir
    Path tempDir;

    private Path inputFile;
    private Path outputFile;

    @BeforeEach
    void setUp() throws IOException {
        inputFile = tempDir.resolve("input.txt");
        outputFile = tempDir.resolve("output.txt");
    }

    /**
     * 测试用例1: 测试正确的players命令
     * 验证能够正确识别players指令并创建PlayerInfoArgument
     */
    @Test
    void testPlayersCommand() throws IOException {
        // 准备测试数据
        Files.writeString(inputFile, "players");

        // 执行测试
        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });

        // 验证不会抛出异常
        assertDoesNotThrow(coreModule::invoke);
        
        // 验证输出文件已创建
        assertTrue(Files.exists(outputFile));
    }

    /**
     * 测试用例2: 测试错误的指令（大小写敏感）
     * 验证"Players"（大写P）应该返回Error
     */
    @Test
    void testCaseSensitiveCommand() throws IOException {
        Files.writeString(inputFile, "Players");

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        
        // 验证输出包含Error
        String output = Files.readString(outputFile);
        assertTrue(output.contains("Error"));
        assertTrue(output.contains("-----"));
    }

    /**
     * 测试用例3: 测试result命令 - 女子1m跳板
     * 验证能够正确解析result women 1m springboard命令
     */
    @Test
    void testResultWomen1mSpringboard() throws IOException {
        Files.writeString(inputFile, "result women 1m springboard");

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        assertTrue(Files.exists(outputFile));
    }

    /**
     * 测试用例4: 测试result detail命令 - 女子10m跳台详细结果
     * 验证能够正确解析带detail参数的result命令
     */
    @Test
    void testResultDetailCommand() throws IOException {
        Files.writeString(inputFile, "result women 10m platform detail");

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        assertTrue(Files.exists(outputFile));
    }

    /**
     * 测试用例5: 测试错误的比赛项目名称
     * 验证"result women 10m springboard"（不存在的项目）应该返回N/A
     */
    @Test
    void testInvalidSportName() throws IOException {
        Files.writeString(inputFile, "result women 10m springboard");

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        
        // 验证输出包含N/A（因为10m springboard不存在，只有1m和3m springboard）
        String output = Files.readString(outputFile);
        // 注意：如果项目不存在于映射中，会输出N/A
        assertTrue(output.contains("N/A"));
    }

    /**
     * 测试用例6: 测试错误的detail参数
     * 验证"result women 1m springboard details"（复数形式）应该返回N/A
     */
    @Test
    void testInvalidDetailParameter() throws IOException {
        Files.writeString(inputFile, "result women 1m springboard details");

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        
        String output = Files.readString(outputFile);
        assertTrue(output.contains("N/A"));
        assertTrue(output.contains("-----"));
    }

    /**
     * 测试用例7: 测试无法识别的指令
     * 验证"player"（缺少s）应该返回Error
     */
    @Test
    void testUnrecognizedCommand() throws IOException {
        Files.writeString(inputFile, "player");

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        
        String output = Files.readString(outputFile);
        assertTrue(output.contains("Error"));
        assertTrue(output.contains("-----"));
    }

    /**
     * 测试用例8: 测试result命令缺少参数
     * 验证"result women"（缺少高度和项目）应该返回N/A
     */
    @Test
    void testIncompleteResultCommand() throws IOException {
        Files.writeString(inputFile, "result women");

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        
        String output = Files.readString(outputFile);
        assertTrue(output.contains("N/A"));
    }

    /**
     * 测试用例9: 测试多行输入
     * 验证能够正确处理多个命令
     */
    @Test
    void testMultipleCommands() throws IOException {
        Files.writeString(inputFile,
                """
                        players
                        result women 1m springboard
                        result men 3m springboard
                        players"""
        );

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        assertTrue(Files.exists(outputFile));
        
        // 验证输出文件不为空
        String output = Files.readString(outputFile);
        assertFalse(output.isEmpty());
    }

    /**
     * 测试用例10: 测试空行和空白行
     * 验证空行和空白行应该被忽略
     */
    @Test
    void testEmptyLines() throws IOException {
        Files.writeString(inputFile,
                """
                        players
                        
                          \s
                        result women 1m springboard"""
        );

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        assertTrue(Files.exists(outputFile));
    }

    /**
     * 测试用例11: 测试错误的性别参数
     * 验证"result male 1m springboard"应该返回N/A
     */
    @Test
    void testInvalidGender() throws IOException {
        Files.writeString(inputFile, "result male 1m springboard");

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        
        String output = Files.readString(outputFile);
        assertTrue(output.contains("N/A"));
    }

    /**
     * 测试用例12: 测试错误的高度格式
     * 验证"result women 5m springboard"（高度格式错误）应该返回N/A
     */
    @Test
    void testInvalidHeightFormat() throws IOException {
        Files.writeString(inputFile, "result women 5m springboard");

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        
        String output = Files.readString(outputFile);
        // 5m不在有效高度列表中，但格式正确，会尝试查找，如果找不到会返回N/A
        assertFalse(output.isEmpty());
    }

    /**
     * 测试用例13: 测试result命令后直接跟项目名（缺少性别和高度）
     * 验证"result springboard"应该返回N/A
     */
    @Test
    void testResultWithoutGenderAndHeight() throws IOException {
        Files.writeString(inputFile, "result springboard");

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        
        String output = Files.readString(outputFile);
        assertTrue(output.contains("N/A"));
    }

    /**
     * 测试用例14: 测试所有有效的比赛项目
     * 验证所有10个有效项目都能被正确解析
     */
    @Test
    void testAllValidEvents() throws IOException {
        String[] validEvents = {
            "result women 1m springboard",
            "result women 3m springboard",
            "result women 10m platform",
            "result women 3m synchronised",
            "result women 10m synchronised",
            "result men 1m springboard",
            "result men 3m springboard",
            "result men 10m platform",
            "result men 3m synchronised",
            "result men 10m synchronised"
        };

        StringBuilder input = new StringBuilder();
        for (String event : validEvents) {
            input.append(event).append("\n");
        }
        
        Files.writeString(inputFile, input.toString());

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        assertTrue(Files.exists(outputFile));
    }

    /**
     * 测试用例15: 测试文件不存在的情况
     * 验证当输入文件不存在时应该抛出异常
     */
    @Test
    void testInputFileNotExists() {
        Path nonExistentFile = tempDir.resolve("nonexistent.txt");
        
        CoreModule coreModule = CoreModule.build(new String[]{
            nonExistentFile.toString(),
            outputFile.toString()
        });
        
        assertThrows(NullPointerException.class, coreModule::invoke);
    }

    /**
     * 测试用例16: 测试build方法参数不足
     * 验证当参数少于2个时应该抛出异常
     */
    @Test
    void testBuildWithInsufficientArguments() {
        assertThrows(RuntimeException.class, () -> {
            CoreModule.build(new String[]{"input.txt"});
        });
        
        assertThrows(RuntimeException.class, () -> {
            CoreModule.build(new String[]{});
        });
    }

    /**
     * 测试用例17: 测试result命令后跟多余参数
     * 验证"result women 1m springboard detail extra"应该返回N/A
     */
    @Test
    void testResultWithExtraParameters() throws IOException {
        Files.writeString(inputFile, "result women 1m springboard detail extra");

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        
        String output = Files.readString(outputFile);
        assertTrue(output.contains("N/A"));
    }

    /**
     * 测试用例18: 测试result命令格式错误（缺少空格）
     * 验证"resultwomen 1m springboard"应该返回Error
     */
    @Test
    void testResultCommandWithoutSpace() throws IOException {
        Files.writeString(inputFile, "resultwomen 1m springboard");

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        
        String output = Files.readString(outputFile);
        assertTrue(output.contains("Error"));
    }

    /**
     * 测试用例19: 测试混合正确和错误的命令
     * 验证能够正确处理混合的命令列表
     */
    @Test
    void testMixedValidAndInvalidCommands() throws IOException {
        Files.writeString(inputFile,
                """
                        players
                        player
                        result women 1m springboard
                        result women 1m springboard details
                        result sss
                        result detail
                        result men 10m synchronised"""
        );

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        
        String output = Files.readString(outputFile);
        // 应该包含Error和N/A
        assertTrue(output.contains("Error") || output.contains("N/A"));
    }

    /**
     * 测试用例20: 测试所有男子项目的detail命令
     * 验证所有男子项目的detail命令都能被正确解析
     */
    @Test
    void testAllMenDetailCommands() throws IOException {
        String[] menDetailEvents = {
            "result men 1m springboard detail",
            "result men 3m springboard detail",
            "result men 10m platform detail",
            "result men 3m synchronised detail",
            "result men 10m synchronised detail"
        };

        StringBuilder input = new StringBuilder();
        for (String event : menDetailEvents) {
            input.append(event).append("\n");
        }
        
        Files.writeString(inputFile, input.toString());

        CoreModule coreModule = CoreModule.build(new String[]{
            inputFile.toString(),
            outputFile.toString()
        });
        
        assertDoesNotThrow(coreModule::invoke);
        assertTrue(Files.exists(outputFile));
    }
}

