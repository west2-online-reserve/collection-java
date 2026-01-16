package Bonus.EmailVerification;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

public class EmailVerificationString {

    // 实用版邮箱正则：用户名 + @ + 域名 + 顶级域名
    // 说明：如果你想更严格（禁止 -domain.com / domain-.com），我后面也给你改法
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$"
    );

    public static boolean isValidEmail(String email) {
        if (email == null) return false;
        String s = email.trim();
        if (s.isEmpty()) return false;
        return EMAIL_PATTERN.matcher(s).matches();
    }

    public static void main(String[] args) {
        // 默认读取 emails.txt（优先从项目根目录读；读不到再从 classpath/resources 读）
        String fileName = "emails.txt";
        validateFromFile(fileName);
    }

    public static void validateFromFile(String fileName) {
        try (InputStream is = openFile(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

            String line;
            int lineNo = 0;

            while ((line = br.readLine()) != null) {
                lineNo++;
                String email = line.trim();

                // 跳过空行 / 注释行（以 # 开头）
                if (email.isEmpty() || email.startsWith("#")) {
                    continue;
                }

                boolean ok = isValidEmail(email);
                System.out.printf("Line %-3d  %-30s  => %s%n",
                        lineNo, email, ok ? "VALID" : "INVALID");
            }

        } catch (Exception e) {
            System.err.println("读取或校验失败: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private static InputStream openFile(String fileName) throws Exception {
        // 1) 项目根目录（直接运行 java EmailVerification 时最常用）
        try {
            return new FileInputStream(fileName);
        } catch (Exception ignore) {
            InputStream is = EmailVerificationString.class.getResourceAsStream(fileName);
            if (is != null) return is;
            throw new IllegalArgumentException("找不到文件");
        }
    }
}
