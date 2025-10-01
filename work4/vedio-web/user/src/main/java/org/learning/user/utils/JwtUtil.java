package org.learning.user.utils;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class JwtUtil {

    /* ========================= 1. 配置常量 ========================= */
    /** 密钥长度≥256bit（32字节），否则 HS256 会抛异常 */
    private static final String DEFAULT_SECRET = "BootJwtSecretKey2025BootJwtSecretKey32"; // 32字节
    /** 默认过期 30 分钟 */
    private static final long DEFAULT_EXPIRE = 1000 * 60 * 30;

    /* ========================= 2. 生成密钥 ========================= */
    private static SecretKey getKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    /* ========================= 3. 快速生成 ========================= */
    public static String createToken(String subject) {
        return createToken(subject, Map.of(), DEFAULT_EXPIRE, DEFAULT_SECRET);
    }

    public static String createToken(String subject, Map<String, Object> extraClaims) {
        return createToken(subject, extraClaims, DEFAULT_EXPIRE, DEFAULT_SECRET);
    }

    public static String createToken(String subject,
                                     Map<String, Object> extraClaims,
                                     long expireMillis,
                                     String secret) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + expireMillis);
        return Jwts.builder()
                .subject(subject)               // 主题，一般存用户ID
                .claims(extraClaims)            // 自定义载荷
                .issuedAt(now)                  // 签发时间
                .expiration(exp)                // 过期时间
                .signWith(getKey(secret))       // 算法+密钥
                .compact();
    }

    /* ========================= 4. 解析 ========================= */
    public static Claims parse(String token) {
        return parse(token, DEFAULT_SECRET);
    }

    public static Claims parse(String token, String secret) {
        try {
            return Jwts.parser()
                    .verifyWith(getKey(secret))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid JWT", e);
        }
    }

    /* ========================= 5. 校验 ========================= */
    public static boolean validate(String token) {
        return validate(token, DEFAULT_SECRET);
    }

    public static boolean validate(String token, String secret) {
        try {
            parse(token, secret);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* ========================= 6. 取单个值 ========================= */
    public static String getSubject(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public static Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    public static <T> T getClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(parse(token));
    }

    /* ========================= 7. 刷新 ========================= */
    public static String refresh(String token, long expireMillis) {
        Claims old = parse(token);

        Map<String,Object> claims = new HashMap<>(old);
        return createToken(old.getSubject(), claims, expireMillis, DEFAULT_SECRET);
    }

    /* ========================= 8. 是否即将过期 ========================= */
    public static boolean willExpireSoon(String token, int seconds) {
        Date exp = getExpiration(token);
        return exp.getTime() - System.currentTimeMillis() < seconds * 1000L;
    }

    /* ========================= 9. 私有构造 ========================= */
    private JwtUtil() {}
}
