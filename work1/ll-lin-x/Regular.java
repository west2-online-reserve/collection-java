package west2.task1;

public class Regular {
    public static void main(String[] args) {
        String s = "abc132413432@163.com";
        Regular reg = new Regular();
        System.out.println(reg.isPostStyle(s));
    }
    public boolean isPostStyle(String s){
        boolean matches = s.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.com");
        return matches;
    }
}
