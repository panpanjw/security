package com.security;


import com.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
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
        map.put("id", userEntity.getId());
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


    /**
     * 从Token中获取用户名
     * @param token
     * @return
     */
    public  String getUserNameForToken(String token){
        String userName = null;
        try {
            Claims claims = getClaimsFormToken(token);
            userName = claims.get("sub", String.class);
        } catch (Exception e){
            userName = null;
        }

        return userName;
    }

    /**
     * 解析Token
     * @param token
     * @return
     */
    private Claims getClaimsFormToken(String token){
        Claims claims = null;
        try {
            Key key = new SecretKeySpec(SINGNING_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());
            claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }


}
