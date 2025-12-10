package core.argument;

import lombok.Data;

import java.util.Objects;

@Data
public class ResultArgument implements Argument {
    private String gender;
    private int height;
    private String sport;
    private boolean detail=false;

    public ResultArgument(String gender, int height, String sport) {
        this.gender = gender;
        this.height = height;
        this.sport = sport;
    }

    public ResultArgument() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ResultArgument that = (ResultArgument) o;
        return height == that.height && Objects.equals(gender, that.gender) && Objects.equals(sport, that.sport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, height, sport);
    }
}
