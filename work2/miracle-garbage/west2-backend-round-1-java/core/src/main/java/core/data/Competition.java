package core.data;

public class Competition {
    private String id;
    private String name;
    private String from;
    private String to;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void printAll() {
        System.out.println("Competition{id=" + id
                + ", name=" + name
                + ", from=" + from
                + ", to=" + to + "}");
    }

}
