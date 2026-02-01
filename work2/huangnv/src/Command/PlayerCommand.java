package Command;

public class PlayerCommand extends Command {
    public PlayerCommand(int line,String rawCommand) {
        super("player",line,rawCommand);
    }
}
