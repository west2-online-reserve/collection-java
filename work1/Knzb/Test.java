package work1;

import work1.Booth;

public class Test {
    public static void main(String[] args) {
        Booth booth = new Booth(123456,"keYuQing",12,false);
        //String s = booth.toString();
        System.out.println(booth);
        Booth.purchase(booth,10);
        Booth.purchase(booth,5);
        Booth.purchase(booth,-1);
        booth.setClosed(true);
        Booth.purchase(booth,10);
        booth.setClosed(false);
        booth.restock(201);
        booth.restock(200);
        booth.restock(-2);
        Booth.purchase(booth,2000);
        System.out.println(booth.toString());
        booth.setClosed(true);
        Booth[] array = new Booth[2];
        array[0] = booth;
        Booth booth1 = new Booth(135790,"meili",11,false);
        array[1] = booth1;
        Booth.closeBooths(array);
        Booth.closeBooths(array);
    }
}
