package dwasearch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 数据完整性校验的回归测试。
 *
 * <p>每个测试都用 DataLoader.load() 取一份全新的对象，只改内存里的这份副本，
 * 从不改动 classpath 里正式的 data.json —— 这就是"独立样本"，彼此不干扰。
 */
public class DataValidationTest {

    /** 反面：完整快照必须能通过校验 */
    @Test
    void completeSnapshotPassesValidation() throws Exception {
        assertDoesNotThrow(() -> DataLoader.validate(DataLoader.load()));
    }

    /** 正面：故意挖掉一个合法项目，必须抛异常，而且报错要点名这个项目 */
    @Test
    void missingRequiredEventIsRejected() throws Exception {
        CompetitionData data = DataLoader.load();
        data.events.removeIf(e -> "women 3m springboard".equals(e.key));

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> DataLoader.validate(data));
        assertTrue(ex.getMessage().contains("women 3m springboard"),
                "报错里要点名缺了哪个项目");
    }

    /** 正面：项目还在，但决赛成绩被清空，也必须拦下 */
    @Test
    void eventWithNoFinalsIsRejected() throws Exception {
        CompetitionData data = DataLoader.load();
        data.events.get(0).results.clear();

        IllegalStateException ex = assertThrows(IllegalStateException.class,
                () -> DataLoader.validate(data));
        assertTrue(ex.getMessage().contains("决赛成绩"),
                "报错里要说明是决赛成绩缺失");
    }
}
