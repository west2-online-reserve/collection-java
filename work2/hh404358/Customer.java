class Customer{
    String name;
    int time;//到店次数
    LocalDateTime t;//最新到店时间，我用LocalDateTime具体到顾客到店时间
    Animal like;//顾客喜欢的动物，以便根据这个进行购买

    public Customer() {
        this.name = "customer";
        this.time = 0;
        this.t = LocalDateTime.of(2023,8,1,9,28,10);
        this.like = new Cat();
    }

    public Customer(String name, int time, LocalDateTime t, Animal like) {
        this.name = name;
        this.time = time;
        this.t = t;
        this.like = like;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", time=" + time +
                ", t=" + t +
                ", like='" + like + '\'' +
                '}';
    }
}