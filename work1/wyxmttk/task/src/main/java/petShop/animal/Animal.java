package petShop.animal;

public abstract class Animal {
    private String name;
    private int age;
    //true 雄性 false 雌性
    private boolean gender;
    private double price;
    private double profit;

    private volatile static long lastId = 0;
    private long id;

    {
        synchronized (Animal.class) {
            this.id = ++lastId;
        }
    }

    public static long getLastId() {
        return lastId;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    protected void setPrice(double price) {
        this.price = price;
    }

    public Animal(String name, int age, boolean gender, double price, double profit) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.profit = profit;
    }
    public abstract String toString();

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
}
