/*正则判断邮箱格式是否合法
 由于不了解邮箱一般格式，判断的规则为D老师给出
 */
import java.util.regex.Pattern;

public class EmailValidator{

    //设定长度上限
    private static final int MAX_TOTAL = 254;   // 整个地址
    private static final int MAX_LOCAL = 64;    // 本地部分
    private static final int MAX_DOMAIN = 253;  // 域名部分
    private static final int MAX_LABEL = 63;    // 单个域名标签
    private static final int MIN_TLD = 2;       // 顶级域最短
    private static final int MAX_TLD = 63;      // 顶级域最长

    // 注意：字符类里 - 放末尾、^ 不在开头即字面量；] 和 \ 不在此集合中
    private static final String LOCAL_ATOM =
            "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+";

    // 点分隔的 atom 序列：atom(.atom)*
    // 结构上天然禁止 首点、尾点、连续点
    private static final String LOCAL_PART =
            LOCAL_ATOM + "(?:\\." + LOCAL_ATOM + ")*";

    // 域名标签：1~63 个字符，字母数字开头结尾，中间可含连字符
    private static final String LABEL =
            "[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?";

    // 域名：一个或多个 "标签." + 顶级域（纯字母，2~63 位）
    private static final String DOMAIN =
            "(?:" + LABEL + "\\.)+[A-Za-z]{" + MIN_TLD + "," + MAX_TLD + "}";

    // 整体模式：^ + 本地部分 + @ + 域名 + \z（\z 是绝对结尾，比 $ 严格）
    private static final Pattern STRICT =
            Pattern.compile("^" + LOCAL_PART + "@" + DOMAIN + "\\z");

    private EmailValidator() {}

    /**
     * 严格校验邮箱格式。
     * 支持：普通字母数字、. _ % + - 以及 RFC atext 特殊字符（!#$%&'*+/=?^_`{|}~），
     * 拒绝：连续点、首尾点、域名标签首尾连字符、无 TLD、TLD 含非字母、超长等。
     * 不支持（业务一般不需要）：引号本地部分、"a b"@x.com、IP 字面量 user@[1.2.3.4]、中文 IDN。
     */
    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        String e = email.trim();
        if (e.isEmpty() || e.length() > MAX_TOTAL) return false;

        int at = e.lastIndexOf('@');
        if (at <= 0 || at > MAX_LOCAL) return false;          // 本地部分 1~64
        if (at == e.length() - 1) return false;               // 没有域名
        if (e.length() - at - 1 > MAX_DOMAIN) return false;   // 域名超长

        return STRICT.matcher(e).matches();
    }

    private static String repeat(char c, int n) {
        char[] arr = new char[n];
        java.util.Arrays.fill(arr, c);
        return new String(arr);
    }
//测试方法部分
    public static void main(String[] args) {
        String[] valid = {
                "a@b.com",
                "zhang.san@example.com",
                "user+tag@mail.co.uk",
                "first_last@sub.domain.org",
                "x@y.io",
                "user!#$%&'*+-/=?^_`{|}~@example.com",
                "a.b.c@x.com",
                "USER.Name@Example.COM",
                "u1@sub-1.example.co"
        };

        String[] invalid = {
                null,
                "",
                "   ",
                "plainaddress",                 // 没有 @
                "@no-local.com",                // 无本地部分
                "user@",                        // 无域名
                "user@.com",                    // 域名以点开头
                "user@domain",                  // 没有 TLD
                "user@domain.c",                // TLD 只有 1 位
                "user@domain.c0m",              // TLD 含数字
                "user@domain.c-m",              // TLD 含连字符
                "user..name@x.com",             // 本地部分连续点
                ".user@x.com",                  // 本地部分以点开头
                "user.@x.com",                  // 本地部分以点结尾
                "user@-bad.com",                // 域名标签以连字符开头
                "user@bad-.com",                // 域名标签以连字符结尾
                "user@bad..com",                // 域名连续点
                "user@x.com.",                  // 域名以点结尾
                "user name@x.com",              // 含空格
                "user@x.com extra",             // 尾部多余内容
                "user@@x.com",                  // 两个 @
                repeat('a', 65) + "@x.com"      // 本地部分 65 位，超长
        };

        int fail = 0;

        System.out.println("合法的邮箱们");
        for (String s : valid) {
            boolean r = isValidEmail(s);
            if (!r) fail++;
            System.out.printf("%-45s 判断： %s%n", s, r);
        }

        System.out.println("不合法的邮箱们");
        for (String s : invalid) {
            boolean r = isValidEmail(s);
            if (r) fail++;
            System.out.printf("%-45s 判断： %s%n", s == null ? "null" : s, r);
        }

    }
}