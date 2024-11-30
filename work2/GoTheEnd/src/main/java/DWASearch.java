import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DWASearch {
    public static Pattern PLAYER_REGEX = Pattern.compile("players");
    public static Pattern CONTEST_REGEX = Pattern.compile("result (men 1m springboard|men 3m springboard|" +
            "men 3m synchronised|men 10m platform|men 10m synchronised|women 1m springboard|" +
            "women 3m springboard|women 3m synchronised|women 10m platform|women 10m synchronised)");
    public static Pattern CONTEST_DETAIL_REGEX = Pattern.compile("result (men 1m springboard|men 3m springboard|" +
            "men 3m synchronised|men 10m platform|men 10m synchronised|women 1m springboard|" +
            "women 3m springboard|women 3m synchronised|women 10m platform|women 10m synchronised) detail");

    public static void main(String @NotNull [] args) {
        String inputFile = args[0];
        String outputFile = args[1];
        String contestType;
        CoreModule coreModule = new CoreModule();
        try (
                BufferedReader in = new BufferedReader(new FileReader("src\\main\\resources\\" + inputFile));
                BufferedWriter out = new BufferedWriter(new FileWriter("src\\main\\resources\\" + outputFile))
        ) {
            String inputText;
            while ((inputText = in.readLine()) != null){
                Matcher contestMatcher = CONTEST_REGEX.matcher(inputText);
                Matcher playerMatcher = PLAYER_REGEX.matcher(inputText);
                Matcher contestDetailMatcher = CONTEST_DETAIL_REGEX.matcher(inputText);
                if (contestMatcher.matches()) {
                    contestType = contestMatcher.group(1);
                    coreModule.displayResultsForEachEvent(contestType, out);
                } else if (playerMatcher.matches()) {
                    coreModule.displayAllPlayersInfo(out);
                } else if (contestDetailMatcher.matches()) {
                    contestType = contestDetailMatcher.group(1);
                    coreModule.displayDetailResult(contestType, out);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
