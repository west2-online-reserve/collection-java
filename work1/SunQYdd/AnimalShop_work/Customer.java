public class Customer {
    private String name;
    private int cnt;
    private LocalDate Date;

    Customer(String name, int cnt, LocalDate date) {
        this.name = name;
        this.cnt = cnt;
        this.Date = date;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getCnt() {
        return this.cnt;
    }

    @Override
    public String toString() {
        return "Customer [name=" + name + ", cnt=" + cnt + ", LocalDate=" + Date.getTime();
    }
}
