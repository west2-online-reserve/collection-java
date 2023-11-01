package java_work2;

import java.time.LocalDate;

/**
 * Customer类
 *
 * @author formrc
 * @date 2023/10/23
 */
class Customer {
    private String name;
    private int visitCount;
    private LocalDate latestVisit;

    public Customer(String name, int visitCount, LocalDate latestVisit) {
        this.name = name;
        this.visitCount = visitCount;
        this.latestVisit = latestVisit;
    }

    @Override
    public String toString() {
        return  "Customer: " + "\n" +
                "name: " + name +  "\n" +
                "visitCount: " + visitCount + "\n" +
                "latestVisit: " + latestVisit ;
    }
}
