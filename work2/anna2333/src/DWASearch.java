import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class DWASearch {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("用法为: java -jar DWASearch.jar <input.txt> <output.txt>");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];

        List<String> commands = Files.readAllLines(new File(inputFile).toPath(), StandardCharsets.UTF_8);

        FileUtils.writeStringToFile(new File(outputFile), "", StandardCharsets.UTF_8);

        Tools.choose(commands, outputFile);
    }
}
