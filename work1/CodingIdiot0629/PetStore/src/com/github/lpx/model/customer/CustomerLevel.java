package com.github.lpx.model.customer;

public class CustomerLevel {
    static double discountOfLevel1 = 1.0;
    static double discountOfLevel2 = 1.0;
    static double discountOfLevel3 = 1.0;
    private int level;

    private CustomerLevel(int level) {
        this.level = level;
    }

    public static CustomerLevel createCustomerLevel(int numOfVisits) {
        int level = 0;
        if (numOfVisits < 3) {
            level = 1;
        } else if (numOfVisits < 8) {
            level = 2;
        } else {
            level = 3;
        }
        return new CustomerLevel(level);
    }

    @Override
    public String toString() {
        String str = "顾客会员等级为";
        str += level;
        return str;
    }

    public int getLevel() {
        return level;
    }

    public void setLevelByNumOfVisits(int numOfVisits) {
        int level = 0;
        if (numOfVisits < 3) {
            level = 1;
        } else if (numOfVisits < 8) {
            level = 2;
        } else {
            level = 3;
        }
        this.level = level;
    }

    static boolean setDiscountOfLevel(int level, double discount) {
        if (discount > 1 || discount < 0) {
            return false;
        }
        if (level == 1) discountOfLevel1 = discount;
        else if (level == 2) discountOfLevel2 = discount;
        else if (level == 3) discountOfLevel3 = discount;
        else return false;
        return true;
    }

    public double getDiscount() {
        double discount;
        if (level == 3) {
            discount = discountOfLevel1;
        } else if (level == 2) {
            discount = discountOfLevel2;
        } else {
            discount = discountOfLevel1;
        }
        return discount;
    }

}
