import java.util.LinkedList;

/**
 * Date 2023/10/22  11:12
 *
 * @author Kkkrran
 * @version 1.0
 */
public class MyLinkedList<E> extends LinkedList<E> {
    @Override
    public String toString() {
        String s = "";
        int len = this.size();
        for (int i = 0; i < len; i++) {
            Object a = this.get(i);
            s += a.toString() + "\n";
        }
        return s;
    }
}
