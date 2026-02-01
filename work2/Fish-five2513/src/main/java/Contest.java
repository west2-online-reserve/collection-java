import java.util.List;

public class Contest {
    private String DisciplineName;
    private String level;
    private int finalrank;
    private int semifinalrank;
    private int preliminaryrank;
    private double finalTotalPoints;
    private double semifinalTotalPoints;
    private double preliminaryTotalPoints;
    private List<Double> finalPoints;
    private List<Double> semifinalPoints;
    private List<Double> preliminaryPoints;
    private Athlete athletes;
    public String getDisciplineName() {
        return DisciplineName;
    }
    public void setDisciplineName(String disciplineName) {
        DisciplineName = disciplineName;
    }
    public String getLevel() {
        return level;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public Athlete getAthletes() {
        return athletes;
    }
    public void setAthletes(Athlete athletes) {
        this.athletes = athletes;
    }
    public Integer getFinalrank() {
        return finalrank;
    }
    public void setFinalrank(int finalrank) {
        this.finalrank = finalrank;
    }
    public Integer getSemifinalrank() {
        return semifinalrank;
    }
    public void setSemifinalrank(int semifinalrank) {
        this.semifinalrank = semifinalrank;
    }
    public Integer getPreliminaryrank() {
        return preliminaryrank;
    }
    public void setPreliminaryrank(int preliminaryrank) {
        this.preliminaryrank = preliminaryrank;
    }
    public double getFinalTotalPoints() {
        return finalTotalPoints;
    }
    public void setFinalTotalPoints(double finalTotalPoints) {
        this.finalTotalPoints = finalTotalPoints;
    }
    public double getSemifinalTotalPoints() {
        return semifinalTotalPoints;
    }
    public void setSemifinalTotalPoints(double semifinalTotalPoints) {
        this.semifinalTotalPoints = semifinalTotalPoints;
    }
    public double getPreliminaryTotalPoints() {
        return preliminaryTotalPoints;
    }
    public void setPreliminaryTotalPoints(double preliminaryTotalPoints) {
        this.preliminaryTotalPoints = preliminaryTotalPoints;
    }
    public List<Double> getFinalPoints() {
        return finalPoints;
    }
    public void setFinalPoints(List<Double> finalPoints) {
        this.finalPoints = finalPoints;
    }
    public List<Double> getSemifinalPoints() {
        return semifinalPoints;
    }
    public void setSemifinalPoints(List<Double> semifinalPoints) {
        this.semifinalPoints = semifinalPoints;
    }
    public List<Double> getPreliminaryPoints() {
        return preliminaryPoints;
    }
    public void setPreliminaryPoints(List<Double> preliminaryPoints) {
        this.preliminaryPoints = preliminaryPoints;
    }
    public Contest(String DisciplineName, String level, int finalrank, int semifinalrank, int preliminaryrank,
            double finalTotalPoints,
            double semifinalTotalPoints, double preliminaryTotalPoints,
            List<Double> finalPoints,
            List<Double> semifinalPoints,
            List<Double> preliminaryPoints,
            Athlete athletes) {
        this.DisciplineName = DisciplineName;
        this.level = level;
        this.finalrank = finalrank;
        this.semifinalrank = semifinalrank;
        this.preliminaryrank = preliminaryrank;

        this.finalTotalPoints = finalTotalPoints;
        this.semifinalTotalPoints = semifinalTotalPoints;
        this.preliminaryTotalPoints = preliminaryTotalPoints;
        this.finalPoints = finalPoints;
        this.semifinalPoints = semifinalPoints;
        this.preliminaryPoints = preliminaryPoints;
        this.athletes = athletes;
    }
}
