package com.security;


import com.entity.UserEntity;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author panjw
 * @date 2021/3/15 14:26
 */
public class JwtTokenUtil {

    private static String SINGNING_KEY = "123456";


    public static String createToken(UserEntity userEntity, long expirationMillis){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        Map<String, Object> map = new HashMap<>();
        map.put("id", userEntity.getUserId());
        map.put("username", userEntity.getUserName());

        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(userEntity.getUserName())   //主题-用户名
                .setClaims(map)
                .setIssuedAt(now)   //发行时间
                .setExpiration(new Date(nowMillis + expirationMillis))     //过期时间
                .signWith(SignatureAlgorithm.HS256, SINGNING_KEY);

        return jwtBuilder.compact();
    }



    public static String verifyToken(String token){
        String userId = Jwts.parser()
                .setSigningKey(SINGNING_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getId();
        return userId;
    }


}
