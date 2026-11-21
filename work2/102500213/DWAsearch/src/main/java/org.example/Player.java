package org.example;

public class Player implements Comparable<Player>{
    private String fullName;
    private boolean gender;
    private String country;

    public Player(){}
    public String getFullName(){ return fullName; }
    public void setFullName(String fullName){ this.fullName =fullName; }
    public boolean getGender(){ return gender; }
    public void setGender(boolean gender){ this.gender =gender; }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country){ this.country =country; }

    public String getContent(){
        StringBuilder str = new StringBuilder();
        str.append("Full Name:").append(this.fullName).append("\n");
        str.append("Gender:").append((this.gender?"Female":"Male")).append("\n");
        str.append("Country:").append(this.country).append("\n");
        str.append("-----\n");
        return str.toString();
    }
    @Override
    public int compareTo(Player o) {
        int flag=this.country.compareToIgnoreCase(o.getCountry());
        if(flag==0){
            flag=this.fullName.compareToIgnoreCase(o.getFullName());
            if(flag==0){
                if(this.gender==o.getGender()) return 0;
                return this.gender?-1:1;
            }
            return flag;
        }
        return flag;
    }

    public void showPlayer(){
        System.out.println("Full Name:"+this.fullName);
        System.out.println("Gender:"+(this.gender?"Female":"Male"));
        System.out.println("Country:"+this.country);
        System.out.println("-----");
    }
}
