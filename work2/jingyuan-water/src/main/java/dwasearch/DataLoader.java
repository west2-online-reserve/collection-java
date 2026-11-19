package dwasearch;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * 读取 classpath 下的 data.json，反序列化为 {@link CompetitionData}，并校验完整性。
 */
public class DataLoader {

    /**
     * 加载数据文件。
     *
     * @throws Exception 文件缺失、JSON 解析失败或数据不完整时抛出，由调用方统一处理
     */
    public static CompetitionData load() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        // getResourceAsStream 从 classpath 取文件：打包进 jar 后，在任何工作目录都能找到
        try (InputStream in = DataLoader.class.getResourceAsStream("/data.json")) {

            if (in == null) {
                throw new IllegalStateException(
                        "classpath 下找不到 data.json，请确认文件位于 src/main/resources/data.json");
            }

            CompetitionData data = mapper.readValue(in, CompetitionData.class);

            // 读完立刻体检：数据不全就在这里拦下，不让程序带着坏数据继续跑
            validate(data);

            return data;
        }
    }

    /**
     * 校验快照完整性：8 个必需项目必须齐全，且每项都要有完整的决赛成绩。
     *
     * <p>单独抽成 public 方法，是为了让测试能传入自己构造的样本，
     * 不必真的去改动 classpath 里的正式 data.json。
     *
     * @throws IllegalStateException 任何一个必需项目或字段缺失时抛出，
     *                              由 main 的 catch 统一走 stderr + 退出码 1
     */
    public static void validate(CompetitionData data) {

        if (data == null || data.events == null) {
            throw new IllegalStateException("快照缺少 events 字段");
        }

        // 先收集快照里实际有的项目名，顺便检查每一项的决赛字段
        Set<String> present = new HashSet<>();
        for (CompetitionData.Event e : data.events) {
            if (e == null || e.key == null) {
                throw new IllegalStateException("快照里存在没有 key 的项目");
            }
            present.add(e.key);

            if (e.results == null || e.results.isEmpty()) {
                throw new IllegalStateException("项目 " + e.key + " 缺少决赛成绩 results");
            }
            for (EventResult r : e.results) {
                if (r == null || r.fullName == null || r.dives == null
                        || r.dives.isEmpty() || r.total == null) {
                    throw new IllegalStateException("项目 " + e.key + " 的决赛成绩字段不完整");
                }
            }
        }

        // 再拿 8 个必需项目逐一对照，缺一个就点名报错
        for (String required : CommandRunner.EVENT_KEYS) {
            if (!present.contains(required)) {
                throw new IllegalStateException("快照缺少必需项目: " + required);
            }
        }
    }
}