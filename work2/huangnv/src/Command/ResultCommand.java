package Command;

public class ResultCommand extends Command {
    private  final String project;
    private  final boolean isDetail;

    public ResultCommand(int line,String rawCommand,String project,boolean isDetail){
        super("result",line,rawCommand);
        this.project = project;
        this.isDetail = isDetail;
    }
    public String getProject() { return project; }
    public boolean isDetail() { return isDetail; }

    @Override
    public String immediateOutput() {
        if ("N/A".equals(project)) {
            return "N/A\n-----\n";
        }
        return null;
    }
}
