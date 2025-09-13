package DAWSearch.jar;

public interface FunctionDeclaration {
    void inputCommand(String command);
    void displayAllPlayersInfo(String filePath);
    void displayFinalResults(String filePath);
    boolean isValidCommand(String command);
}
