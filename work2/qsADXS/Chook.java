public class Chook extends Animal {

    private boolean isDance;


    public Chook(String name, int age, int gender, boolean isDance) {
        super(name, age, gender, 2.5,0.25);
        this.isDance = isDance;
    }

    public String toString() {
        String str;
        str = "这是一只坤\n名字：" + super.getName() + "\n年龄：" + super.getAge() + "\n" + "性别：";
        if (super.getGender() == 0)
            str += "雌性";
        else if (super.getGender() == 1) {
            str += "雄性";
        } else if (super.getGender() == 2) {
            str += "被嘎蛋的雄性";
        }
        str += "\n售价：" + super.getPrice() + "\n进价: " + super.getPurchasingCost() + "\n";
        if(isDance)
            str+="这只坤会跳舞";
        else
            str+="这只坤不会跳舞";

        return str;
    }

    public void dancing(){
        if(!isDance)
            System.out.println(super.getName()+"还不会跳舞，快教教坤坤");
        else {
            System.out.println("你竟然舍得让"+super.getName()+"跳舞");
        }
    }


    public void teachDance(){
        if(isDance)
            System.out.println("这只坤经过你的教导，跳舞能力更上一层楼");
        else{
            isDance = true;
            System.out.println("这只坤在你的教导下学会了跳舞");
        }
    }
}
