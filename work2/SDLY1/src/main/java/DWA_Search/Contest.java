package DWA_Search;

import java.util.List;
public class Contest {
    private String TotalPoints;
    private String FullName;
    private int Rank;
    private List<String> data;
    public Contest(String TotalPoints, int Rank, String FullName, List<String> data) {
        this.TotalPoints = TotalPoints;
        this.Rank = Rank;
        this.FullName = FullName;
        this.data = data;
    }

    public String getTotalPoints() {
        return TotalPoints;
    }

    public void setTotalPoints(String TotalPoints) {
        this.TotalPoints = TotalPoints;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int Rank) {
        this.Rank = Rank;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data
        ;
    }
    public String getscore() {
        StringBuilder data_score = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            if (i +1 == data.size() ) {
                data_score.append(data.get(i)).append(" = ");
                break;
            }
            data_score.append(data.get(i)).append(" + ");
        }

        data_score.append(TotalPoints);
        return data_score.toString();
    }
    @Override
    public String toString() {
        return "Full Name:" + FullName + "\n" +
                "Rank:" + Rank + "\n" +
                "Score:" + getscore() + "\n" +
                "--------------------------\n";
    }
}