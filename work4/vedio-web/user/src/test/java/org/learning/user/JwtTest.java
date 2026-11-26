package org.learning.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

public class JwtTest {
    @Test
    public void testJwt(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",1);
        map.put("username","sb");
        map.put("password","123");
        SecretKey key= Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String keyStr=Encoders.BASE64.encode(key.getEncoded());
        String jwtStr= Jwts.builder().
                signWith(SignatureAlgorithm.HS256, keyStr)
                .addClaims(map)//添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis()+3600*1000))
                .compact();
        System.out.println(jwtStr);
        System.out.println("解码");
        Claims claims=Jwts.parser()
                .setSigningKey(keyStr)
                .build().parseSignedClaims(jwtStr).getBody();
        System.out.println(claims);

    }
}
