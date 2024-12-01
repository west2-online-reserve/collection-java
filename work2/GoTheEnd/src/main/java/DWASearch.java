import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DWASearch {
    public static Pattern PLAYER_REGEX = Pattern.compile("players");
    public static Pattern RESULT_REGEX = Pattern.compile("^result .*");
    public static Pattern CONTEST_REGEX = Pattern.compile("result (men 1m springboard|men 3m springboard|" +
            "men 3m synchronised|men 10m platform|men 10m synchronised|women 1m springboard|" +
            "women 3m springboard|women 3m synchronised|women 10m platform|women 10m synchronised)");
    public static Pattern CONTEST_DETAIL_REGEX = Pattern.compile("result (men 1m springboard|men 3m springboard|" +
            "men 3m synchronised|men 10m platform|men 10m synchronised|women 1m springboard|" +
            "women 3m springboard|women 3m synchronised|women 10m platform|women 10m synchronised) detail");

    public static void main(String @NotNull [] args) throws FileNumberError{
        if (args.length != 2){
            throw new FileNumberError(("输入的文件错误, 应为2, 实为%d").formatted(args.length));
        }
        String inputFile = args[0];
        String outputFile = args[1];
        try (
                BufferedReader in = new BufferedReader(new FileReader("src\\main\\data\\" + inputFile));
                BufferedWriter out = new BufferedWriter(new FileWriter("src\\main\\data\\" + outputFile))
        ) {
            String inputText;

            while ((inputText = in.readLine()) != null){
                judgeText(inputText, out);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void judgeText(String text, BufferedWriter out) throws IOException {
        String contestType;
        CoreModule coreModule = new CoreModule();

            Matcher contestMatcher = CONTEST_REGEX.matcher(text);
            Matcher playerMatcher = PLAYER_REGEX.matcher(text);
            Matcher contestDetailMatcher = CONTEST_DETAIL_REGEX.matcher(text);
            Matcher resultMatcher = RESULT_REGEX.matcher(text);

            if (playerMatcher.matches()) {
                coreModule.displayAllPlayersInfo(out);
            }else if (resultMatcher.matches()) {
                if (contestMatcher.matches()) {
                    contestType = contestMatcher.group(1);
                    coreModule.displayResultsForEachEvent(contestType, out);
                } else if (contestDetailMatcher.matches()) {
                    contestType = contestDetailMatcher.group(1);
                    coreModule.displayDetailResult(contestType, out);
                } else {
                    out.write("N/A\n-----\n");
                }
            } else {
                out.write("Error\n-----\n");
            }

    }
}
