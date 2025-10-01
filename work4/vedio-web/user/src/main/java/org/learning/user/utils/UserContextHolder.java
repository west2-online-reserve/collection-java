package org.learning.user.utils;

import org.learning.user.pojo.User;

public class UserContextHolder {
    // 静态的 ThreadLocal 实例
    private static final ThreadLocal<User> CURRENT_USER = new ThreadLocal<>();

    // 提供公共的静态方法进行操作，而不是直接暴露变量
    public static void setCurrentUser(User user) {
        CURRENT_USER.set(user);
    }

    public static User getCurrentUser() {
        return CURRENT_USER.get();
    }

    // 非常重要：清理数据
    public static void clear() {
        CURRENT_USER.remove();
    }
}
