import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DWASearch {

    /** 本地数据快照路径（相对项目根目录，不依赖个人电脑的绝对路径）。 */
    static final String DATA_FILE = "data_test.json";

    public static void main(String[] args){
        // 判断 args 是否合法
        if (args.length < 2){
            System.err.println("用法: java -jar DWASearch.jar <input.txt> <output.txt>");
            System.exit(1);
        }
        String inputFile = args[0];
        String outputFile = args[1];

        Path inputPath = Paths.get(inputFile);

        try {
            // 数据快照由本项目 Crawler + DataMerger 抓取合并生成，见 README「数据来源与抓取」
            String dataContent = Files.readString(Path.of(DATA_FILE));
            CoreModule core = new CoreModule(dataContent);
            StringBuilder out = new StringBuilder();

            List<String> lines = Files.readAllLines(Path.of(inputFile));
            for (String line : lines) {
                out.append(core.handleLine(line));
            }

            // 输出
            Files.writeString(Path.of(outputFile), out.toString(), StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
        catch (IllegalArgumentException e) {
            // 数据文件损坏/缺字段：明确失败，走 stderr，不打印堆栈，不写出结果文件
            System.err.println("Error: invalid data - " + e.getMessage());
            System.exit(1);
        }
    }
}
