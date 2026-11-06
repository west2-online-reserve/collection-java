package dwasearch;

import java.util.ArrayList;

public class ResultItem {

    public String fullName;
    public int rank;
    public String countryCode;
    public ArrayList<String> scores = new ArrayList<String>();
    public String totalPoints;

    public ResultItem() {
    }
}
