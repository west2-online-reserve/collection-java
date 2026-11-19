import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

// 逐行读命令并分发。输入输出都从外面传进来，命令行和测试共用同一套逻辑。
public final class CommandProcessor {

    private static final String PLAYERS_COMMAND = "players";
    private static final String RESULT_COMMAND_PREFIX = "result ";
    private static final String RESULT_COMMAND = "result";
    private static final String ERROR_OUTPUT = "Error\n-----\n";

    private final CoreModule coreModule;
    private final PrintWriter writer;

    // 查询和输出交给 CoreModule，这里只管分发
    public CommandProcessor(PrintWriter writer) {
        this.writer = Objects.requireNonNull(writer);
        this.coreModule = new CoreModule(writer);
    }

    // 读一行处理一行，读到结尾退出
    public void run(BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            processLine(line);
            writer.flush();
        }
    }

    // 空行跳过，其余先看 players 再按 result 前缀分
    public void processLine(String line) {
        if (line == null || line.isBlank()) {
            return;
        }
        String command = line.trim();
        if (PLAYERS_COMMAND.equals(command)) {
            coreModule.displayAllPlayersInfo();
        } else if (command.equals(RESULT_COMMAND)) {
            // 只有 result 一个词，没有项目名
            coreModule.displayNotFoundEvent();
        } else if (command.startsWith(RESULT_COMMAND_PREFIX)) {
            coreModule.displayEventResult(command);
        } else {
            writer.print(ERROR_OUTPUT);
        }
    }
}
