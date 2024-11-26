public class Snake extends Animal {
    private boolean havePoison;
    public Snake() {}
    public Snake(String name, int age, String gender,double price,boolean havePoison) {
        super(name, age, gender, price);
        this.havePoison = havePoison;
    }
    @Override
    public String toString() {
        return  "姓名"+getName()+"   年龄"+getAge()+"  性别"+getGender()+"    价格"+getPrice();
    }
}
