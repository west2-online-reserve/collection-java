package com.petshop;

public class LocalDate {
    private String date;

    public LocalDate(String date) {
        this.date = date;
    }
    public void setLatestVisitTime(String date) {
        this.date = date;
    }
    public String getLatestVisitTime() {
        return date;
    }
}
