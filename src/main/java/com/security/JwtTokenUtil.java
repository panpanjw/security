package com.security;


import com.entity.UserEntity;
import com.security.usersDetail.JwtUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

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


    /**
     * 生成令牌，Token
     * @param userEntity
     * @param expirationMillis
     * @return
     */
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
    public static String getUserNameFromToken(String token){
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
     * 校验令牌
     * @param token
     * @param userDetails
     * @return
     */
    public static Boolean checkToken(String token, UserDetails userDetails){
        //验证用户名是否相同
        JwtUserDetail jwtUserDetail = (JwtUserDetail) userDetails;
        String userName = getUserNameFromToken(token);

        if (StringUtils.isEmpty(userName)){
            return false;
        }

        if ( !jwtUserDetail.equals(userName) ){
            return false;
        }

        if ( !isTokenExpired(token) ){
            return false;
        }

        return true;
    }

    /**
     * 刷新令牌
     * @param token
     * @return
     */
    public String refreshToken(String token){
        Claims claims = getClaimsFormToken(token);
        return null;
    }

    /**
     * 解析Token,从令牌中获取数据声明
     * @param token
     * @return
     */
    private static Claims getClaimsFormToken(String token){
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SINGNING_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }

        return claims;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    private static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFormToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }


}
