package com.petshop;

public class Customer {
    private String name;
    private int numberOfVisits;
    private LocalDate lastVisitDate;

    public Customer(String name, int numberOfVisits, String date) {
        this.name = name;
        this.numberOfVisits = numberOfVisits;
        this.lastVisitDate = new LocalDate(date);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append(", Number of visits: ").append(numberOfVisits).append(", Last visit date: ").append(lastVisitDate.getLatestVisitTime());
        return sb.toString();
    }
}
