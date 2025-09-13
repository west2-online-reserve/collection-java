public class LocalDate {
    private int year;
    private int month;
    private int day;

    LocalDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getTime() {
        return "[" + this.year + "-" + this.month + "-" + this.day + "]";
    }
}
