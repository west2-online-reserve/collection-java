package com.java.leaning;

import java.sql.SQLOutput;

class Booth {
    private long id;
    private String name;
    private int tota;
    private boolean isClosed;

    public Booth() {

    }
    public Booth(long id,String name,int tota,boolean isClosed){
        this.id=id;
        this.name=name;
        this.tota=tota;
        this.isClosed=isClosed;
    }

    public long getId() {return id; }

    public  void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public int getTota() {
        return tota;
    }

    public  void setTota(int tota) {
        this.tota = tota;
    }

    public boolean isIsClosed() {
        return isClosed;
    }

    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    @Override
    public  String toString(){
        String s=" id:"+getId()+"\n name:"+getName()+"\n how many xigua is sole:"+getTota()+"\n is close:"+isIsClosed();
        return s;
    }
    public static void purchase(Booth t,int num){
        if(num<0||t.isIsClosed()||t.getTota()<num){
            System.out.println("can not buy!!!");
            return;
        }
        t.setTota(t.getTota()-num);
        System.out.println("buy success!");
        return;

    }
    public void restock(int in){
        if(in>200){
            System.out.println("too much!");
            return;
        }
        tota+=in;
        System.out.println("restock success!");
    }

    public static void closeBooths(Booth[] booths){
        for(int i=0;i< booths.length;i++){
            if(!booths[i].isIsClosed())
                booths[i].setIsClosed(true);
            else {
                String s=booths[i].toString();
                System.out.println(s);
            }
        }
    }

}

