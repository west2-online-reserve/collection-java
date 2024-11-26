package west2;

import java.time.LocalDate;

public class Customer {
    private String name;
    private int visitCount;
    private LocalDate lastVisit;

    public Customer(String name) {
        this.name = name;
        this.visitCount = 1;
        this.lastVisit = LocalDate.now();
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
    public String toString() {
        return "Customer{" +
               "name='" + name + '\'' +
               ", visitCount=" + visitCount +
               ", lastVisit=" + lastVisit +
               '}';
    }

    public void visit() {
        visitCount++;
        lastVisit = LocalDate.now();
    }
}