package Command;

public class ErrorCommand extends Command {
    public ErrorCommand(int line, String rawCommand) {
        super("error", line, rawCommand);
    }

    @Override
    public String immediateOutput() {
        return "Error\n-----\n";
    }
}
