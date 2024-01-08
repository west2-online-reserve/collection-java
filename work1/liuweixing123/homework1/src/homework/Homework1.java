package homework;

import pojo.Db;
import pojo.Watermelon;

public class Homework1 {
    public static void main(String[] args) {
        System.out.println(Db.arry);
        Watermelon.purchase(1002,7);
        Db.arry[0].restock(2000);
        Watermelon.closeBooths(Db.arry);
//        for(int i=0;i<Db.arry.length;i++)
//            Db.arry[i].toString();
    }
}
