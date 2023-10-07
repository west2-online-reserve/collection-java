package com.west2.work2;

public class AnimalNotFoundException extends RuntimeException {
    private String warning;

    public AnimalNotFoundException(String warning) {
        this.warning = warning;
    }

    public String toString() {
        return this.warning;
    }

}
