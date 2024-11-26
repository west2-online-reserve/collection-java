package DWA;


import com.alibaba.fastjson.annotation.JSONField;

public class Country {
    @JSONField(name = "Participations")
    private Object athletes;
    @JSONField(name = "CountryName")
    private String countryName;

    public Country() {
    }

    public Object getAthletes() {
        return athletes;
    }

    public Country(Object athletes, String countryName) {
        this.athletes = athletes;
        this.countryName = countryName;
    }

    public void setAthletes(Object athletes) {
        this.athletes = athletes;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    @Override
    public String toString() {
        return getCountryName()+getAthletes();
    }

}
