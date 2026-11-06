package dwasearch;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DWASearch {

    public static final int OK = 0;
    public static final int ERR_ARGS = 2;
    public static final int ERR_INPUT = 3;
    public static final int ERR_DATA = 4;
    public static final int ERR_OUTPUT = 5;

    public static void main(String[] args) {
        int code = run(args, System.err);
        if (code != OK) {
            System.exit(code);
        }

    }

    public static int run(String[] args, PrintStream err) {
        if (args == null || args.length != 2) {
            err.println("用法: java -jar DWASearch.jar <input.txt> <output.txt>");
            return ERR_ARGS;
        }

        Path input = Paths.get(args[0]);
        Path output = Paths.get(args[1]);

        String dataPath = System.getProperty("dwa.data", "data.json");
        return run(input, output, Paths.get(dataPath), err);
    }

    public static int run(Path input, Path output, Path dataFile, PrintStream err) {

        if (!Files.exists(input)) {
            err.println("输入文件不存在: " + input.toAbsolutePath());
            return ERR_INPUT;
        }
        if (!Files.isReadable(input)) {
            err.println("输入文件无法读取: " + input.toAbsolutePath());
            return ERR_INPUT;
        }

        GameData data;
        try {
            data = DataLoader.load(dataFile);
        } catch (DataLoader.BadDataException e) {
            err.println("数据文件出错: " + e.getMessage());
            return ERR_DATA;
        }

        ArrayList<String> blocks = new ArrayList<String>();
        try {
            List<String> lines = Files.readAllLines(input, StandardCharsets.UTF_8);
            Query query = new Query(data);

            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.endsWith("\r")) {
                    line = line.substring(0, line.length() - 1);
                }
                line = line.trim();

                if (line.length() == 0) {
                    continue;
                }

                if (line.equals("players")) {
                    blocks.add(query.showPlayers());
                } else if (line.equals("result")) {

                    blocks.add("N/A\n" + Query.LINE);
                } else if (line.startsWith("result ")) {
                    String name = line.substring("result ".length());
                    blocks.add(query.showResult(name));
                } else {

                    blocks.add("Error\n" + Query.LINE);
                }
            }
        } catch (IOException e) {
            err.println("读取输入文件失败: " + input.toAbsolutePath() + " (" + e.getMessage() + ")");
            return ERR_INPUT;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < blocks.size(); i++) {
            if (i > 0) {
                sb.append("\n");
            }
            sb.append(blocks.get(i));
        }

        try {
            BufferedWriter writer = Files.newBufferedWriter(output, StandardCharsets.UTF_8);
            try {
                writer.write(sb.toString());
                if (sb.length() > 0) {
                    writer.write("\n");
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            err.println("写出输出文件失败: " + output.toAbsolutePath()
                    + "，输出可能不完整 (" + e.getMessage() + ")");
            return ERR_OUTPUT;
        }

        return OK;
    }
}
