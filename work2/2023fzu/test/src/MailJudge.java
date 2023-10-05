import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailJudge {
    public static void main(String[] args) {
        String mail = "2797077601@qq.com";
        System.out.println(checkEmail(mail));
        /*String text = "我我我我我我喜欢欢欢欢欢欢欢欢欢欢欢欢编编编编编编编编编编编编编编编程程程程";
        System.out.println(changeStutter(text));*/

    }
    public static boolean checkEmail(String email){
        return email.matches( "\\w{2,}"+ "@" +"\\w{2,20}(\\.\\w{2,20})+");//注意\.代表任意字符
        //------------------------------------------------------------+表示出现一次或多次
        //match(匹配)
    }

    public static ArrayList<String> findSomething(String text){
        ArrayList<String> strings = new ArrayList<>();
        //regex
        String regex = "\\d";
        //把regex封装成一个pattern对象
        Pattern pattern = Pattern.compile(regex);
        //通过pattern 得到查找内容的匹配器
        Matcher matcher = pattern.matcher(text);
        //通过匹配器开始去内容中查找信息
        while(matcher.find()){//找到了返回true,找完了返回false
            strings.add(matcher.group());
            System.out.println(matcher.group());
        }
        return strings;
    }
    public static String changeStutter(String text){//stutter,口吃
        //要把一个口吃的人说的:
        //我我我我我我喜欢欢欢欢欢欢欢欢欢欢欢欢编编编编编编编编编编编编编编编程程程程
        //改成:
        //我喜欢编程
        text = text.replaceAll("(.)\\1+" ,"$1");
        /*
        (.) : 表示一组
        \\1 : 为这个组声明了一个组号
        + : 声明必须是重复的字
        $1 : 取到组 1 代表的拿分重复字符
         */
        return text;
    }




}
