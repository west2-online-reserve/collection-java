import java.util.ArrayList;

public class Contest {
    private String fullName;
    private int rank;
    private ArrayList<String> scores;


    public Contest() {
    }

    public Contest(String fullName, int rank, ArrayList<String> scores) {
        this.fullName = fullName;
        this.rank = rank;
        this.scores = scores;
    }

    /**
     * 获取
     * @return fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 设置
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 获取
     * @return rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * 设置
     * @param rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * 获取
     * @return scores
     */
    public ArrayList<String> getScores() {
        return scores;
    }

    /**
     * 设置
     * @param scores
     */
    public void setScores(ArrayList<String> scores) {
        this.scores = scores;
    }
    @Override
    public String toString() {
        return "Contest{fullName = " + fullName + ", rank = " + rank + " ,scores = " + scores + "}";
    }
}
