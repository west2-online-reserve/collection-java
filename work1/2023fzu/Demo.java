/**
 * @author HarveyBlocks
 * @date 2023/10/03 17:05
 **/
public class Demo {
    public static void main(String[] args) {
        Booth booth = new Booth();
        booth.restock(2);
        Booth.purchase(booth,3);
        Booth[] booths = new Booth[20];
        for (int i = 0; i < 20; i++) {
            booths[i]=new Booth(i,"booth"+i,200*i, true);
        }
        Booth.closeBooths(booths);
        System.out.println(booth);

    }
}
