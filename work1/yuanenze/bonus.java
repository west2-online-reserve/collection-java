package petStore;

public class bonus extends Thread {
    public static void main(String args[]) {
        Temp t = new Temp();
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 0};

        A a = new A(arr1);
        B b = new B(arr2);
        a.start();
        b.start();
    }

//    public static boolean cmp(String s0){
//        //没有理解邮箱的一般格式，CSDN上说就是“名称+域名”，
//        // 名称没有规定几个字符，域名右三级和二级之分，我原来写的带盖就也是这样的，不知道怎么改，求指正
//    }


}
