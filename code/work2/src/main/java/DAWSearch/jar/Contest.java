package DAWSearch.jar;
import java.util.List;
public class Contest {
    private String totalPoints;
    private int rank;
    private String fullName;
    private List<String> dives;
    //getter,setter
    public String getTotalPoints() {
        return totalPoints;
    }
    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }
    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public List<String> getDives() {
        return dives;
    }
    public void setDives(List<String> dives) {
        this.dives = dives;
    }
}
