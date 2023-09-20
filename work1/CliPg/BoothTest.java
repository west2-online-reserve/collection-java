package com.PeanutJava.task1;


public class BoothTest {
    public static void main(String[] args) {
        //摊位1
        Booth booth1 = new Booth(1,"PG",100,false);
        //摊位2
        Booth booth2 = new Booth(2,"KL",200,true);
        //摊位数组
        Booth []booths={booth1,booth2};

        purchase(booth1,50);
        booth2.restock(200);
        System.out.println(booth1.toString());
        closeBooths(booths);
    }

    //向目标摊位购买指定数量西瓜
    public static void purchase(Booth booth,int PurNums) {
        if (PurNums > 0 && !booth.isIsClosed() && PurNums <= booth.getTotal()) {
            int remain = booth.getTotal() - PurNums;
            booth.setTotal(remain);
            System.out.println("购买成功");
        } else {
            System.out.println("购买失败");
        }
    }

    //让booths中所有未被休业整改的摊位歇业，并输出已在休业整改的摊位信息
    public static void closeBooths(Booth []booths){
        for(int i=0;i<booths.length;i++){
            if(!booths[i].isIsClosed()){
                booths[i].setIsClosed(true);
            }else{
                System.out.println(booths[i].toString());
            }
        }


    }
}
