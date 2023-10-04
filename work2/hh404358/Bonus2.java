package org.example;

public class Bonus2 {
    //bonus2:正则表达式检验邮箱
    public static boolean CheckBox(String s){
        /*  权当注释
        String ret1="\\d{0,10}\\@qq.com";//qq邮箱
        String ret2="\\d{0,10}\\@tom.com";//TOM
        String ret3="\\d{0,10}\\@gmail.com";//谷歌邮箱
        String ret4="\\d{0,10}\\@hotmail.com";//微软
        String ret5="\\d{0,10}\\@icloud.com";//苹果邮箱*/

        /*  第二种想法没有充分运用正则表达式
        String box[]={"qq","tom","gamil","hotmail","icloud"};
        String ret="";
        for(int i=0;i<box.length;i++)
        ret="\\d{0,10}\\@"+box[i]+"\\.com";*/

        String ret="\\d{0,10}@(qq|tom|gmail|hotmail|icloud).com";
        return s.matches(ret);
    }

    public static boolean test2(){
        return (CheckBox("1234567890@qq.com"));
    }
}
