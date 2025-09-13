package DAWSearch.jar;

public class Athletes {
    @JsonProperty
    private String name;
    private String gender;
    private String country;
    private String rank;
    private double[] scores;
    private double totalScore;

    public Athletes(String name, String gender, String country, String rank, double[] scores, double totalScore) {
        this.name = name;
        this.gender = gender;
        this.country = country;
        this.rank = rank;
        this.scores = scores;
        this.totalScore = totalScore;
    }
}
