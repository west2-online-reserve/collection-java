public class Result {
    String name;
    String[] array;
    String[] score;

    public Result() {
    }

    public Result(String name, String[] array, String[] score) {
        this.name = name;
        this.array = array;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public String[] getScore() {
        return score;
    }

    public void setScore(String[] score) {
        this.score = score;
    }

    public void setScoreByNum(String s1, int num) {
        if (num == -1) {
            return;
        }
        score[num] = s1;
    }

    public void setRankByNum(String s1, int num) {
        if (num == -1) {
            return;
        }
        array[num] = s1;
    }

    @Override
    public String toString() {
        return "Full Name:" + name + "\n" +
                "Rank:" + array[0] + " | " + array[1]  +  " | " + array[2] + "\n" +
                "Preliminary Score:" + score[0] + "\n" +
                "Semifinal Score:" +  score[1] + "\n" +
                "Final Score:" +  score[2] + "\n" +
                "-----\n";
    }
}
