package org.example.pojo;

public class City {

    private String name;
    private Integer id;
    private Double lat;
    private Double lon;
    private String adm2;//市
    private String adm1;//省
    private String country;
    private String citytype;
    private Integer dengji;
    public City(){

    }

    public City(String name, Integer id, Double lat, Double lon, String adm2, String adm1, String country, String citytype, Integer dengji) {
        this.name = name;
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.adm2 = adm2;
        this.adm1 = adm1;
        this.country = country;
        this.citytype = citytype;
        this.dengji = dengji;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getAdm2() {
        return adm2;
    }

    public void setAdm2(String adm2) {
        this.adm2 = adm2;
    }

    public String getAdm1() {
        return adm1;
    }

    public void setAdm1(String adm1) {
        this.adm1 = adm1;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getcitytype() {
        return citytype;
    }

    public void setcitytype(String citytype) {
        this.citytype = citytype;
    }

    public Integer getDengji() {
        return dengji;
    }

    public void setDengji(Integer dengji) {
        this.dengji = dengji;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", lat=" + lat +
                ", lon=" + lon +
                ", adm2='" + adm2 + '\'' +
                ", adm1='" + adm1 + '\'' +
                ", country='" + country + '\'' +
                ", citytype='" + citytype + '\'' +
                ", dengji=" + dengji +
                '}';
    }
}
