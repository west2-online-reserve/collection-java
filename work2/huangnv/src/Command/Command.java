package Command;

public abstract class Command {
    protected final String  name;
    protected final String rawCommand;
    protected final int  line;

    public Command(String name,int line,String rawCommand) {
        this.name = name;
        this.line = line;
        this.rawCommand = rawCommand;
    }
    public String getName() {
        return name;
    }
    public int getLine() {
        return line;
    }
    public String getRawCommand() {
        return rawCommand;
    }

    public String immediateOutput() {
        return null;
    }
}
