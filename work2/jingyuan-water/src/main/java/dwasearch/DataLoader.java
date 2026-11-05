package dwasearch;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

/**
 * 读取 classpath 下的 data.json，反序列化为 {@link CompetitionData}。
 */
public class DataLoader {

    /**
     * 加载数据文件。
     *
     * @throws Exception 文件缺失或 JSON 解析失败时抛出，由调用方统一处理
     */
    public static CompetitionData load() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        // getResourceAsStream 从 classpath 取文件：打包进 jar 后，在任何工作目录都能找到
        try (InputStream in = DataLoader.class.getResourceAsStream("/data.json")) {

            if (in == null) {
                throw new IllegalStateException(
                        "classpath 下找不到 data.json，请确认文件位于 src/main/resources/data.json");
            }

            return mapper.readValue(in, CompetitionData.class);
        }
    }
}
