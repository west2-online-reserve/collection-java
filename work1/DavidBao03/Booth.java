public class Booth {
    private long id;
    private String name;
    private int tota;
    private boolean isClosed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTota() {
        return tota;
    }

    public void setTota(int tota) {
        this.tota = tota;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    @Override
    public String toString() {
        return "WaterMelonStall{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tota=" + tota +
                ", isClosed=" + isClosed +
                '}';
    }

    public Booth(long id, String name, int tota, boolean isClosed) {
        this.id = id;
        this.name = name;
        this.tota = tota;
        this.isClosed = isClosed;
    }

    public void restock(int num){
        if(num < 0){
            System.out.println("数量不能为负，进货失败。");
            return;
        }

        if(num > 200){
            System.out.println("数量不能超过200，进货失败。");
            return;
        }

        System.out.println("进货成功。");
    }
}
