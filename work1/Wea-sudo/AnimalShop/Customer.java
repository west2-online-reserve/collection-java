package example;

public class Customer {
    private String name;
    private int countOfVisitStore;
    private LocalDate latestDate;


    public Customer(String name) {
        this.name = name;
        this.countOfVisitStore = 0;
        this.latestDate = null;
    }

    public Customer(String name, int countOfVisitStore, LocalDate latestDate) {
        this.name = name;
        this.countOfVisitStore = countOfVisitStore;
        this.latestDate = latestDate;

    }

    public void visit() {
        countOfVisitStore++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCountOfVisitStore() {
        return countOfVisitStore;
    }

    public void setCountOfVisitStore(int countOfVisitStore) {
        this.countOfVisitStore = countOfVisitStore;
    }

    public LocalDate getLatestDate() {
        return latestDate;
    }

    public void setLatestDate(LocalDate latestDate) {
        this.latestDate = latestDate;
    }

    @Override
    public String toString() {
        return "姓名:" + name +
                ", 观光次数:" + countOfVisitStore +
                ", 最新到店时间:" + latestDate;
    }
}
