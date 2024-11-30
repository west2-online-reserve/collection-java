public class Athlete {
    private final String country;
    private final String gander;
    private final String firstName;
    private final String secondName;

    public Athlete(String country, int gander, String firstName, String secondName) {
        this.country = country;
        if (gander == 0){
            this.gander = "Male";
        }else {
            this.gander = "Female";
        }

        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getCountry() {
        return country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public String toString(){
        return (("""
                Full Name:%s %s
                Gender:%s
                Country:%s
                -----
                """).formatted(secondName, firstName, gander, country));
    }
}
