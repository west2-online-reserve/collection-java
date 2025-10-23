import java.util.Scanner;

public class  Booth {

     private long id;
     private String name;
     private int total;
     private boolean isClosed;

//私有变量 只能在类里面进行访问


   //上述变量对应的get set方法：
   public long getId() {    // I 要大写..万恶的命名约定
    return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }//set像输入 get像输出   this.？ = 实例变量，？ = 参数，相当于一个过程（？）
     // return作用为方法返回值，告诉结果。


    //to string重写
    public String toString() {
    return " 西瓜摊 [" + id + "] - " + name + "  库存:" + total + "  " + (isClosed ? "休业" : "营业");
}


//构造方法
public  Booth(long id, String name, int total ,boolean isClosed) {
    this.id = id;
    this.name = name;
    this.total = total;
    this.isClosed = isClosed;
}

//静态方法
public static void Purchase(Booth shangjia, int count){
     if(count<0){
        System.out.println("购买失败,商家无法提供负数个西瓜");
        return;
     }

     if(shangjia.isClosed){
        System.out.println("购买失败,商家已歇业");
        return;
     }

     if(shangjia.total<count){
        System.out.println("购买失败,商家无法提供足够数量的西瓜");
        return;
     }
    
     else{
        shangjia.total -=count;
        System.out.println("购买成功，欢迎下次光临");
        return ;
     }

}


//一个实例方法 restock(int 进货西瓜数)单次进货量不能超过 200，进货失败则输出相应的提示信息。
public void restock(int count){
    if(count>200){
        System.out.println("购买失败,最大进货量不能超过两百");
        return;
    }
    
    this.total+=count;
    System.out.println("成功,"+ this.id +" 号摊位已经成功购入"+count +"个西瓜");
        
}

// 静态休业方法:  * 一个静态(static)方法 让 booths 中所有未被休 业整改的摊位歇业(将 false 变为 true)，输出已在休业整改的摊位信息（调用实例的 toString()方法）。
    public static void closeBooths(Booth[] booths) {   //booths 指的是一个数组，这个数组里面存放了多个Booth对象（也就是多个西瓜摊）。
//         Booth[] booths 的含义：
// Booth[] = "一个装着西瓜摊对象的容器"
// booths = "这个容器的名字，我们可以通过它来访问里面的每个摊位   可以对每个booth进行批量处理
       
        for(int i=0; i<booths.length;i++){
            Booth booth=booths[i];
            if (!booth.isClosed()) {
                booth.setClosed(true);
            
        }
        else {
            System.out.println(booth.toString());
        }
    }
}

//1
    public static void main(String[] args) {

       
        Booth booth1 = new Booth(1001, "张三西瓜摊", 50, false);
        Booth booth2 = new Booth(1002, "李四水果摊", 30, true);
    //     // 使用无参构造方法和setter创建摊位
    // Booth booth2 = new Booth();
    // booth2.setId(1002);
    // booth2.setName("李四水果摊");
    // booth2.setTotal(30);
    // booth2.setClosed(true);

        
        
        System.out.println("摊位信息：");
        System.out.println(booth1.toString());
        
        System.out.println("\n购买测试：");
        Booth.Purchase(booth1, 10);
        
        System.out.println("\n进货测试：");
        booth1.restock(20);
        
        System.out.println("\n批量休业测试：");
        Booth[] booths = {booth1, booth2};
        Booth.closeBooths(booths);
    }
}
