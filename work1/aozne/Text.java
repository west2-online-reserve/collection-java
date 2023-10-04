public class Text {
    public static void main(String[] args) {
        Booth b = new Booth(10086, "Wuwuwuwu", 37, false);
        String s = b.toString();
        System.out.print(s);
        //购买
        b.purchase(b,12);//购买正常
        b.purchase(b,38);//购买超过
        b.purchase(b,-1);//购买少于
        b.setClosed(true);//休摊测试
        b.purchase(b,12);
        b.setClosed((false));
        System.out.println("*****这是一条分割线******");//分割线
        //进货
        b.restock(b,100);//进货正常
        b.restock(b,-1);//进货小于0
        b.restock(b,201);//进货大于200测试
        System.out.println("*****这是一条分割线******");//分割线
        //休息整改
        Booth b1=new Booth(10087,"Hahahaha",38,true);
        Booth booths[]={b,b1};
        for(int i=0;i< booths.length;i++){
            System.out.println(booths[i].toString());

        }
    }
}