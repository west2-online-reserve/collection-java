

## 模块接口的设计与实现过程

一共六个类，其中Athlete,Event,EventDetail被用于储存从json中读入的信息。

CoreModule被用于实现程序的主要功能(例如其函数displayAllPlayersInfo可以将运动员信息输入output.txt中)。

FileIO被用于实现文件的读取写入。

DWASearch是程序的主函数。

DWASearch会调用FileIO来读取input.txt内容以执行指令并调用相应的CoreModule的方法,CoreModule会解析.json文件的数据并存入Athlete,Event,EventDetail中,之后通过FileIO存入output.txt。

算法的关键:json文件的解析

独到之处:使用了linkedHashMap来在保持顺序的情况下通过Event构建EventDetail

## 模块接口部分的性能改进

使用了哈希表来防止多次遍历查找

使用了缓冲流来提高文件读写速度

## 单元测试展示

```
@Test
public void test1(){
    //测试无参数
    try {
        DWASearch.main(new String[]{});
    }
    catch (Exception e){
        e.printStackTrace();
    }

}

@Test
public void test2(){
    //测试参数为空
    try {
        DWASearch.main(new String[]{" "," "});
    }
    catch (Exception e){
        e.printStackTrace();
    }
}

@Test
public void test3(){
    //测试参数错误(无前txt)
    try {
        DWASearch.main(new String[]{"input","output.txt"});
    }
    catch (Exception e){
        e.printStackTrace();
    }
}
......
@Test
    public void test5(){
        //测试参数正确
        try {
            DWASearch.main(new String[]{"input.txt","output.txt"});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
......
 @Test
    public void test9(){
        //测试参数错误(多参数)
        try {
            DWASearch.main(new String[]{"input.txt","output.txt","aaa.txt","bbb.txt"});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test10(){
        //测试参数错误
        try {
            DWASearch.main(new String[]{"input.txt","output.t"});
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
```

## 异常处理

使用try catch块处理异常

