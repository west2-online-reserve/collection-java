package com.petshop;

public class Customer {
    String name;
    int numberOfVisits;
    LocalDate lastVisitDate;

    public Customer(String name, int numberOfVisits, String date) {
        this.name = name;
        this.numberOfVisits = numberOfVisits;
        this.lastVisitDate = new LocalDate(date);
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append(", Number of visits: ").append(numberOfVisits).append(", Last visit date: ").append(lastVisitDate);
        return sb.toString();
    }
}
