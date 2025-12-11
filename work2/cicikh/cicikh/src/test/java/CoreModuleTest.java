import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * CoreModule的单元测试类
 */
public class CoreModuleTest {
    private static final String TEST_OUTPUT_FILE = "test_output.txt";

    /**
     * 测试CoreModule初始化
     */
    @Test
    public void testCoreModuleInitialization() {
        CoreModule coreModule = new CoreModule();
        assertNotNull(coreModule);
    }

    /**
     * 测试输出所有选手信息
     */
    @Test
    public void testDisplayAllPlayersInfo() {
        // 创建CoreModule实例
        CoreModule coreModule = new CoreModule();

        // 删除之前的测试文件
        deleteTestFile();

        // 调用方法
        coreModule.displayAllPlayersInfo(TEST_OUTPUT_FILE);

        // 验证文件存在且有内容
        File file = new File(TEST_OUTPUT_FILE);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);

        // 验证文件内容格式
        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_OUTPUT_FILE))) {
            String line;
            int playerCount = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Full Name:")) {
                    playerCount++;
                    // 验证性别行
                    line = reader.readLine();
                    assertNotNull(line);
                    assertTrue(line.startsWith("Gender:"));
                    // 验证国籍行
                    line = reader.readLine();
                    assertNotNull(line);
                    assertTrue(line.startsWith("Country:"));
                    // 验证分隔线
                    line = reader.readLine();
                    assertNotNull(line);
                    assertEquals("-----", line);
                }
            }
            // 至少有一个选手
            assertTrue(playerCount > 0);
        } catch (IOException e) {
            e.printStackTrace();
            fail("读取测试文件失败");
        }
    }

    /**
     * 测试输出决赛结果（有效比赛项目）
     */
    @Test
    public void testDisplayFinalResultsValidEvent() {
        CoreModule coreModule = new CoreModule();
        deleteTestFile();

        // 使用一个已知存在的比赛项目（来自模拟数据）
        boolean success = coreModule.displayFinalResults("women-10m", TEST_OUTPUT_FILE);

        // 验证是否成功
        assertTrue(success);

        // 验证文件内容
        File file = new File(TEST_OUTPUT_FILE);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    /**
     * 测试输出决赛结果（无效比赛项目）
     */
    @Test
    public void testDisplayFinalResultsInvalidEvent() {
        CoreModule coreModule = new CoreModule();
        deleteTestFile();

        // 使用一个不存在的比赛项目
        boolean success = coreModule.displayFinalResults("invalid event", TEST_OUTPUT_FILE);

        // 验证是否失败
        assertFalse(success);
    }

    /**
     * 测试输出详细结果（有效比赛项目）
     */
    @Test
    public void testDisplayDetailedResultsValidEvent() {
        CoreModule coreModule = new CoreModule();
        deleteTestFile();

        // 使用一个已知存在的比赛项目（来自模拟数据）
        boolean success = coreModule.displayDetailedResults("women-10m", TEST_OUTPUT_FILE);

        // 验证是否成功
        assertTrue(success);

        // 验证文件内容
        File file = new File(TEST_OUTPUT_FILE);
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    /**
     * 测试输出详细结果（无效比赛项目）
     */
    @Test
    public void testDisplayDetailedResultsInvalidEvent() {
        CoreModule coreModule = new CoreModule();
        deleteTestFile();

        // 使用一个不存在的比赛项目
        boolean success = coreModule.displayDetailedResults("invalid event", TEST_OUTPUT_FILE);

        // 验证是否失败
        assertFalse(success);
    }

    /**
     * 测试选手信息排序
     */
    @Test
    public void testPlayerSorting() {
        CoreModule coreModule = new CoreModule();
        deleteTestFile();

        coreModule.displayAllPlayersInfo(TEST_OUTPUT_FILE);

        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_OUTPUT_FILE))) {
            String line;
            String previousCountry = "";
            String previousLastName = "";
            boolean firstPlayer = true;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Full Name:")) {
                    // 解析选手信息
                    String fullName = line.substring(11);
                    line = reader.readLine(); // Gender
                    line = reader.readLine(); // Country
                    String country = line.substring(8).trim();

                    // 解析姓氏
                    String[] nameParts = fullName.split(" ");
                    String lastName = nameParts[nameParts.length - 1];

                    if (!firstPlayer) {
                        // 验证排序：国籍升序，姓氏升序
                        if (country.equals(previousCountry)) {
                            assertTrue(lastName.compareTo(previousLastName) >= 0);
                        } else {
                            assertTrue(country.compareTo(previousCountry) > 0);
                        }
                    }

                    previousCountry = country;
                    previousLastName = lastName;
                    firstPlayer = false;

                    // 跳过分隔线
                    reader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("读取测试文件失败");
        }
    }

    /**
     * 测试文件追加功能
     */
    @Test
    public void testFileAppend() {
        CoreModule coreModule = new CoreModule();
        deleteTestFile();

        // 第一次调用
        coreModule.displayAllPlayersInfo(TEST_OUTPUT_FILE);
        long length1 = new File(TEST_OUTPUT_FILE).length();

        // 第二次调用
        coreModule.displayAllPlayersInfo(TEST_OUTPUT_FILE);
        long length2 = new File(TEST_OUTPUT_FILE).length();

        // 验证文件长度增加
        assertTrue(length2 > length1);
    }

    /**
     * 测试输出格式
     */
    @Test
    public void testOutputFormat() {
        CoreModule coreModule = new CoreModule();
        deleteTestFile();

        coreModule.displayFinalResults("women-10m", TEST_OUTPUT_FILE);

        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_OUTPUT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Full Name:")) {
                    // 验证格式
                    assertNotNull(line.substring(11));

                    line = reader.readLine(); // Rank
                    assertNotNull(line);
                    assertTrue(line.startsWith("Rank:"));
                    assertNotNull(line.substring(5));

                    line = reader.readLine(); // Score
                    assertNotNull(line);
                    assertTrue(line.startsWith("Score:"));
                    assertNotNull(line.substring(6));

                    line = reader.readLine(); // Separator
                    assertNotNull(line);
                    assertEquals("-----", line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            fail("读取测试文件失败");
        }
    }

    /**
     * 测试比赛结果排序和分数计算
     * 验证排名是否正确，分数格式是否正确
     */
    @Test
    public void testResultsRankingAndScores() {
        System.out.println("=== 测试比赛结果排序和分数计算 ===");

        CoreModule coreModule = new CoreModule();
        deleteTestFile();

        // 测试女子10米跳台决赛结果
        boolean success = coreModule.displayFinalResults("women-10m", TEST_OUTPUT_FILE);
        assertTrue("有效的比赛项目应该输出成功", success);

        try (BufferedReader reader = new BufferedReader(new FileReader(TEST_OUTPUT_FILE))) {
            String line;
            int previousRank = 0;
            double previousTotalScore = 0.0;
            boolean firstResult = true;
            int resultCount = 0;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Full Name:")) {
                    resultCount++;
                    String playerName = line.substring(11);
                    System.out.println("选手 " + resultCount + ": " + playerName);

                    // 读取Rank行
                    line = reader.readLine();
                    assertNotNull("Rank行不能为空", line);
                    assertTrue("Rank行应该以'Rank:'开头: " + line, line.startsWith("Rank:"));

                    String rankStr = line.substring(5).trim();
                    int currentRank = Integer.parseInt(rankStr);
                    System.out.println("  排名: " + currentRank);

                    // 读取Score行
                    line = reader.readLine();
                    assertNotNull("Score行不能为空", line);
                    assertTrue("Score行应该以'Score:'开头: " + line, line.startsWith("Score:"));

                    String scoreStr = line.substring(6).trim();
                    System.out.println("  分数: " + scoreStr);

                    // 验证分数格式：应该包含 " + " 和 " = "
                    assertTrue("分数格式应该包含' + ': " + scoreStr,
                            scoreStr.contains(" + "));
                    assertTrue("分数格式应该包含' = ': " + scoreStr,
                            scoreStr.contains(" = "));

                    // 解析总分
                    String[] scoreParts = scoreStr.split(" = ");
                    assertEquals("分数应该被等号分成两部分: " + scoreStr, 2, scoreParts.length);

                    double totalScore = Double.parseDouble(scoreParts[1]);
                    System.out.println("  总分: " + totalScore);

                    if (!firstResult) {
                        // 验证排名顺序：应该是1, 2, 3... 递增
                        assertTrue("排名应该递增: " + previousRank + " < " + currentRank,
                                currentRank > previousRank);

                        if (totalScore > previousTotalScore) {
                            System.out.println("  注意：排名" + currentRank + "的分数(" + totalScore +
                                    ") > 排名" + previousRank + "的分数(" + previousTotalScore + ")");
                        }
                    }

                    previousRank = currentRank;
                    previousTotalScore = totalScore;
                    firstResult = false;

                    // 验证分隔线
                    line = reader.readLine();
                    assertNotNull("分隔线不能为空", line);
                    assertEquals("分隔线应该是'-----'", "-----", line);

                    System.out.println();
                }
            }

            // 验证至少有一个结果
            assertTrue("应该至少有一个比赛结果", resultCount > 0);
            System.out.println("总计: " + resultCount + " 个比赛结果");

            // 女子10米跳台应该有5个结果（来自模拟数据）
            assertEquals("女子10米跳台应该有5个决赛结果", 5, resultCount);

        } catch (IOException e) {
            fail("读取测试文件失败: " + e.getMessage());
        } catch (NumberFormatException e) {
            fail("数据格式错误（无法解析排名或分数）: " + e.getMessage());
        }

        System.out.println("=== 测试完成 ===\n");
    }

    /**
     * 辅助方法：删除测试文件
     */
    private void deleteTestFile() {
        File file = new File(TEST_OUTPUT_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
}