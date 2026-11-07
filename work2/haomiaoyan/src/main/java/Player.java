public class Player {
    private final String fullname;
    private final String gender;
    private final String country;


    public Player(String fullname, String gender, String country) {
        this.fullname = fullname;
        this.gender = gender;
        this.country = country;
    }

    public String getFullname() {
        return fullname;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getLastName(){
        int index = this.fullname.indexOf(" ");
        if (index == -1)
            return getFullname();
        else
            return this.fullname.substring(0, index);
    }

}
