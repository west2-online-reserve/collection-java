package HomeworkWall;

public class Test01 {
    public static void main(String[] args) {
        Watermelon watermelon1=new Watermelon(1,"老兵西瓜摊",200,true) ;
        watermelon1.purchase(watermelon1,20);//买20个西瓜
        watermelon1.purchase(watermelon1,2);//买2个西瓜
        watermelon1.setClosed(false);//休摊
        watermelon1.add(watermelon1,50);//进货
        watermelon1.add(watermelon1,20);
        watermelon1.toString();//
        Watermelon watermelon2=new Watermelon(2,"小明西瓜摊",120,false);
        watermelon2.setClosed(true);//2号西瓜摊开张
        watermelon2.purchase(watermelon2,30);
        watermelon2.add(watermelon2,60);//进货
        Watermelon []allWatermelon={watermelon1,watermelon2};
        Watermelon.closeBooth(allWatermelon);//把未开业的西瓜摊改为开业
        for (int i=0;i<allWatermelon.length;i++){//输出西瓜摊信息
            System.out.println(allWatermelon[i].toString());
        }
    }
}
