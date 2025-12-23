package west2.task1.AnimalShop;

import java.time.LocalDate;

public class Custormer {
    private String name;
    private int comeTime;
    private LocalDate last;

    Custormer(String name){
        this.name = name;
    }

    public void setLast(LocalDate last) {
        this.last = last;
    }

    @Override
    public String toString() {
        return " name: " +name+"\tcomeTime: "+comeTime+"\tlast: "+last;
    }
}
