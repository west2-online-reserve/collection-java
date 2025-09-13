package DAWSearch.jar;

public class Athletes {
    private String Countryname;
    private int gender;
    private String fullName;
    private String preferredLastName;
    private String preferredFirstName;
    private String CountryCode;
    private int rank;
    private String[] divePoints;
    private int[] ranks;
    private String finalScore;
    private double[] Fscores;
    private double[] Sscores;
    private double[] Pscores;

    public Athletes(String Countryname,String countryCode, String preferredLastName, String preferredFirstName, int gender) {
        this.Countryname = Countryname;
        this.gender = gender;
        this.preferredLastName = preferredLastName;
        this.preferredFirstName = preferredFirstName;
        this.CountryCode = countryCode;
    }

    public Athletes(int rank,String finalScore,String[] divePoints,String fullName) {
        this.rank = rank;
        this.finalScore = finalScore;
        this.divePoints = divePoints;
        this.fullName = fullName;
    }

    public Athletes(int[] ranks,double[] Fscores,double[] Sscores,double[] Pscores,String fullName) {
        this.ranks = ranks;
        this.Fscores = Fscores;
        this.Sscores = Sscores;
        this.Pscores = Pscores;
        this.fullName = fullName;
    }

    //getter
    public String getCountryName() {
        return Countryname;
    }
    public String getCountryCode() {
        return CountryCode;
    }
    public int getGender() {
        return gender;
    }

    public String getFullName() {
        return preferredFirstName+" "+preferredLastName;
    }

    public String getPreferredLastName() {
        return preferredLastName;
    }

    public String getPreferredFirstName() {
        return preferredFirstName;
    }

    public int getRank() {
        return rank;
    }

    public String[] getDivePoints() {
        return divePoints;
    }

    public String getFinalScore() {
        return finalScore;
    }
}