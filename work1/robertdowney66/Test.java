package com.west2.check01;

public class Test {
    public static void main(String[] args) {
        Booth a= new Booth(123,"ming",500,false);
        Booth b= new Booth(345,"hong",700,true);
        Booth c= new Booth(456,"hua",900,false);


        Booth.purchase(a,123);
        System.out.println(a);
        Booth.purchase(a,510);
        Booth.purchase(a,-30);
        System.out.println(a);
        Booth.purchase(b,200);
        a.restock(200);
        System.out.println(a);
        a.restock(-20);
        System.out.println(a);
        a.closeBooths(new Booth[]{a,b,c});

    }


}
