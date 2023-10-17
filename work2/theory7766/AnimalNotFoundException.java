package com.west2.work2;

public class AnimalNotFoundException extends RuntimeException {
    /**
     * 具体的未找到宠物警告语句
     */
    private String warning;

    public AnimalNotFoundException(String warning) {
        this.warning = warning;
    }

    public String toString() {
        return this.warning;
    }

}
