package com.huayu.work02;

/**
 * BonusTest2类表示对正则表达式的测试结果
 *
 * 该类包含了正则判断方法
 * @author 余思衡
 * date 2023/10/27
 */
public class BonusTest2 {
    /**
     * 该方法用于接收邮箱地址并判断是否符合邮箱格式
     *
     * @param mailbox
     * @return boolean
     */
    public static boolean judge(String mailbox) {
        String regex1 = "[\\w\\.+-]{1,64}[@][A-Za-z0-9][A-Za-z0-9\\.-]{0,253}[A-Za-z0-9]";
        if (mailbox.matches(regex1)) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        String mailbox1 = "2874602095@qq.com";
        String mailbox2 = "51611615@$";
        System.out.println(judge(mailbox1));
        System.out.println(judge(mailbox2));

    }
}
