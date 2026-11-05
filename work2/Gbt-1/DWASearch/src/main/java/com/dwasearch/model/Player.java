package com.dwasearch.model;

public class Player {
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String country;

    public Player(String firstName, String lastName, String gender, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getCountry() {
        return country;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }
}