package com.github.lpx.model;

public class Supplier {
    private double dogPrice;
    private double catPrice;
    private double birdPrice;
    private double dragonPrice;
    private double lizardPrice;

    public Supplier(double dogPrice, double catPrice, double birdPrice, double dragonPrice, double lizardPrice) {
        this.dogPrice = dogPrice;
        this.catPrice = catPrice;
        this.birdPrice = birdPrice;
        this.dragonPrice = dragonPrice;
        this.lizardPrice = lizardPrice;
    }

    public double getDogPrice() {
        return dogPrice;
    }

    public void setDogPrice(double dogPrice) {
        this.dogPrice = dogPrice;
    }

    public double getCatPrice() {
        return catPrice;
    }

    public void setCatPrice(double catPrice) {
        this.catPrice = catPrice;
    }

    public double getBirdPrice() {
        return birdPrice;
    }

    public void setBirdPrice(double birdPrice) {
        this.birdPrice = birdPrice;
    }

    public double getDragonPrice() {
        return dragonPrice;
    }

    public void setDragonPrice(double dragonPrice) {
        this.dragonPrice = dragonPrice;
    }

    public double getLizardPrice() {
        return lizardPrice;
    }

    public void setLizardPrice(double lizardPrice) {
        this.lizardPrice = lizardPrice;
    }
}
