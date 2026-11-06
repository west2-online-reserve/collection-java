package top.yangguangmc.dwasearch;

import java.util.Comparator;

public record Player(
        String firstName,
        String lastName,
        String gender,
        String country
) implements Comparable<Player> {
    public Player(String firstName, String lastName, int gender, String country) {
        this(firstName, lastName, gender == 0 ? "Male" : "Female", country);
        if (gender != 0 && gender != 1) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int compareTo(Player o) {
        return Comparator
                .comparing(Player::country)
                .thenComparing(Player::lastName)
                .compare(this, o);
    }

    public void print(IODataSet out) {
        out.println("Full Name:%s %s", lastName, firstName)
                .println("Gender:%s", gender)
                .println("Country:%s", country);
    }
}
