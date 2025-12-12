public class Player {
    private String lastName;
    private String firstName;
    private  String gender;
    private  String country;

    public Player(String lastName, String firstName, String gender, String country) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.country = country;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Full Name:").append(lastName).append(" ").append(firstName).append("\n");
        sb.append("Gender:").append(gender).append("\n");
        sb.append("Country:").append(country).append("\n");
        sb.append("-----\n");

        return sb.toString();
    }
}
